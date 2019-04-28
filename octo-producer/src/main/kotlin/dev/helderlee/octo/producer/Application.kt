package dev.helderlee.octo.producer

import dev.helderlee.octo.common.domain.Config
import dev.helderlee.octo.producer.endpoint.ProducerEndpointGroup
import dev.helderlee.octo.producer.module.producerModule
import io.javalin.Javalin
import io.javalin.JavalinEvent
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.getProperty
import org.koin.standalone.inject
import org.slf4j.LoggerFactory

class Application : KoinComponent {

    companion object {
        val logger = LoggerFactory.getLogger(Application::class.java)
    }

    private val issueEventEndpoint by inject<ProducerEndpointGroup>()

    fun start() = Javalin.create().apply {
        StandAloneContext.startKoin(listOf(producerModule), properties = Config().loadKoinProperties())

        disableStartupBanner()
        error(404) { ctx -> ctx.json("not found") }
        event(JavalinEvent.SERVER_STOPPED) {
            StandAloneContext.stopKoin()
        }
        exception(Exception::class.java) { e, _ -> logger.error(e.message) }
        port(getProperty("javalin_producer_port"))

        routes {
            issueEventEndpoint.addEndpoints()
        }

        start()
    }!!

}

object BootRun {

    @JvmStatic
    fun main(args: Array<String>) {
        Application().start()
    }

}
