package com.jeeproj.company.base.utility;

import com.jeeproj.company.base.messagebroker.BaseKafkaConsumer;
import com.jeeproj.company.user.dto.AccRegisterDTO;
import com.jeeproj.company.user.service.serialization.AccRegisterDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.Duration;

@Startup
@Singleton
public class ConfirmAccRegistration extends BaseKafkaConsumer<AccRegisterDTO> {
    public ConfirmAccRegistration() {
        super(AccRegisterDeserializer.class, "confirm-acc-reg-topic", "confirm-acc-reg-group");
    }

    @PostConstruct
    public void initialize() {
        new Thread(this::consumeMessageFromTopic).start();
    }

    @Override
    protected void consumeMessage(KafkaConsumer<String, AccRegisterDTO> consumer) {
        while (true) {
            ConsumerRecords<String, AccRegisterDTO> records = consumer.poll(Duration.ofMillis(5000));
            records.forEach(record -> {
                System.out.println("----Receive message---- \n" +
                        "Consumer: " + this.getClass().getName() + "\n" +
                        "Topic: " + record.topic() + "\n" +
                        "Key: " + record.key() + "\n" +
                        "Value full name: " + record.value().getEmail() + "\n" +
                        "Partition: " + record.partition() + "\n" +
                        "Offset: " + record.offset() + "\n");
            });
        }
    }
}
