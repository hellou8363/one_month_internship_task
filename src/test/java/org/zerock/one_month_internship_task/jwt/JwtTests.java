package org.zerock.one_month_internship_task.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.zerock.one_month_internship_task.domain.user.dto.type.AuthoritiesType;
import org.zerock.one_month_internship_task.infra.security.jwt.JwtHelper;

import static org.springframework.test.util.AssertionErrors.*;

@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@SpringBootTest
public class JwtTests {

    @Autowired
    private JwtHelper jwtHelper;


    @Test
    void generateAccessTokenTest() {
        String token = jwtHelper.generateAccessToken(
                "TestUser",
                AuthoritiesType.ROLE_USER
        );

        assertNotNull("token is null", token);
        assertTrue("fail validate token", jwtHelper.validateToken(token));
    }

    @Test
    void validateAccessTokenTest() throws InterruptedException {
        String token = jwtHelper.generateAccessToken(
                "TestUser",
                AuthoritiesType.ROLE_USER
        );

        assertNotNull("token is null", token);
        assertTrue("fail validate token", jwtHelper.validateToken(token));
        Thread.sleep(3000);
        assertFalse("fail validate token", jwtHelper.validateToken(token));
    }

    @Test
    void generateRefreshTokenTest() {
        String token = jwtHelper.generateRefreshToken(
                "TestUser",
                AuthoritiesType.ROLE_USER
        );

        assertNotNull("token is null", token);
        assertTrue("fail validate token", jwtHelper.validateToken(token));
    }

    @Test
    void validateRefreshTokenTest() throws InterruptedException {
        String token = jwtHelper.generateRefreshToken(
                "TestUser",
                AuthoritiesType.ROLE_USER
        );

        assertNotNull("token is null", token);
        assertTrue("fail validate token", jwtHelper.validateToken(token));
        Thread.sleep(3000);
        assertFalse("fail validate token", jwtHelper.validateToken(token));
    }
}
