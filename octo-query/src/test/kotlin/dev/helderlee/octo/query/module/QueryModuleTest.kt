package dev.helderlee.octo.query.module

import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.checkModules

class QueryModuleTest : KoinTest {

    @Test
    fun `Check Test - check all definitions dependencies`() {
        checkModules(listOf(queryModule))
    }
}
