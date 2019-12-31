package cn.onesdream.service;

import cn.onesdream.dao.RoleMapper;
import cn.onesdream.dao.UserMapper;
import cn.onesdream.pojo.Role;
import cn.onesdream.pojo.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WangShijun
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<User> getAll(String userName,String userRole) {
        EntityWrapper wrapper = new EntityWrapper();
        if (userName != null && !"".equals(userName) ){
            wrapper.like("userName", userName);
        }if (userRole != null && !"0".equals(userRole)){
            wrapper.eq("userRole", Long.parseLong(userRole));
        }

        List<User> users = userMapper.selectList(wrapper);
        List<Role> roles = roleMapper.selectList(null);
        for(User user: users){
            for(Role role : roles){
                if(user.getUserRole().equals(role.getId())){
                    user.setRoleName(role.getRoleName());
                    user.setRole(role);
                    break;
                }
            }
        }
        return users;
    }

    @Override
    public User getByCode(String userCode) {
        User su = new User();
        su.setUserCode(userCode);
        return userMapper.selectOne(su);
    }



}

