package io.crowdcode.flaschenlager.user.repository;

import io.crowdcode.flaschenlager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
