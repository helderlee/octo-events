package dev.helderlee.octo.persistence.repository

import com.google.gson.Gson
import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.common.domain.response.Event
import org.apache.commons.io.IOUtils
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.standalone.inject
import java.nio.charset.StandardCharsets

class IssueEventRepositoryTest : InitTest() {

    private val issueRepository by inject<IssueRepository>()
    private val issueEventRepository by inject<IssueEventRepository>()
    private val gson = Gson()
    private lateinit var payload: IssueEvent

    @BeforeEach
    fun setUp() {
        val payloadIS = javaClass.getResourceAsStream("/payload/issue-event-valid-2.json")
        val payloadString = IOUtils.toString(payloadIS, StandardCharsets.UTF_8.name())
        payload = gson.fromJson(payloadString, IssueEvent::class.java)
    }

    @Test
    fun `DB Test - Asserts the successful retrieval of events`() {
        Assertions.assertEquals(true, issueRepository.save(payload.issue))
        Assertions.assertEquals(true, issueEventRepository.save(payload))
        val expected = listOf(
            Event("edited", DateTime("2018-05-30T20:18:32Z").toString(), DateTime("2018-05-30T20:18:32Z").toString(), null, "Codertocat")
        )
        val result = issueEventRepository.list(2)
        Assertions.assertEquals(expected, result)
    }
}