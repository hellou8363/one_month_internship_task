package org.zerock.one_month_internship_task.domain.user.dto;

import org.zerock.one_month_internship_task.domain.user.dto.type.AuthoritiesType;

public record AuthoritiesResponse(
        AuthoritiesType authorityName
) {
}
