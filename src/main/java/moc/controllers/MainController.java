package moc.controllers;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/9/17.
 */
@RestController
@Configuration
@Component
public class MainController {

    @GetMapping("/ddd")
    @ResponseBody
    public String zzz() {
        return "ddddd";
    }

    @GetMapping("/indexzz")
    @ResponseBody
    public String index() {
        return "ddddd";
    }
//
}
