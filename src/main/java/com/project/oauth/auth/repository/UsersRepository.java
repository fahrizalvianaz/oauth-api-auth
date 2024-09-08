package com.project.oauth.auth.repository;



import com.project.oauth.auth.model.Role;
import com.project.oauth.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

}
