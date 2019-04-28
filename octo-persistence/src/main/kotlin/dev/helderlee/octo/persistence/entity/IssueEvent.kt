package dev.helderlee.octo.persistence.entity

import dev.helderlee.octo.persistence.table.IssuesEvents
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import java.util.*

class IssueEvent(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<IssueEvent>(IssuesEvents)

    var action by IssuesEvents.action
    var issue by Issue referencedOn IssuesEvents.issueId
    var updatedAt by IssuesEvents.updatedAt
    var userLogin by IssuesEvents.userLogin

}
