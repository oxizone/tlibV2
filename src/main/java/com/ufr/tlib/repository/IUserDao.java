package com.ufr.tlib.repository;

import com.ufr.tlib.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {

    User getUserByUsername(String userName);
}
