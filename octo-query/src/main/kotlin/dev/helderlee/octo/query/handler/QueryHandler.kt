package dev.helderlee.octo.query.handler

import dev.helderlee.octo.query.service.QueryService
import io.javalin.Context
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object QueryHandler : KoinComponent {

    private val issueEventService by inject<QueryService>()

    fun getEvents(ctx: Context) {
        val issueNumber = ctx.pathParam("number").toInt()
        val events = issueEventService.getEvents(issueNumber)
        ctx.json(events)
    }

}
