package org.zerock.one_month_internship_task.domain.user.service;

import org.springframework.stereotype.Service;
import org.zerock.one_month_internship_task.domain.user.dto.SignupRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignupResponse;
import org.zerock.one_month_internship_task.domain.user.model.User;
import org.zerock.one_month_internship_task.domain.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public SignupResponse signup(SignupRequest signupRequest) {
        return userRepository.save(
                new User(
                        signupRequest.username(),
                        signupRequest.nickname(),
                        signupRequest.password()
                )
        ).toResponse();
    }
}
