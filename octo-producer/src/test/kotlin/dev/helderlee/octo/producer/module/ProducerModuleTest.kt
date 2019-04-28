package dev.helderlee.octo.producer.module

import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.checkModules

class ProducerModuleTest : KoinTest {

    @Test
    fun `Check Test - check all definitions dependencies`() {
        checkModules(listOf(producerModule))
    }
}
