package dev.helderlee.octo.persistence.table

import org.jetbrains.exposed.dao.IdTable

object Issues : IdTable<Long>("issues") {
    override val id = long("id").primaryKey().entityId()
    val number = integer("number")
    val createdAt = datetime("created_at")
    val closedAt = datetime("closed_at").nullable()
}
