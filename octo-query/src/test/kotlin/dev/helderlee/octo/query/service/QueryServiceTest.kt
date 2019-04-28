package dev.helderlee.octo.query.service

import com.google.gson.Gson
import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.common.domain.response.Event
import dev.helderlee.octo.persistence.repository.IssueEventRepositoryImpl
import dev.helderlee.octo.query.AppConfig
import org.apache.commons.io.IOUtils
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.nio.charset.StandardCharsets

@RunWith(MockitoJUnitRunner::class)
class QueryServiceTest {

    @InjectMocks
    private lateinit var queryService: QueryServiceImpl

    @Mock
    private lateinit var issueEventRepository: IssueEventRepositoryImpl

    private lateinit var payload: IssueEvent

    private val gson = Gson()

    @BeforeEach
    fun setUp() {
        AppConfig().config()

        MockitoAnnotations.initMocks(this)

        val payloadIS = javaClass.getResourceAsStream("/payload/issue-event-valid-2.json")
        val payloadString = IOUtils.toString(payloadIS, StandardCharsets.UTF_8.name())
        payload = gson.fromJson(payloadString, IssueEvent::class.java)
    }

    @Test
    fun `Service Test - Asserts the successful retrieving of events`() {
        doReturn(listOf(
            Event("edited", DateTime("2018-05-30T20:18:32Z").toString(), DateTime("2018-05-30T20:18:32Z").toString(), null, "Codertocat")
        )).`when`(issueEventRepository).list(1)
        assertEquals(listOf(
            Event("edited", DateTime("2018-05-30T20:18:32Z").toString(), DateTime("2018-05-30T20:18:32Z").toString(), null, "Codertocat")
        ), queryService.getEvents(1))
    }

    @Test
    fun `Service Test - Asserts the failed retrieving of events`() {
        doReturn(listOf(Event("", "", "", "", ""))).`when`(issueEventRepository).list(1)
        assertNotEquals(listOf(
            Event("edited", DateTime("2018-05-30T20:18:32Z").toString(), DateTime("2018-05-30T20:18:32Z").toString(), null, "Codertocat")
        ), queryService.getEvents(1))
    }

}
