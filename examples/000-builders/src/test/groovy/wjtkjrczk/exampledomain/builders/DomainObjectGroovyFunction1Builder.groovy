package wjtkjrczk.exampledomain.builders

import kotlin.Unit
import kotlin.jvm.functions.Function1
import wjtkjrczk.exampledomain.DecisionMade
import wjtkjrczk.exampledomain.DomainObject

import java.time.Instant

class DomainObjectGroovyFunction1Builder {
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

    def setUpdatedAt(String value) {
        updatedAt = Instant.parse(value)
    }

    static DomainObject domainObject(
            @DelegatesTo(value = DomainObjectGroovyFunction1Builder, strategy = Closure.DELEGATE_ONLY)
                    Function1<DomainObjectGroovyFunction1Builder, Unit> function1
    ) {
        final DomainObjectGroovyFunction1Builder builder = new DomainObjectGroovyFunction1Builder()
        function1.invoke(builder)
        builder.build()
    }
}
