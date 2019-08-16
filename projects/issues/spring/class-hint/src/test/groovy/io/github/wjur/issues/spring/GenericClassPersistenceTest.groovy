package io.github.wjur.issues.spring


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(
    classes = [AppRunner],
    properties = "application.environment=integration",
    webEnvironment = RANDOM_PORT)
@ContextConfiguration
class GenericClassPersistenceTest extends Specification {

    @Autowired EntityRepository entityRepository

    def setup() {
        entityRepository.deleteAll()
    }

    def "should save and find event"() {
        given:
        StoredEvent storedEvent = persistedDomainEvent()

        when:
        StoredEvent<OfferCreated, Long> found = entityRepository.findById(100).get()

        then:
        found == storedEvent
    }

    def "should save and find event without type info"() {
        given:
        StoredEvent storedEvent = persistedDomainEvent()

        expect:
        entityRepository.findById(100).get() == storedEvent
    }

    def "should save and findAll events"() {
        given:
        StoredEvent storedEvent = persistedDomainEvent()

        expect:
        entityRepository.findAll() as Set == [storedEvent] as Set
    }

    private StoredEvent persistedDomainEvent() {
        OfferDetails offerDetails = new OfferDetails("offer-name")

        OfferCreated offerCreated = new OfferCreated(123, offerDetails)

        StoredEvent storedEvent = new StoredEvent(100, offerCreated)
        return entityRepository.save(storedEvent)
    }
}
