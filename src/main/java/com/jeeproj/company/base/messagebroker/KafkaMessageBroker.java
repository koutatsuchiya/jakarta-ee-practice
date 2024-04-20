package com.jeeproj.company.base.messagebroker;

import com.jeeproj.company.base.config.KafkaConfig;
import com.jeeproj.company.base.messagebroker.serialization.ObjectDeserializer;
import com.jeeproj.company.base.messagebroker.serialization.ObjectSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaMessageBroker {
    public static Properties getProducerProperties() {
        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.SERVER);
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ObjectSerializer.class.getName());
        producerProperties.setProperty(ProducerConfig.ACKS_CONFIG, KafkaConfig.ACK);
        return producerProperties;
    }

    public static Properties getConsumerProperties() {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.SERVER);
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfig.GROUP_ID);
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConfig.AUTO_OFFSET_RESET_CONFIG);
        return consumerProperties;
    }

    public static void sendMessage(ProducerRecord<String, Object> record,
                                   Producer<String, Object> producer) {
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                System.out.println("Send message \n" +
                        "Topic: " + recordMetadata.topic() + "\n" +
                        "Partition: " + recordMetadata.partition() + "\n" +
                        "Offset: " + recordMetadata.offset() + "\n" +
                        "Timestamp: " + recordMetadata.timestamp());
            } else {
                System.out.println(e.getMessage());
            }
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
