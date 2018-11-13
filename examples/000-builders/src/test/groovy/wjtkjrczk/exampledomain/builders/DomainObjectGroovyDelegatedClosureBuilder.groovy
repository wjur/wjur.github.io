package wjtkjrczk.exampledomain.builders

import groovy.transform.TypeChecked
import groovy.transform.builder.Builder
import wjtkjrczk.exampledomain.DecisionMade
import wjtkjrczk.exampledomain.DomainObject

import java.time.Instant

@Builder
class DomainObjectGroovyDelegatedClosureBuilder {
    int id = 5
    String name = "n"
    DecisionMade decisionMade = new DecisionMade.Maybe()
    String description = "a description"
    Instant updatedAt = Instant.now()

    private DomainObject build() {
        new DomainObject(
                id,
                name,
                decisionMade,
                description,
                updatedAt
        )
    }

    void setUpdatedAt(String value) {
        updatedAt = Instant.parse(value)
    }

    @TypeChecked
    static DomainObject domainObject(
            @DelegatesTo(
                    value = DomainObjectGroovyDelegatedClosureBuilder,
                    strategy = Closure.DELEGATE_ONLY)
                    Closure closure
    ) {
        final DomainObjectGroovyDelegatedClosureBuilder builder = new DomainObjectGroovyDelegatedClosureBuilder()
        builder.with closure
        builder.build()
    }
}
