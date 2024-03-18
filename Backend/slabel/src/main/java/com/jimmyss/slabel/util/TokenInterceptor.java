package com.jimmyss.slabel.util;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmyss.slabel.component.BaseResponse;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = request.getRequestURI();
        if (login.contains("/login")) {
            System.out.println("detect login");
            return true;
        }
        System.out.println("not login");
        // //获取请求头中的token
        final String token;
        final String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith("Bearer ")) {
            //            截取token
            token = authHeader.substring(7);
        } else {
            if (request.getHeader("token") != null) {
                token = request.getHeader("token");
            } else {
                token = request.getParameter("token");
            }
        }
        Map<String, Object> mp = new HashMap<>();
        try {
            if (token == null) {
                System.out.println("token空");
            }
            JwtToken.verifyJwtToken(token);//验证token
            return true;  //上一句无异常就 放行
        } catch (SignatureVerificationException e) {
//            e.printStackTrace();
            mp.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            mp.put("msg", "token过期，请重新登录");
        } catch (AlgorithmMismatchException e) {
//            e.printStackTrace();
            mp.put("msg", "算法不匹配");
        } catch (NullPointerException e) {
//            e.printStackTrace();
            mp.put("msg", "token不能为空");
        } catch(RuntimeException e){
            e.printStackTrace();
            mp.put("msg", "token不正确");
        } catch (Exception e) {
//            e.printStackTrace();
            mp.put("msg", "其他异常");
        }
        mp.put("state", false);
        //map转换为json
        String json = new ObjectMapper().writeValueAsString(mp);

        response.setContentType("application/json; charset=UTF-8");

        //返回
        response.getWriter().println(json);
        return false;

//        String token = request.getHeader("Authorization");
//        String msg;
//        ServletOutputStream os = response.getOutputStream();
//        String uri = request.getRequestURI();
//
//        try {
//            JwtToken.verifyJwtToken(token);
//            log.info("in token interceptor, this url is {}, valid", uri);
//            return true;
//        } catch (TokenExpiredException e) {
//            msg = "token is expired";
//            log.error("in token interceptor, this url is {}, expired", uri);
//            os.write(JSON.toJSONString(BaseResponse.error(msg)).getBytes());
//        } catch (SignatureVerificationException e) {
//            msg = "signature is invalid";
//            log.error("in token interceptor, this url is {}, invalid signature", uri);
//            os.write(JSON.toJSONString(BaseResponse.error(msg)).getBytes());
//        } catch (AlgorithmMismatchException e) {
//            msg = "wrong encrypt algorithm";
//            log.error("in token interceptor, this url is {}, wrong encrypt algorithm", uri);
//            os.write(JSON.toJSONString(BaseResponse.error(msg)).getBytes());
//        } catch (Exception e) {
//            msg = "invalid token";
//            log.error(token);
//            log.error("in token interceptor, this url is {}, invalid token", uri);
//            log.error(e.toString());
//            os.write(JSON.toJSONString(BaseResponse.error(msg)).getBytes());
//        }
//        return false;
    }
}
