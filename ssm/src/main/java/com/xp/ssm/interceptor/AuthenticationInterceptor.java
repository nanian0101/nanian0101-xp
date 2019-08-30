package com.xp.ssm.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.xp.ssm.entity.User;
import com.xp.ssm.service.IUserService;
import com.xp.ssm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从http请求中取出token
        //String token = request.getHeader("token");
        String token = request.getParameter("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有LonginToken注释,有则跳过认证
        if(method.isAnnotationPresent(LoginToken.class)){
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if(loginToken.required()){
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if(method.isAnnotationPresent(CheckToken.class)){
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if(checkToken.required()){
                //执行认证
                if(token == null){
                    throw new RuntimeException("用户登录失效,请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId  = JWT.decode(token).getClaim("uid").asString();
                } catch (JWTDecodeException J) {
                    throw new RuntimeException("访问异常");
                }
                User user = userService.selectByUid(Integer.valueOf(userId));
                if(user == null){
                    throw new RuntimeException("用户不存在,请重新登录");
                }
                Boolean verify = JwtUtil.isVerify(token,user);
                if(!verify){
                    throw new RuntimeException("非法访问");
                }
                //将token与对应的用户信息保存在redis中
                return true;
            }
        }
        return false;
    }
}
