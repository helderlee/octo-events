package dev.helderlee.octo.producer.endpoint

import dev.helderlee.octo.producer.Application
import io.javalin.Javalin
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek

object EndpointTest : Spek({

    lateinit var app: Javalin
    val baseUrl = "http://localhost:8080"

    beforeGroup {
        app = Application().start()
    }

    group("Endpoint Tests") {
        beforeEachTest {
        }

        test("Spek Test - Asserts endpoint not found") {
            val response = khttp.get(
                url = "$baseUrl/endpoint/not/found"
            )
            assertEquals(404, response.statusCode)
        }

        test("Spek Test - Asserts payload endpoint found with success") {
            val payload = javaClass.getResourceAsStream("/payload/issue-event-valid-3.json")!!
            val response = khttp.post(
                url = "$baseUrl/issues/events",
                data = payload
            )
            assertEquals(200, response.statusCode)
            assertEquals("payload loaded", response.jsonObject["message"])
        }

        test("Spek Test - Asserts payload endpoint found with error") {
            val payload = javaClass.getResourceAsStream("/payload/issue-event-invalid-3.json")!!
            val response = khttp.post(
                url = "$baseUrl/issues/events",
                data = payload
            )
            assertEquals(406, response.statusCode)
            assertEquals("payload not loaded", response.jsonObject["message"])
        }

        afterEachTest {
        }
    }

    afterGroup {
        app.stop()
    }
})