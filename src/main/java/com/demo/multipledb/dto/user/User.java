package com.demo.multipledb.dto.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long user_id;
    
    @Column(name = "name")
    private String name;

    public User() {
    }
    
    public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
