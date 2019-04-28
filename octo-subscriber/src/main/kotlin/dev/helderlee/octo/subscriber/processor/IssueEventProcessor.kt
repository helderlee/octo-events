package dev.helderlee.octo.subscriber.processor

import com.google.gson.Gson
import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.subscriber.service.SubscriberService
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class IssueEventProcessor : Processor, KoinComponent {

    private val gson = Gson()
    private val issueEventService by inject<SubscriberService>()

    override fun process(exchange: Exchange?) {
        val body = exchange?.getIn()?.body

        if (body != null) {
            val issueEvent = gson.fromJson(body as String, IssueEvent::class.java)
            when (issueEventService.saveEvent(issueEvent)) {
                true -> exchange.getIn().body = "OK"
                else -> throw RuntimeException()
            }
        }
    }

}