package wjtkjrczk.exampledomain

import java.time.Instant

class DomainObjectKotlinBuilder {
    var id: Int = 5
    var name: String = "some-name"
    var decisionMade: DecisionMade = DecisionMade.Maybe
    var description: String = "An example description"
    var updatedAt: Instant = Instant.now()

    fun build(): DomainObject = DomainObject(
            id,
            name,
            decisionMade,
            description,
            updatedAt
    )

    fun setUpdatedAt(value: String){
        updatedAt = Instant.parse(value)
    }
}

fun domainObject(init: DomainObjectKotlinBuilder.() -> Unit): DomainObject {
    val builder = DomainObjectKotlinBuilder()
    init(builder)

    return builder.build()
}