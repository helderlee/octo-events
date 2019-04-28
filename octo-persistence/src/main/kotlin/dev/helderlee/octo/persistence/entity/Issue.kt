package dev.helderlee.octo.persistence.entity

import dev.helderlee.octo.persistence.table.Issues
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class Issue(id: EntityID<Long>) : LongEntity(id) {

    companion object : LongEntityClass<Issue>(Issues)

    var number by Issues.number
    var createdAt by Issues.createdAt
    var closedAt by Issues.closedAt

}
