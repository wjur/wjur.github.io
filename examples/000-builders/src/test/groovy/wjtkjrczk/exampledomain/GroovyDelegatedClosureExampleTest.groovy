package wjtkjrczk.exampledomain

import spock.lang.Specification

import static java.time.Instant.parse
import static wjtkjrczk.exampledomain.builders.DomainObjectGroovyDelegatedClosureBuilder.domainObject

class GroovyDelegatedClosureExampleTest extends Specification {

    def "when valueX is set then it has a correct value"() {
        given:
        def obj = domainObject {
            id = 1337
            name = "Some name"

            // Note that it uses a setter method.
            updatedAt = "2018-04-14T23:10:16.123Z"

            // If you don't remember fields' names, you can refer to the delegate variable to see hints.
            delegate.description = "Some other description"
        }

        expect:
        with(obj) {
            id == 1337
            name == "Some name"
            updatedAt == parse("2018-04-14T23:10:16.123Z")
            description == "Some other description"
        }
    }
}
