package com.jimmyss.slabel.service.serviceImpl;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.entity.User;
import com.jimmyss.slabel.repository.UserRepository;
import com.jimmyss.slabel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.LinkedHashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public BaseResponse<User> loginService(String username, String password){
        //bcrypt加密处理
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String ciphertext = encoder.encode(password);

        User user = userRepository.findUserByUsername(username);

        //没查到返回登录失败
        if(user == null) {
            return BaseResponse.error("找不到用户，请注册");
        }

        //密码错误登录失败
        if(!encoder.matches(password, user.getPassword())){
            return BaseResponse.error("密码错误");
        }

        // 密码正确token生成
        var map = new LinkedHashMap<String, String>();
        map.put("id", String.valueOf(user.getId()));
        map.put("username", user.getUsername());
//        String token = JwtToken.create(map);

//        map.put("token", token);
        return BaseResponse.success("登录成功", user);
    }

    public BaseResponse<User> registerService(String username, String password){
        User user=userRepository.findUserByUsername(username);

        //如果用户名重复
        if(user!=null){
            return BaseResponse.error("用户名重复，换一个吧");
        }

        //未被注册
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String cryptPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, cryptPassword));

        return BaseResponse.success("注册成功", user);
    }
}