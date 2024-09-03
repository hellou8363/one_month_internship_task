package org.zerock.one_month_internship_task.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.one_month_internship_task.domain.user.dto.SignRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignResponse;
import org.zerock.one_month_internship_task.domain.user.dto.SignupRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignupResponse;
import org.zerock.one_month_internship_task.domain.user.model.User;
import org.zerock.one_month_internship_task.domain.user.repository.UserRepository;
import org.zerock.one_month_internship_task.infra.security.jwt.JwtHelper;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;

    private UserService(
            UserRepository userRepository,
            JwtHelper jwtHelper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.passwordEncoder = passwordEncoder;
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        return userRepository.save(
                new User(
                        signupRequest.username(),
                        signupRequest.nickname(),
                        passwordEncoder.encode(signupRequest.password())
                )
        ).toResponse();
    }

    public SignResponse sign(SignRequest signRequest) {
        User user = userRepository
                .findByUsername(signRequest.username())
                .orElseThrow(() -> new IllegalArgumentException("회원정보를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(signRequest.password(), user.getPassword())) {
            throw new IllegalArgumentException("password가 일치하지 않습니다.");
        }

        return new SignResponse(
                jwtHelper.generateAccessToken(
                        user.getUsername(),
                        user.getAuthorityName()
                )
        );
    }
}
