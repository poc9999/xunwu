package com.imooc.base.service.user;

import com.imooc.base.service.IUserService;
import com.imooc.entity.Role;
import com.imooc.entity.User;
import com.imooc.repositories.RoleRepositories;
import com.imooc.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/17 22:54
 * Description  :
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private RoleRepositories roleRepositories;

    @Override
    public User findUserByName(String userName) {

        User user = userRepositories.findByName(userName);
        if(user == null){
            return null;
        }
        List<Role> roleList = roleRepositories.findRolesByUserId(user.getId());

        if(roleList == null || roleList.isEmpty()){
            throw new DisabledException("权限非法");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorities(authorities);
        return user;
    }
}
