package com.imooc.repositories;

import com.imooc.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/13 23:10
 * Description  :
 */
public interface UserRepositories extends CrudRepository<User,Long> {

    User findByName(String userName);
}
