package com.imooc.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/17 23:40
 * Description  :
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String USER_URL = "user";
    @GetMapping(value = "/login")
    public String userLoginPage(){
        return USER_URL+"/login";
    }

    @GetMapping(value = "/center")
    public String userCenterPage(){
        return USER_URL+"/center";
    }
}
