package org.zerock.one_month_internship_task.domain.user.model;

import jakarta.persistence.*;
import org.zerock.one_month_internship_task.domain.user.dto.AuthoritiesResponse;
import org.zerock.one_month_internship_task.domain.user.dto.SignupResponse;
import org.zerock.one_month_internship_task.domain.user.dto.type.AuthoritiesType;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String username;
    private String nickname;
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthoritiesType authorityName = AuthoritiesType.ROLE_USER;


    public SignupResponse toResponse() {
        return new SignupResponse(
                username,
                nickname,
                List.of(new AuthoritiesResponse(authorityName))
        );
    }
}
