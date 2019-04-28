package dev.helderlee.octo.query.service

import dev.helderlee.octo.common.domain.response.Event

interface QueryService {
    fun getEvents(issueNumber: Int): List<Event>
}

