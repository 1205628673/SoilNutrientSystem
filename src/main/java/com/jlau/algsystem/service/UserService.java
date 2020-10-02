package com.jlau.algsystem.service;

import com.jlau.algsystem.entity.Role;
import com.jlau.algsystem.entity.User;
import com.jlau.algsystem.repository.UserRepository;
import com.jlau.algsystem.utils.CodeUtil;
import com.jlau.algsystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cxr1205628673 on 2020/5/1.
 */
@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    public Result findAll(){
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),userRepository.findAll());
    }
    public Result findAll(Integer page) {
        Pageable pageable = PageRequest.of(page,20);
        Page<User> users = userRepository.findAll(pageable);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),users);
    }
    public Result findUserById(Integer id) {
        User user = userRepository.getOne(id);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),user);
    }
    public Result save(User user) throws Exception{
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        for (Role role:user.getRoles()) {
            String authName = role.getName();
            if(authName != null && !"".equals(authName) && !authName.startsWith("ROLE")){
                throw new Exception("authentication must star with ROLE");
            }
        }
        if(user.getId() == null && userRepository.findByusername(user.getUsername()) != null){
            throw new Exception("已经存在该用户名");
        }else{
            userRepository.save(user);
        }
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"ok");
    }

    public Result deleteById(Integer id) {
        userRepository.deleteById(id);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"ok");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username);
        if(user == null) {
            throw new UsernameNotFoundException("没有该账号");
        }
        return user;
    }

}
