package dev.helderlee.octo.persistence.module

import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.checkModules

class PersistenceModuleTest : KoinTest {

    @Test
    fun `Check Test - check all definitions dependencies`() {
        checkModules(listOf(persistenceModule))
    }
}
