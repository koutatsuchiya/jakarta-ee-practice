package com.jeeproj.company.user.service.broker;

import com.jeeproj.company.base.messagebroker.BaseKafkaProducer;
import com.jeeproj.company.user.dto.AccRegisterDTO;
import com.jeeproj.company.user.service.serialization.AccRegisterSerializer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.ejb.Singleton;

@Singleton
public class AccRegisterProducer extends BaseKafkaProducer<AccRegisterDTO> {
    public AccRegisterProducer() {
        super(AccRegisterSerializer.class, "confirm-acc-reg-topic");
    }

    @Override
    protected void sendMessage(Producer<String, AccRegisterDTO> producer,
                               ProducerRecord<String, AccRegisterDTO> record) {
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                System.out.println("Send message \n" +
                        "Topic: " + recordMetadata.topic() + "\n" +
                        "Partition: " + recordMetadata.partition() + "\n" +
                        "Offset: " + recordMetadata.offset() + "\n" +
                        "Timestamp: " + recordMetadata.timestamp());
            } else {
                // Handle exception
                System.out.println(e.getMessage());
            }
        });
    }
}
