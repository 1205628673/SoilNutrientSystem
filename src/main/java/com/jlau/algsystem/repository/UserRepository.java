package com.jlau.algsystem.repository;

import com.jlau.algsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cxr1205628673 on 2020/5/1.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByusername(String username);
}
