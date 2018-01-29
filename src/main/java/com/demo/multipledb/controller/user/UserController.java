package com.demo.multipledb.controller.user;

import com.demo.multipledb.dto.user.User;
import com.demo.multipledb.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository usersrepo;
    
    @RequestMapping(method = RequestMethod.POST)
    public String saveAll(@RequestBody User user)
    {
   try {
	   usersrepo.save(user);
    		return "Successfully Saved";
       }
   catch(DataIntegrityViolationException e) {}
   return "Error: Name already exist";
    }
    			  
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
             return usersrepo.findAll(new Sort(Sort.Direction.ASC, "user_id"));
    }
}
