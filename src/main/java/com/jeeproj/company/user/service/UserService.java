package com.jeeproj.company.user.service;

import com.jeeproj.company.base.config.KafkaConfig;
import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.base.messagebroker.KafkaMessageBroker;
import com.jeeproj.company.user.dao.UserDAO;
import com.jeeproj.company.user.dto.AccRegisterDTO;
import com.jeeproj.company.user.dto.SignUpDTO;
import com.jeeproj.company.user.dto.UserDTO;
import com.jeeproj.company.user.entity.User;
import com.jeeproj.company.user.service.mapper.UserMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    @Inject
    private UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userMapper.toUserDTOs(userDAO.findAll());
    }

    @Transactional(rollbackOn = {
            RuntimeException.class
    })
    public UserDTO signUpUser(SignUpDTO signUpDTO) throws BadRequestException {
        if (userDAO.findByEmail(signUpDTO.getEmail()).isPresent()) {
            throw new BadRequestException(AppMessage.USER_EXIST);
        }
        User user = userMapper.toUser(signUpDTO);
        user.setPassword(BCrypt.hashpw(signUpDTO.getPassword(), BCrypt.gensalt()));
        userDAO.add(user);

        AccRegisterDTO accRegisterDTO = userMapper.toAccRegisterDTO(user);
        KafkaProducer<String, Object> producer = new KafkaProducer<>(KafkaMessageBroker.getProducerProperties());
        ProducerRecord<String, Object> record = new ProducerRecord<>(KafkaConfig.TOPIC, accRegisterDTO);
        KafkaMessageBroker.sendMessage(record, producer);
        producer.flush();
        producer.close();

        return userMapper.toUserDTO(user);
    }
}
