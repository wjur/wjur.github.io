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
class LastModifiedDateTest extends Specification {

    @Autowired CrudRepository<Entity, Long> entityRepository
    @Autowired CrudRepository<EntityVar, Long> entityRepositoryVar
    @Autowired CrudRepository<EntityWither, Long> entityRepositoryWither

    def setup() {
        entityRepository.deleteAll()
    }

    def "should persist updated LastModifiedDate field (val)"() {
        given:
        Entity entity = new Entity(100L, null, 'foo', null)
        def inserted = entityRepository.save(entity)
        def modified = inserted.withDetail('bar')
        Thread.sleep(5000) // Sorry, but it was simpler to use Thread.sleep rather than mocking date time provider
        def updated = entityRepository.save(modified)
        def fetched = entityRepository.findById(100L).get()

        println("Inserted = ${inserted.modificationDate}")
        println("Updated  = ${updated.modificationDate}")
        println("Fetched  = ${fetched.modificationDate}")

        expect:
        updated.modificationDate.isAfter(inserted.modificationDate)
        !fetched.modificationDate.isBefore(updated.modificationDate.truncatedTo(ChronoUnit.MILLIS))
    }

    def "should persist updated LastModifiedDate field (var)"() {
        given:
        EntityVar entity = new EntityVar(100L, null, 'foo', null)
        def inserted = entityRepositoryVar.save(entity)
        def modified = inserted.withDetail('bar')
        Thread.sleep(1000) // Sorry, but it was simpler to use Thread.sleep rather than mocking date time provider
        def updated = entityRepositoryVar.save(modified)
        def fetched = entityRepositoryVar.findById(100L).get()

        println("Inserted = ${inserted.modificationDate}")
        println("Updated  = ${updated.modificationDate}")
        println("Fetched  = ${fetched.modificationDate}")

        expect:
        updated.modificationDate.isAfter(inserted.modificationDate)
        !fetched.modificationDate.isBefore(updated.modificationDate.truncatedTo(ChronoUnit.MILLIS))
    }

    def "should persist updated LastModifiedDate field (wither)"() {
        given:
        EntityWither entity = new EntityWither(100L, null, 'foo', null)
        def inserted = entityRepositoryWither.save(entity)
        def modified = inserted.withDetail('bar')
        Thread.sleep(1000) // Sorry, but it was simpler to use Thread.sleep rather than mocking date time provider
        def updated = entityRepositoryWither.save(modified)
        def fetched = entityRepositoryWither.findById(100L).get()

        println("Inserted = ${inserted.modificationDate}")
        println("Updated  = ${updated.modificationDate}")
        println("Fetched  = ${fetched.modificationDate}")

        expect:
        updated.modificationDate.isAfter(inserted.modificationDate)
        !fetched.modificationDate.isBefore(updated.modificationDate.truncatedTo(ChronoUnit.MILLIS))
    }
}
