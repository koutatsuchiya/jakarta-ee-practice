package com.jeeproj.company.base.messagebroker.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class ObjectSerializer implements Serializer<Object> {
    @Override
    @SneakyThrows
    public byte[] serialize(String s, Object o) {
        byte[] bytes;
        ObjectMapper objMapper = new ObjectMapper();
        System.out.println("Serialized: " + o);
        bytes = objMapper.writeValueAsString(o).getBytes();
        return bytes;
    }
}
