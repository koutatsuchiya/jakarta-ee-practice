package com.jeeproj.company.base.messagebroker;

import com.jeeproj.company.base.config.KafkaConfig;
import com.jeeproj.company.base.messagebroker.serialization.ObjectDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

@RequiredArgsConstructor
public abstract class BaseKafkaConsumer<T> {
    protected final Class<? extends ObjectDeserializer<T>> objectDeserializerClass;
    protected final String topic;
    protected final String groupId;

    protected Properties getConsumerProperties() {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.SERVER);
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, objectDeserializerClass.getName());
        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConfig.AUTO_OFFSET_RESET_CONFIG);
        return consumerProperties;
    }

    protected abstract void consumeMessage(KafkaConsumer<String, T> consumer);

    public void consumeMessageFromTopic() {
        KafkaConsumer<String, T> consumer = new KafkaConsumer<>(getConsumerProperties());
        consumer.subscribe(Collections.singleton(topic));
        consumeMessage(consumer);
    }
}
