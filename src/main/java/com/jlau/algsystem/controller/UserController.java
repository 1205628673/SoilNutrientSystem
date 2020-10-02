package com.jlau.algsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlau.algsystem.entity.User;
import com.jlau.algsystem.service.UserService;
import com.jlau.algsystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cxr1205628673 on 2020/5/1.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/member/findall")
    public Object getAllUser(){
        Result result = userService.findAll();
        return result;
    }
    @GetMapping("/admin/member/find/page/{page}")
    public Object getAllUserByPage(@PathVariable Integer page){
        Result result = userService.findAll(page);
        return result;
    }
    @GetMapping("/user/member/find/{id}")
    public Object getUser(@PathVariable("id") Integer id){
        Result result = userService.findUserById(id);
        return result;
    }

    @PutMapping("/admin/member/save")
    public Object saveUser(@RequestBody User user) throws Exception{
        Result result = userService.save(user);
        return result;
    }

    @DeleteMapping("/admin/member/delete/{id}")
    public Object delteUser(@PathVariable Integer id) {
        Result result = userService.deleteById(id);
        return result;
    }
}
