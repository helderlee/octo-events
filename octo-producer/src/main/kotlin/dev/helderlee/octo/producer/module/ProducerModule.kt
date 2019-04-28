package dev.helderlee.octo.producer.module

import dev.helderlee.octo.producer.endpoint.ProducerEndpointGroup
import org.koin.dsl.module.module

val producerModule = module {
    single { ProducerEndpointGroup() }
}
