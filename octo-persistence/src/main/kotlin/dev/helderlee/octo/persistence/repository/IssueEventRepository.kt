package dev.helderlee.octo.persistence.repository

import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.common.domain.response.Event

interface IssueEventRepository {
    fun save(issueEvent: IssueEvent): Boolean
    fun list(issueNumber: Int): List<Event>
}

