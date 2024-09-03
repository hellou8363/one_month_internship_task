package org.zerock.one_month_internship_task.domain.user.dto;

import java.util.List;

public record SignupResponse(
        String username,
        String nickname,
        List<AuthoritiesResponse> authorities
) {
}
