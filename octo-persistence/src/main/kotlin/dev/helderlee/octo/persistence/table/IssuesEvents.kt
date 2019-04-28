package dev.helderlee.octo.persistence.table

import org.jetbrains.exposed.dao.UUIDTable

object IssuesEvents : UUIDTable("issues_events") {
    val issueId = reference("issue_id", Issues)
    val action = varchar("action", 16)
    val updatedAt = datetime("updated_at")
    val userLogin = varchar("user_login", 64)
}

