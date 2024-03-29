package com.jimmyss.slabel.repository;

import com.jimmyss.slabel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    Optional<User> findUserById(Integer id);

}
