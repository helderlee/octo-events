package dev.helderlee.octo.producer.endpoint

import dev.helderlee.octo.producer.handler.ProducerHandler
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.EndpointGroup

class ProducerEndpointGroup : EndpointGroup {

    override fun addEndpoints() {
        post("/issues/events", ProducerHandler::requestSaveEvent)
    }
}
