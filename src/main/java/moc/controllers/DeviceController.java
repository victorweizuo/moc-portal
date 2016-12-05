package moc.controllers;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import moc.redis.RedisManager;
import moc.services.DeviceService;
import moc.services.impl.DeviceServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/11/6.
 */
@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceController {
    DeviceService deviceService = new DeviceServiceImpl();

    @RequestMapping(method = RequestMethod.POST, path = "/retrieveall")
    public String retrieveAllDevices(HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("failed");
            return "failed";
        } else {
            String token=(String) httpSession.getAttribute("token");
            if(RedisManager.getData(token+"/alldevices")!=null){
                return RedisManager.getData(token+"/alldevices");
            }
            Gson gson = new Gson();
            String result=gson.toJson(deviceService.getDevices(token));
            RedisManager.setData(token+"alldevices",result);
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/retrieveone")
    public String retrieveOneDevices(String devuuid,HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("failed");
            return "failed";
        } else {
            String token=(String) httpSession.getAttribute("token");
            if(RedisManager.getData(token+"/onedevice")!=null){
                return RedisManager.getData(token+"/onedevice");
            }
            Gson gson = new Gson();
            String result=gson.toJson(deviceService.getDevice(devuuid,token));
            RedisManager.setData(token+"onedevice",result);
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST,path="/getcurlocation")
    public String getDeviceLocation(String devuuid,HttpSession httpSession){
        if (httpSession.getAttribute("token") == null){
            log.info("Get token failed.");
            return "failed";
        }
        else{
            if(RedisManager.getData(devuuid)==null) {
                Gson gson = new Gson();
                String result=gson.toJson(deviceService.getDeviceLocation(devuuid, (String) httpSession.getAttribute("token")));
                RedisManager.setData(devuuid+"/curlocation",result);
                return result;
            }
            else{
                return RedisManager.getData(devuuid+"/curlocation");
            }
        }
    }
}
