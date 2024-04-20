package com.jeeproj.company.base.messagebroker.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeeproj.company.user.dto.AccRegisterDTO;
import org.apache.kafka.common.serialization.Deserializer;

public class ObjectDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper objMapper = new ObjectMapper();
        Object o;
        try {
            o = objMapper.readValue(bytes, Object.class);
        } catch (Exception e) {
            o = null;
        }
        return o;
    }
}
