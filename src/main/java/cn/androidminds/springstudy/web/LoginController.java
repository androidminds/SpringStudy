package cn.androidminds.springstudy.web;

import javax.servlet.http.HttpServletRequest;

import cn.androidminds.springstudy.domain.User;
import cn.androidminds.springstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;


@Controller
public class LoginController {
    private UserService userService;

    @RequestMapping(value = "/")
    public String loginPage(){return "login";}

    @RequestMapping(value = "/register.html")
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value = "/registerCheck.html")
    public ModelAndView registerCheck(HttpServletRequest request, LoginCommand loginCommand){
        boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(),
                loginCommand.getPassword());
        if (!isValidUser) {
            if(userService.addUser(loginCommand.getUserName(),loginCommand.getPassword())){
                return new ModelAndView("login");
            } else {
                return new ModelAndView("register", "error", "添加用户失败。");
            }
        } else {
            return new ModelAndView("register", "error", "用户名已经存在。");
        }
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
        boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(),
                loginCommand.getPassword());
        if (isValidUser) {
            User user = userService.findUserByUserName(loginCommand
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            request.getSession().setAttribute("user", user);
            userService.addLoginLog(user.getUserId(), user.getLastIp());
            return new ModelAndView("main");
        } else {
            return new ModelAndView("login", "error", "用户不存在。");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
