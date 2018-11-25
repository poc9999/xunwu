package com.imooc.repositories;

import com.imooc.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/17 23:17
 * Description  : 角色数据DAO
 */
public interface RoleRepositories extends CrudRepository<Role,Long> {

    List<Role> findRolesByUserId(Long userId);
}
