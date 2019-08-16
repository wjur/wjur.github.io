package io.github.wjur.issues.spring

import org.springframework.data.annotation.AccessType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "domain_events")
data class StoredEvent<AGGREGATE, ID>(
        @Id val sequenceNo: Long = 0,
        val event: DomainEvent<AGGREGATE, ID>? = null
)


abstract class DomainEvent<AGGREGATE, ID>(
    val aggregateId: ID,
    val root: AGGREGATE
) {
    override fun toString(): String {
        return "DomainEvent(aggregateId=$aggregateId, root=$root)"
    }
}


class OfferCreated(
        aggregateId: Long,
        root: OfferDetails
) : DomainEvent<OfferDetails, Long>(aggregateId, root) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OfferCreated

        if (aggregateId != other.aggregateId) return false
        if (root != other.root) return false

        return true
    }

    override fun hashCode(): Int {
        var result = aggregateId.hashCode()
        result = 31 * result + root.hashCode()
        return result
    }

    override fun toString(): String {
        return "OfferCreated(aggregateId=$aggregateId, root=$root)"
    }
}


data class OfferDetails(
        val name: String
) /* : WithClassHintWorkaround */

interface WithClassHintWorkaround {

    var classHintWorkaroundField: String
        @Field("_class")
        @AccessType(AccessType.Type.PROPERTY)
        get() = javaClass.name
        @Field("_class")
        set(value) {

        }

}