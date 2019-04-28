package dev.helderlee.octo.subscriber.service

import dev.helderlee.octo.common.domain.github.IssueEvent

interface SubscriberService {
    fun saveEvent(issueEvent: IssueEvent): Boolean
}

