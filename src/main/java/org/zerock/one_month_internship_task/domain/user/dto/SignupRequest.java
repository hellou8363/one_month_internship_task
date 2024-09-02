package org.zerock.one_month_internship_task.domain.user.dto;

public record SignupRequest(
        String username,
        String password,
        String nickname
) {
}
