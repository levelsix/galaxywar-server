package com.lvl6.galaxywars.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvl6.galaxywars.po.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUdid(String udid);
	
}
