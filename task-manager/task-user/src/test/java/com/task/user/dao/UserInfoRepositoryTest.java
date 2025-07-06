package com.task.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.user.entity.UserInfo;

@Repository
public interface UserInfoRepositoryTest extends JpaRepository<UserInfo, Long> {

}
