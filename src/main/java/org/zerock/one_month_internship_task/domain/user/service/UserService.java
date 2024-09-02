package org.zerock.one_month_internship_task.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.one_month_internship_task.domain.user.dto.SignupRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignupResponse;
import org.zerock.one_month_internship_task.domain.user.model.User;
import org.zerock.one_month_internship_task.domain.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
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
}
