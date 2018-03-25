package wjtkjrczk.exampledomain

import java.time.Instant

data class DomainObject(
        val id: Int,
        val name: String,
        val decisionMade: DecisionMade,
        val description: String,
        val updatedAt: Instant
)

sealed class DecisionMade {
    object Yes : DecisionMade()
    object No : DecisionMade()
    object Maybe : DecisionMade()
}