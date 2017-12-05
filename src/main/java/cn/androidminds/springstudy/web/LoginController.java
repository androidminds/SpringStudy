package cn.androidminds.springstudy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.androidminds.springstudy.domain.User;
import cn.androidminds.springstudy.service.LocaleMessageSourceService;
import cn.androidminds.springstudy.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.*;
import java.util.Date;
import java.util.Locale;


@Controller
public class LoginController {
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LocaleMessageSourceService localeMessageSource;

    @RequestMapping(value = "/")
    public String loginPage(){
        Locale locale = LocaleContextHolder.getLocale();
        logger.info("loginPage: " + locale.getLanguage());
        return "login";
    }

    @RequestMapping(value = "/upload_file")
    public @ResponseBody String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                return localeMessageSource.getMessage("upload_fail")+e.getMessage();
            } catch (IOException e) {
                return localeMessageSource.getMessage("upload_fail")+e.getMessage();
            }
            return localeMessageSource.getMessage("upload_success");
        }else{
            return localeMessageSource.getMessage("upload_fail") + localeMessageSource.getMessage("file_is_null");
        }
    }

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
                return new ModelAndView("register", "error",
                        localeMessageSource.getMessage("err_add_user_fail"));
            }
        } else {
            return new ModelAndView("register", "error",
                    localeMessageSource.getMessage("err_user_existed"));
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
            Locale locale = LocaleContextHolder.getLocale();
            logger.info(locale.toString());
            return new ModelAndView("login", "error",
                    localeMessageSource.getMessage("user_not_exist"));
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request, HttpServletResponse response, String lang){
        logger.info(lang);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        }else if("en".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "redirect:/";
    }
}
