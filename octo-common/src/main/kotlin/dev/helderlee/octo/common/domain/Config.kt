package dev.helderlee.octo.common.domain

import org.apache.kafka.common.serialization.StringSerializer
import org.koin.core.KoinProperties
import org.koin.standalone.KoinComponent
import org.koin.standalone.getProperty
import java.util.*

class Config : KoinComponent {

    fun loadKoinProperties() = when (System.getenv("ENV")) {
        "HML", "PRD" -> KoinProperties(useEnvironmentProperties = true)
        else -> KoinProperties(useKoinPropertiesFile = true)
    }

    fun kafkaProperties() : Properties {
        val props = Properties()

        val serializer = StringSerializer::class.java.canonicalName

        props["bootstrap.servers"] = getProperty("kafka_brokers")
        props["key.serializer"] = serializer
        props["value.serializer"] = serializer

        return props
    }

}