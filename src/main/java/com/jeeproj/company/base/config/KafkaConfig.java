package com.jeeproj.company.base.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaConfig {
    private static final ResourceBundle rb = ResourceBundle.getBundle("kafka");
    public static final String SERVER = rb.getString("kafka.server");
    public static final String ACK = rb.getString("kafka.ack");
    public static final String TOPIC = "confirm-acc-reg";
    public static final String GROUP_ID = "confirm-acc-reg";
    public static final String AUTO_OFFSET_RESET_CONFIG = "earliest";
}
