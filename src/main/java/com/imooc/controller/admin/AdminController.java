package com.imooc.controller.admin;

import com.imooc.base.Result;
import com.imooc.base.XunWuEnum.ResultEnum;
import com.imooc.base.service.house.IQiNiuService;
import com.qiniu.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/17 22:22
 * Description  :
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final static String ADMIN_URL = "admin";

    @Autowired
    private IQiNiuService iQiNiuService;

    @GetMapping(value = "/welcome")
    public String getWelcomePage(){
        return ADMIN_URL+"/welcome";
    }

    @GetMapping(value = "/center")
    public String getCenterPage(){
        return ADMIN_URL+"/center";
    }
    @GetMapping(value = "/login")
    public String getLoginPage(){
        return ADMIN_URL+"/login";
    }

    @GetMapping(value = "/add/house")
    public String addHousePage(){
        return ADMIN_URL+"/house-add";
    }

    @PostMapping(value = "/upload/photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile multipartFile){

        if(multipartFile.isEmpty()){
            return Result.fail(ResultEnum.EXCEPTION);
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Response response = iQiNiuService.uploadSteam(inputStream);
            if(response.isOK()){
                return Result.success(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(ResultEnum.EXCEPTION);
        }
        return Result.success(null);
    }

}
