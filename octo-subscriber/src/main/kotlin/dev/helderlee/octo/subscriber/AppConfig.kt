package dev.helderlee.octo.subscriber

import dev.helderlee.octo.persistence.table.Issues
import dev.helderlee.octo.persistence.table.IssuesEvents
import dev.helderlee.octo.subscriber.module.subscriberModule
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinProperties
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.getProperty

class AppConfig : KoinComponent {

    fun config() {
        StandAloneContext.stopKoin()
        StandAloneContext.startKoin(listOf(subscriberModule), properties = loadKoinProperties())

        Database.connect(
            url = getProperty("octo.database.url"),
            driver = getProperty("octo.database.driver"),
            user = getProperty("octo.database.username"),
            password = getProperty("octo.database.password")
        )

        transaction {
            SchemaUtils.create(Issues, IssuesEvents)
        }

    }

    private fun loadKoinProperties() = when (System.getenv("ENV")) {
        "hml", "prd" -> KoinProperties(useEnvironmentProperties = true)
        else -> KoinProperties(useKoinPropertiesFile = true)
    }

}
