package dev.helderlee.octo.persistence.repository

import dev.helderlee.octo.persistence.entity.Issue
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class IssueRepositoryImpl : IssueRepository {

    override fun save(issue: dev.helderlee.octo.common.domain.github.Issue) = transaction {
        val entity = Issue.findById(issue.id)

        if (entity == null) {
            Issue.new(issue.id) {
                this.number = issue.number
                this.createdAt = DateTime(issue.created_at)
                if (issue.closed_at != null) {
                    closedAt = DateTime(issue.closed_at)
                }
            }.flush()
        } else {
            false
        }

    }

}
