package cn.onesdream.service;
import cn.onesdream.dao.RoleMapper;
import cn.onesdream.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> getAll() {
        List<Role> roles = roleMapper.selectList(null);
        return roles;
    }
}
