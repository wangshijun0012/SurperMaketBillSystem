package cn.onesdream.service;

import cn.onesdream.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getAll(String userName,String userRole);
    public User getByCode(String userCode);


}
