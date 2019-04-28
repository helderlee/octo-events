package dev.helderlee.octo.query.endpoint

import com.google.gson.Gson
import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.persistence.repository.IssueEventRepositoryImpl
import dev.helderlee.octo.persistence.repository.IssueRepositoryImpl
import dev.helderlee.octo.persistence.table.Issues
import dev.helderlee.octo.persistence.table.IssuesEvents
import dev.helderlee.octo.query.Application
import io.javalin.Javalin
import org.apache.commons.io.IOUtils
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import java.nio.charset.StandardCharsets

object EndpointTest : Spek({

    lateinit var queryApp: Javalin
    val gson = Gson()
    val queryUrl = "http://localhost:8787"

    beforeGroup {
        queryApp = Application().start()
        transaction {
            SchemaUtils.drop(Issues, IssuesEvents)
            SchemaUtils.create(Issues, IssuesEvents)
        }
    }

    group("Endpoint Tests") {
        beforeEachTest {
        }

        test("Spek Test - Asserts endpoint not found") {
            val response = khttp.get(
                url = "$queryUrl/endpoint/not/found"
            )
            assertEquals(404, response.statusCode)
        }

        test("Spek Test - Asserts issue events endpoint found with success") {
            val payloadIS = javaClass.getResourceAsStream("/payload/issue-event-valid-3.json")!!
            val payloadString = IOUtils.toString(payloadIS, StandardCharsets.UTF_8.name())
            val issueEvent = gson.fromJson(payloadString, IssueEvent::class.java)
            val issueRepository = IssueRepositoryImpl()
            val issueEventRepository = IssueEventRepositoryImpl()
            issueRepository.save(issueEvent.issue)
            issueEventRepository.save(issueEvent)
            val response = khttp.get(
                url = "$queryUrl/issues/3/events"
            )
            assertEquals(200, response.statusCode)
            assertEquals(1, response.jsonArray.length())
        }

        test("Spek Test - Asserts issue events endpoint found with error") {
            val response = khttp.get(
                url = "$queryUrl/issues/0/events"
            )
            assertEquals(200, response.statusCode)
            assertEquals(0, response.jsonArray.length())
        }

        afterEachTest {
        }
    }

    afterGroup {
        transaction {
            SchemaUtils.drop(Issues, IssuesEvents)
            SchemaUtils.create(Issues, IssuesEvents)
        }
        queryApp.stop()
    }
})