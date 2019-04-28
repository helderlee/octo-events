package dev.helderlee.octo.subscriber.route

import dev.helderlee.octo.subscriber.processor.IssueEventProcessor
import org.apache.camel.builder.RouteBuilder
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.stereotype.Component

@Component
class SubscriberRouteBuilder : RouteBuilder() {

    @Throws(Exception::class)
    override fun configure() {

        val deserializer = StringDeserializer::class.java.canonicalName

        from("kafka:{{kafka_topic}}"
                +"?brokers={{kafka_brokers}}"
                + "&groupId={{kafka_group_id}}"
                + "&autoCommitEnable={{kafka_auto_commit_enable}}"
                + "&autoCommitIntervalMs={{kafka_auto_commit_interval_ms}}"
                + "&autoOffsetReset={{kafka_auto_offset_reset}}"
                + "&sessionTimeoutMs={{kafka_session_timeout_ms}}"
                + "&keyDeserializer=$deserializer"
                + "&valueDeserializer=$deserializer"
        )
            .routeId("issueEventConsumer")
            .transacted()
            .process(IssueEventProcessor())
            .log("${body()}")

    }
}