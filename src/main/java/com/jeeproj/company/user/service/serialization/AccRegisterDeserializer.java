package com.jeeproj.company.user.service.serialization;

import com.jeeproj.company.base.messagebroker.serialization.BaseDeserializer;
import com.jeeproj.company.user.dto.AccRegisterDTO;

public class AccRegisterDeserializer extends BaseDeserializer<AccRegisterDTO> {
    public AccRegisterDeserializer() {
        super(AccRegisterDTO.class);
    }
}
