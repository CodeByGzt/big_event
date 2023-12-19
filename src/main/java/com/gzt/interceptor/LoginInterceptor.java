package com.gzt.interceptor;

import com.gzt.enums.AppHttpCodeEnum;
import com.gzt.exception.SystemException;
import com.gzt.util.JwtUtil;
import com.gzt.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * ClassName: LoginInterceptor
 * Package: com.gzt.interceptor
 * Description: 对请求验证
 *
 * @Author gzt
 * @Create 12/10/2023 7:55 PM
 * @Version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取令牌
        String token = request.getHeader("Authorization");
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        if (!StringUtils.hasText(operations.get(token))){
            // redis里没有说明需要重新登录
            response.setStatus(401);
            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 放入ThreadLocal
            ThreadLocalUtil.set(claims);
            // 放行
            return true;
        }catch (Exception e){
            // http响应码设为401 不放行
            response.setStatus(401);
            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal 中的数据
        ThreadLocalUtil.remove();
    }
}
