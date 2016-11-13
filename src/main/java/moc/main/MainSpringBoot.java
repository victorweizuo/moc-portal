package moc.main;

import moc.controllers.SessionIntercepter;
import moc.redis.RedisManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2016/9/17.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "moc.controllers")
public class MainSpringBoot extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        RedisManager.getServer().start();
        SpringApplication.run(MainSpringBoot.class, args);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionIntercepter()).addPathPatterns("/device/**");
    }
}
