package dev.helderlee.octo.query

import dev.helderlee.octo.common.domain.Config
import dev.helderlee.octo.query.module.queryModule
import org.jetbrains.exposed.sql.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.getProperty

class AppConfig : KoinComponent {

    fun config() {
        StandAloneContext.stopKoin()
        StandAloneContext.startKoin(listOf(queryModule), properties = Config().loadKoinProperties())

        Database.connect(
            url = getProperty("octo.database.url"),
            driver = getProperty("octo.database.driver"),
            user = getProperty("octo.database.username"),
            password = getProperty("octo.database.password")
        )
    }

}
