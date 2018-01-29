package com.demo.multipledb.controller.user2;

import com.demo.multipledb.dto.user2.User2;
import com.demo.multipledb.repository.user2.UserRepository2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user2")
public class UserController2 {
	
    @Autowired
    UserRepository2 users2repo;
    
    @RequestMapping(method = RequestMethod.POST)
    public String saveAll(@RequestBody User2 user2)
    {
   try {
    		users2repo.save(user2);
    		return "Successfully Saved";
       }
   catch(DataIntegrityViolationException e) {}
   return "Error: Name already exist";
    }
    			  
    @RequestMapping(method = RequestMethod.GET)
    public List<User2> getAll() {
             return users2repo.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
