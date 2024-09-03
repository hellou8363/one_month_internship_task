package org.zerock.one_month_internship_task.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.zerock.one_month_internship_task.domain.user.dto.type.AuthoritiesType;
import org.zerock.one_month_internship_task.infra.security.jwt.JwtHelper;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class JwtTests {

    @Autowired
    private JwtHelper jwtHelper;


    @Test
    void generateTokenTest() {
        String token = jwtHelper.generateAccessToken(
                "TestUser",
                AuthoritiesType.ROLE_USER
        );

        assertNotNull("token is null", token);
        assertTrue("fail validate token", jwtHelper.validateToken(token));
    }
}
