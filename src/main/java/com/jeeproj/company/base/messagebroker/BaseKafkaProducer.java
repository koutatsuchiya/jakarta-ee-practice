package com.jeeproj.company.base.messagebroker;

import com.jeeproj.company.base.config.KafkaConfig;
import com.jeeproj.company.base.messagebroker.serialization.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@RequiredArgsConstructor
public abstract class BaseKafkaProducer<T> {
    protected final Class<? extends ObjectSerializer<T>> objectSerializerClass;
    protected final String topic;

    protected Properties getProducerProperties() {
        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.SERVER);
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, objectSerializerClass.getName());
        producerProperties.setProperty(ProducerConfig.ACKS_CONFIG, KafkaConfig.ACK);
        return producerProperties;
    }

    protected abstract void sendMessage(Producer<String, T> producer, ProducerRecord<String, T> record);

    public void produceMessageToTopic(T t) {
        KafkaProducer<String, T> producer = new KafkaProducer<>(getProducerProperties());
        ProducerRecord<String, T> record = new ProducerRecord<>(topic, t);
        sendMessage(producer, record);
        producer.flush();
        producer.close();
    }
}
