package org.zerock.one_month_internship_task.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.one_month_internship_task.domain.user.dto.SignRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignResponse;
import org.zerock.one_month_internship_task.domain.user.dto.SignupRequest;
import org.zerock.one_month_internship_task.domain.user.dto.SignupResponse;
import org.zerock.one_month_internship_task.domain.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(
            @RequestBody SignupRequest signupRequest
    ) {
        return new ResponseEntity<>(
                userService.signup(signupRequest),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/sign")
    public ResponseEntity<SignResponse> sign(
            @RequestBody SignRequest signRequest
    ) {
        return new ResponseEntity<>(
                userService.sign(signRequest),
                HttpStatus.OK
        );
    }
}
