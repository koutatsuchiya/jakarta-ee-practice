package com.jeeproj.company.user.service.serialization;

import com.jeeproj.company.base.messagebroker.serialization.ObjectDeserializer;
import com.jeeproj.company.user.dto.AccRegisterDTO;

public class AccRegisterDeserializer extends ObjectDeserializer<AccRegisterDTO> {
    public AccRegisterDeserializer() {
        super(AccRegisterDTO.class);
    }
}
