package com.jeeproj.company.base.messagebroker.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;

@RequiredArgsConstructor
public abstract class ObjectDeserializer<T> implements Deserializer<T> {
    protected final Class<T> classType;

    @Override
    public T deserialize(String s, byte[] bytes) {
        ObjectMapper objMapper = new ObjectMapper();
        T t;
        try {
            t = objMapper.readValue(bytes, classType);
        } catch (Exception e) {
            t = null;
        }
        return t;
    }
}
