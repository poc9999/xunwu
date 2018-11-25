package com.imooc.repositories;

import com.imooc.ApplicationTests;
import com.imooc.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/13 23:12
 * Description  :
 */
public class UserRepositoriesTest extends ApplicationTests {

    @Autowired
    UserRepositories userRepositories;

    @Test
    public void testFindOne(){
        User user = userRepositories.findOne(1L);
        Assert.assertEquals("waliwali",user.getName());
    }
}