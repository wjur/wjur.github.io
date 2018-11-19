Let's use type-safe test builders instead of using Maps!

```groovy
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
```

```groovy
class GroovyFunction1ExampleTest extends Specification {

    def "when valueX is set then it has a correct value"() {
        given:
        def obj = domainObject {
            // You must to refer to 'it', but you always get hints.
            it.id = 1337
            it.name = "Some name"

            // Note that it uses a setter method.
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
```

```groovy
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
```