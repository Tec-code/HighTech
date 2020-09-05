package com.ht.controller;

import com.ht.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getUserNameById(@PathVariable int userId) {
        return userService.getUserById(userId).getUserName();
    }
}
