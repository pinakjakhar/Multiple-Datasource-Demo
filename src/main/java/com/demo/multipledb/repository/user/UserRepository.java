package com.demo.multipledb.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.multipledb.dto.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

	
}
