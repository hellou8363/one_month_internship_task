package org.zerock.one_month_internship_task.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.one_month_internship_task.domain.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
