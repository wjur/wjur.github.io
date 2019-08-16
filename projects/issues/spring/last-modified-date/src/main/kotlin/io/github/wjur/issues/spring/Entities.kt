package io.github.wjur.issues.spring

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("entities")
data class Entity(
        @Id val id: Long,
        @LastModifiedDate val modificationDate: Instant?,
        val detail: String,
        @Version val version: Long?
) {
    fun withDetail(detail: String) = copy(detail = detail)
}

@Document("entities")
data class EntityVar(
        @Id val id: Long,
        @LastModifiedDate var modificationDate: Instant?,
        val detail: String,
        @Version val version: Long?
) {
    fun withDetail(detail: String) = copy(detail = detail)
}

@Document("entities")
data class EntityWither(
        @Id val id: Long,
        @LastModifiedDate val modificationDate: Instant?,
        val detail: String,
        @Version val version: Long?
) {
    fun withDetail(detail: String) = copy(detail = detail)
    fun withModificationDate(modificationDate: Instant): EntityWither = copy(modificationDate = modificationDate)
}
