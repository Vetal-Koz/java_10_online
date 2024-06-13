package org.example.final_server.repository.user;

import org.example.final_server.entity.user.User;
import org.example.final_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
