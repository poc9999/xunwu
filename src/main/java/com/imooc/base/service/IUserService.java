package com.imooc.base.service;

import com.imooc.entity.User;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/17 22:53
 * Description  : 用户服务
 */
public interface IUserService {
    User findUserByName(String userName);
}
