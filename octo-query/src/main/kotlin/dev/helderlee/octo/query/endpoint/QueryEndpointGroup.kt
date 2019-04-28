package dev.helderlee.octo.query.endpoint

import dev.helderlee.octo.query.handler.QueryHandler
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.EndpointGroup

class QueryEndpointGroup : EndpointGroup {

    override fun addEndpoints() {
        path("/issues") {
            get("/:number/events", QueryHandler::getEvents)
        }

    }
}
