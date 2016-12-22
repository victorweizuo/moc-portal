package moc.controllers;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.services.UserService;
import moc.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.rmi.Remote;

/**
 * Created by Administrator on 2016/10/22.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService =new UserServiceImpl();
    @RequestMapping(method= RequestMethod.POST, path="/validate")
    public String login(@RequestParam (value="username") String username,
                            @RequestParam (value="password") String password,HttpSession httpSession){
        log.info("User "+username+" is trying to login in with password "+password);
        JsonObject loginResult= userService.validate(username,password);
        if(loginResult!=null){
            String appid=loginResult.getAsJsonObject("response_body").get("appid").getAsString();
            String appsecret=loginResult.getAsJsonObject("response_body").get("appsecret").getAsString();
            httpSession.setAttribute("appid",appid);
            httpSession.setAttribute("appsecret",appsecret);
            httpSession.setAttribute("username",username);
            String token=RemoteConnector.getInstance().requestJsonObject(RequestModel.getToken(appid,appsecret)).getAsJsonObject("response_body").get("generaltoken").getAsString();
            httpSession.setAttribute("token",token);
            return "Success";
        }
        else {
            return "Invalidate login profile";
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getusername")
    public String getUserName(HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            String result = (String) httpSession.getAttribute("username");
            return result;
        }
    }

    @RequestMapping(method= RequestMethod.POST, path="/changepassword")

    public String changePassword(@RequestParam (value="password") String password,
                        @RequestParam (value="passwordreset") String passwordreset,HttpSession httpSession){
        String username= (String) httpSession.getAttribute("username");
        String message=userService.changePassword(username,password,passwordreset);
        return message;
    }
}
