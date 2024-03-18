package com.jimmyss.slabel.service.serviceImpl;

import com.jimmyss.slabel.component.BaseResponse;
import com.jimmyss.slabel.component.TokenParser;
import com.jimmyss.slabel.entity.User;
import com.jimmyss.slabel.repository.UserRepository;
import com.jimmyss.slabel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.jimmyss.slabel.util.JwtToken;
import java.util.LinkedHashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public BaseResponse<String> loginService(String username, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findUserByUsername(username);

        if(user == null || !encoder.matches(password, user.getPassword())){
            return BaseResponse.error("用户名或密码错误");
        }

        var map = new LinkedHashMap<String, String>();
        map.put("id", String.valueOf(user.getId()));
        map.put("username", user.getUsername());
        String token = JwtToken.createJwtToken(map);

        return BaseResponse.success("登录成功", token);
    }

    public BaseResponse<TokenParser> registerService(String username, String password, String confirmPassword){
        if(username==null){
            return BaseResponse.error("用户名不能为空");
        }
        if(password==null){
            return BaseResponse.error("密码不能为空");
        }
        if(confirmPassword==null){
            return BaseResponse.error("验证密码不能为空");
        }
        if(!password.equals(confirmPassword)){
            return BaseResponse.error("两次密码不一致");
        }
        User user=userRepository.findUserByUsername(username);
        //如果用户名重复
        if(user!=null){
            return BaseResponse.error("用户名重复，换一个吧");
        }

        //未被注册
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String cryptPassword = passwordEncoder.encode(password);
        User newUser=new User(username, cryptPassword);
        userRepository.save(newUser);

        var map = new LinkedHashMap<String, String>();
        map.put("id", String.valueOf(newUser.getId()));
        map.put("username", newUser.getUsername());
        String token = JwtToken.createJwtToken(map);

        TokenParser tokenParser=new TokenParser(token);
        System.out.println("calling register service");
        return BaseResponse.success("注册成功", tokenParser);
    }

    public BaseResponse<String> logoutService(){
        return BaseResponse.success(null);
    }
}
