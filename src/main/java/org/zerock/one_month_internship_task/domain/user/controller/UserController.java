package org.zerock.one_month_internship_task.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.one_month_internship_task.domain.user.dto.SignupRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignupResponse;
import org.zerock.one_month_internship_task.domain.user.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users/signup")
    public ResponseEntity<SignupResponse> signup(
            @RequestBody SignupRequest signupRequest
    ) {
        return new ResponseEntity<>(
                userService.signup(signupRequest),
                HttpStatus.CREATED
        );
    }
}
