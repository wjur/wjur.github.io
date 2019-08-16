package io.github.wjur.issues.spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class AppRunner {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(AppRunner::class.java, *args)
        }
    }
}
