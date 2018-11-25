package com.imooc.base.service.house;

import com.imooc.ApplicationTests;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/18 21:48
 * Description  :
 */
public class QiNiuServiceImplTest extends ApplicationTests {
    @Autowired
    private IQiNiuService qiNiuService;
    @Test
    public void uploadFile() throws Exception {
        String fileName = "E:\\WorkSpace\\xunwu\\temp\\a.jpg";
        File file = new File(fileName);
        Assert.assertTrue(file.exists());
        Response response = qiNiuService.uploadFile(file);
        Assert.assertTrue(response.isOK());
    }

    @Test
    public void deleteFile(){
        String hashKey = "Ftha4Efei0Plf6nOO-0KmTwhPZj-";

        try {
            Response response = qiNiuService.delete(hashKey);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }

    }

}