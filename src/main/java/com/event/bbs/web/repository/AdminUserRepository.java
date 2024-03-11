package com.event.bbs.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.bbs.web.entity.AdminUser;


public interface AdminUserRepository  extends JpaRepository<AdminUser,Integer> {

	Optional<AdminUser> findByLoginId(String loginId);

}
