package com.demo.multipledb.repository.user2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.multipledb.dto.user2.User2;

@Repository
public interface UserRepository2 extends JpaRepository<User2, Long> {
	
	


	
}
