package dev.helderlee.octo.subscriber.service

import dev.helderlee.octo.common.domain.github.IssueEvent
import dev.helderlee.octo.persistence.repository.IssueEventRepository
import dev.helderlee.octo.persistence.repository.IssueRepository
import org.jetbrains.exposed.sql.transactions.transaction

class SubscriberServiceImpl(
    private val issueRepository: IssueRepository,
    private val issueEventRepository: IssueEventRepository
) : SubscriberService {

    override fun saveEvent(issueEvent: IssueEvent) = transaction {
        issueRepository.save(issueEvent.issue)
        issueEventRepository.save(issueEvent)
    }

}
