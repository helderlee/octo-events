package dev.helderlee.octo.query

import dev.helderlee.octo.query.endpoint.QueryEndpointGroup
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

    private val issueEventEndpoint by inject<QueryEndpointGroup>()

    fun start() = Javalin.create().apply {
        AppConfig().config()

        disableStartupBanner()
        error(404) { ctx -> ctx.json("not found") }
        event(JavalinEvent.SERVER_STOPPED) {
            StandAloneContext.stopKoin()
        }
        exception(Exception::class.java) { e, _ -> logger.error(e.message) }
        port(getProperty("javalin_query_port"))

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
