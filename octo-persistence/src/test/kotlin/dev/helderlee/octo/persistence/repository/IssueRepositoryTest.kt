package dev.helderlee.octo.persistence.repository

import com.google.gson.Gson
import dev.helderlee.octo.common.domain.github.IssueEvent
import org.apache.commons.io.IOUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.standalone.inject
import java.nio.charset.StandardCharsets

class IssueRepositoryTest : InitTest() {

    private val issueRepository by inject<IssueRepository>()
    private val gson = Gson()
    private lateinit var payload: IssueEvent

    @BeforeEach
    fun setUp() {
        val payloadIS = javaClass.getResourceAsStream("/payload/issue-event-valid-1.json")
        val payloadString = IOUtils.toString(payloadIS, StandardCharsets.UTF_8.name())
        payload = gson.fromJson(payloadString, IssueEvent::class.java)
    }

    @Test
    fun `DB Test - Asserts the successful inserting of payload`() {
        Assertions.assertEquals(true, issueRepository.save(payload.issue))
    }

}