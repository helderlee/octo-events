package dev.helderlee.octo.persistence.repository

import dev.helderlee.octo.common.domain.response.Event
import dev.helderlee.octo.persistence.entity.Issue
import dev.helderlee.octo.persistence.entity.IssueEvent
import dev.helderlee.octo.persistence.table.Issues
import dev.helderlee.octo.persistence.table.IssuesEvents
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class IssueEventRepositoryImpl : IssueEventRepository {

    override fun save(issueEvent: dev.helderlee.octo.common.domain.github.IssueEvent) = transaction {
        val issue = Issue.findById(issueEvent.issue.id)

        IssueEvent.new {
            this.action = issueEvent.action
            this.issue = issue!!
            this.updatedAt = DateTime(issueEvent.issue.updated_at)
            this.userLogin = issueEvent.issue.user.login
        }.flush()
    }

    override fun list(issueNumber: Int) = transaction {
        (IssuesEvents innerJoin Issues).select { Issues.number eq issueNumber }.map {
            Event(
                it[IssuesEvents.action],
                it[Issues.createdAt].toString(),
                it[IssuesEvents.updatedAt].toString(),
                it[Issues.closedAt]?.toString(),
                it[IssuesEvents.userLogin]
            )
        }
    }

}
