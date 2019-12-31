package cn.onesdream.controller;

import cn.onesdream.pojo.Role;
import cn.onesdream.pojo.User;
import cn.onesdream.service.RoleService;
import cn.onesdream.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @RequestMapping("/doLogin")
    public String doLogin(String userCode, String password, HttpSession session) {
        User user2 = userService.getByCode(userCode);
        if (user2 != null && password.equals(user2.getUserPassword())) {
            session.setAttribute("loginUserName", user2.getUserCode());
            return "/frame";
        }
        session.setAttribute("error", "用户名或密码错误！");
        return "/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request) {
        String userName = request.getParameter("queryname");
        String userRole = request.getParameter("queryUserRole");
        List<User> users = userService.getAll(userName, userRole);
        List<Role> roles = roleService.getAll();
        session.setAttribute("queryUserName", userName);
        session.setAttribute("queryUserRole", userRole);
        session.setAttribute("userList", users);
        session.setAttribute("roleList",roles);
        return "/user/userlist";
    }

}
