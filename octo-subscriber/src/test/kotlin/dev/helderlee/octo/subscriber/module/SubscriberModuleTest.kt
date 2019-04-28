package dev.helderlee.octo.subscriber.module

import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.koin.test.checkModules

class SubscriberModuleTest : KoinTest {

    @Test
    fun `Check Test - check all definitions dependencies`() {
        checkModules(listOf(subscriberModule))
    }
}
