package dev.helderlee.octo.persistence.repository

import dev.helderlee.octo.persistence.module.persistenceModule
import dev.helderlee.octo.persistence.table.Issues
import dev.helderlee.octo.persistence.table.IssuesEvents
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinProperties
import org.koin.standalone.StandAloneContext
import org.koin.standalone.getProperty
import org.koin.test.KoinTest

abstract class InitTest: KoinTest {

    init {

        StandAloneContext.stopKoin()

        StandAloneContext.startKoin(listOf(persistenceModule), KoinProperties(useKoinPropertiesFile = true))

        Database.connect(
            url = getProperty("octo.database.url"),
            driver = getProperty("octo.database.driver"),
            user = getProperty("octo.database.username"),
            password = getProperty("octo.database.password")
        )

        transaction {
            SchemaUtils.drop (Issues, IssuesEvents)
            SchemaUtils.create (Issues, IssuesEvents)
        }

    }

}