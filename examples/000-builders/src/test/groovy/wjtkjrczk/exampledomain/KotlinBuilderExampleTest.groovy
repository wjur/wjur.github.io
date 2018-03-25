package wjtkjrczk.exampledomain

import spock.lang.Specification


import static java.time.Instant.*
import static wjtkjrczk.exampledomain.DomainObjectKotlinBuilderKt.*

class KotlinBuilderExampleTest extends Specification {

    def "when valueX is set then it has a correct value"() {
        given:
        def obj = domainObject {
            // You must to refer to 'it', but you always get hints.
            it.id = 1337
            it.name = "Some name"

            // Note that it uses a setter method. IntelliJ infers the type correctly.
            it.updatedAt = "2018-04-14T23:10:16.123Z"
        }

        expect:
        with(obj) {
            id == 1337
            name == "Some name"
            updatedAt == parse("2018-04-14T23:10:16.123Z")
        }
    }
}
