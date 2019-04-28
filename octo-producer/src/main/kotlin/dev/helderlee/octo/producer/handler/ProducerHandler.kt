package dev.helderlee.octo.producer.handler

import dev.helderlee.octo.common.domain.Config
import io.javalin.Context
import org.apache.commons.io.IOUtils
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener
import org.koin.standalone.KoinComponent
import org.koin.standalone.getProperty
import org.slf4j.LoggerFactory
import java.io.InputStreamReader


object ProducerHandler : KoinComponent {

    val logger = LoggerFactory.getLogger(ProducerHandler::class.java)

    private fun createProducer(): Producer<String, String> {
        return KafkaProducer<String, String>(Config().kafkaProperties())
    }

    fun requestSaveEvent(ctx: Context) {
        val topic: String = getProperty("kafka_topic")
        val body = ctx.body()
        try {
            val jsonSubject = JSONObject(body)
            val jsonSchema = JSONObject(
                JSONTokener(
                    IOUtils.toString(
                        InputStreamReader(ProducerHandler::class.java.getResourceAsStream("/issue-event-schema.json"))
                    )
                )
            )
            val schema = SchemaLoader.load(jsonSchema)
            schema.validate(jsonSubject)

            val producer = createProducer()
            producer.send(ProducerRecord(topic, body),
                fun(metadata: RecordMetadata, exception: java.lang.Exception?) {
                    if (exception == null) {
                        logger.info("Offset: ${metadata.offset()}")
                    } else {
                        logger.error("Error: ${exception.message}")
                    }
                })
            producer.flush()
            producer.close()
            ctx.status(200).json(mapOf("message" to "payload loaded"))
        } catch (e: Exception) {
            logger.error(e.message)
            ctx.status(406).json(mapOf("message" to "payload not loaded"))
        }
    }

}
