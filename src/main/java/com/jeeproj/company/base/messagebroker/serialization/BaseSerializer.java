package com.jeeproj.company.base.messagebroker.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public abstract class BaseSerializer<T> implements Serializer<T> {
    @Override
    @SneakyThrows
    public byte[] serialize(String s, T t) {
        byte[] bytes;
        ObjectMapper objMapper = new ObjectMapper();
        System.out.println("Serialized: " + t);
        bytes = objMapper.writeValueAsString(t).getBytes();
        return bytes;
    }
}
