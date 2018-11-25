package com.imooc.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/17 23:44
 * Description  : 基于角色的登录控制器
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private final Map<String,String> authMap;

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authMap = new HashMap<>();
        //普通用户登录
        authMap.put("/user/**","/user/login");
        //管理员登录
        authMap.put("/admin/**","/admin/login");
    }

    /**
     * 工具请求指定跳转到指定的页面
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String uri = request.getRequestURI().replace(request.getContextPath(),"");

        for (Map.Entry<String, String> authEntry : this.authMap.entrySet()) {
            if(this.pathMatcher.match(authEntry.getKey(),uri)){
                return authEntry.getValue();
            }
        }
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
