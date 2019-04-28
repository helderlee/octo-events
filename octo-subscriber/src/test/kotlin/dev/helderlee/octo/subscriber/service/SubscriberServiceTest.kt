package dev.helderlee.octo.subscriber.service

import com.google.gson.Gson
import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.persistence.repository.IssueEventRepositoryImpl
import dev.helderlee.octo.persistence.repository.IssueRepositoryImpl
import dev.helderlee.octo.subscriber.AppConfig
import org.apache.commons.io.IOUtils
import org.junit.jupiter.api.Assertions.assertEquals
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
class SubscriberServiceTest {

    @InjectMocks
    private lateinit var subscriberService: SubscriberServiceImpl

    @Mock
    private lateinit var issueRepository: IssueRepositoryImpl

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
    fun `Service Test - Asserts the successful inserting of payload`() {
        doReturn(true).`when`(issueRepository).save(payload.issue)
        doReturn(true).`when`(issueEventRepository).save(payload)
        assertEquals(true, subscriberService.saveEvent(payload))
    }

    @Test
    fun `Service Test - Asserts the failed inserting of payload`() {
        doReturn(false).`when`(issueRepository).save(payload.issue)
        doReturn(false).`when`(issueEventRepository).save(payload)
        assertEquals(false, subscriberService.saveEvent(payload))
    }

}
