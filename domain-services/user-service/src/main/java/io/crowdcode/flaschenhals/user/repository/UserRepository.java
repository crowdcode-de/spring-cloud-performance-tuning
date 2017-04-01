package io.crowdcode.flaschenhals.user.repository;

import io.crowdcode.flaschenhals.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
