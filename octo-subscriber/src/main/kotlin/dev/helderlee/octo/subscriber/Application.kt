package dev.helderlee.octo.subscriber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("classpath:koin.properties")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    AppConfig().config()
}


