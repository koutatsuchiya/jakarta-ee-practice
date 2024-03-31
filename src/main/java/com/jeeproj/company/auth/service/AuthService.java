package com.jeeproj.company.auth.service;

import com.jeeproj.company.auth.dto.LoginRequestDTO;
import com.jeeproj.company.auth.dto.LoginResponseDTO;
import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.base.security.payload.JwtPayload;
import com.jeeproj.company.base.security.tokenprovider.TokenProvider;
import com.jeeproj.company.user.dao.UserDAO;
import com.jeeproj.company.user.entity.User;
import com.jeeproj.company.user.service.mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthService {
    @Inject
    private UserDAO userDAO;

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private UserMapper userMapper;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws BadRequestException {
        User user = validateCredential(loginRequestDTO);
        String accessToken = generateJWT(user);

        return new LoginResponseDTO(accessToken, userMapper.toUserDTO(user));
    }

    public User validateCredential(LoginRequestDTO loginRequestDTO) throws BadRequestException {
        User user = userDAO.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() ->
                new BadRequestException(AppMessage.INVALID_EMAIL_OR_PASSWORD));
        boolean isValidPassword = BCrypt.checkpw(loginRequestDTO.getPassword(), user.getPassword());
        if (!isValidPassword) {
            throw new BadRequestException(AppMessage.INVALID_EMAIL_OR_PASSWORD);
        }
        return user;
    }

    public String generateJWT(User user) {
        JwtPayload payload = new JwtPayload(user.getEmail(), user.getRole());
        return tokenProvider.generateToken(payload.toMap());
    }
}
