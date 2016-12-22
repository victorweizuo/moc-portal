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
            String token = (String) httpSession.getAttribute("token");
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getDevices(token));
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/retrieveone")
    public String retrieveOneDevices(String devuuid, HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("failed");
            return "failed";
        } else {
            String token = (String) httpSession.getAttribute("token");
            if (RedisManager.getData(token + "/onedevice") != null) {
                return RedisManager.getData(token + "/onedevice");
            }
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getDevice(devuuid, token));
            RedisManager.setData(token + "onedevice", result);
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getcurlocation")
    public String getDeviceLocation(String devuuid, HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            if (RedisManager.getData(devuuid) == null) {
                Gson gson = new Gson();
                String result = gson.toJson(deviceService.getDeviceLocation(devuuid, (String) httpSession.getAttribute("token")));
                RedisManager.setData(devuuid + "/curlocation", result);
                return result;
            } else {
                return RedisManager.getData(devuuid + "/curlocation");
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getdevicestatus")
    public String getDeviceStatus(String devuuid, HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            if (RedisManager.getData(devuuid) == null) {
                Gson gson = new Gson();
                String result = gson.toJson(deviceService.getDeviceStatus(devuuid, (String) httpSession.getAttribute("token")));
                RedisManager.setData(devuuid + "/getdevicestatus", result);
                return result;
            } else {
                return RedisManager.getData(devuuid + "/getdevicestatus");
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getdeviceevents")
    public String getDeviceEvents(HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getDeviceEvents((String) httpSession.getAttribute("token")));
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getdeviceeventsbytime")
    public String getDeviceEventsByTime(String startDate,String endDate,HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getDeviceEventsByTime(startDate,endDate,(String) httpSession.getAttribute("token")));
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getdevicestatisticsgrid")
    public String getDeviceStatisticsGrid(String devuuid, HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getDeviceStatisticsGrid(devuuid,(String) httpSession.getAttribute("token")));
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getdevicestatisticsgridbytime")
    public String getDeviceStatisticsByTime(String startDate,String endDate, HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getDeviceStatisticsByTime(startDate,endDate,(String) httpSession.getAttribute("token")));
            return result;
        }
    }


    @RequestMapping(method = RequestMethod.POST, path = "/savedevice")
    public String saveDevice(String devuuid, String smsno,String blemac,String sppmac,HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.saveDevice(devuuid,smsno,blemac,sppmac,(String) httpSession.getAttribute("token")));
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/adddevice")
    public String addDevice(String serialno, String platvin, String smsno,String blemac,String sppmac,HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.addDevice(serialno,platvin,smsno,blemac,sppmac,(String) httpSession.getAttribute("token")));
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getfleeteventsoverview")
    public String getFleetEventsOverview(String startDate,String endDate,HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("Get token failed.");
            return "failed";
        } else {
            Gson gson = new Gson();
            String result = gson.toJson(deviceService.getFleetEventsOverview(startDate,endDate,(String) httpSession.getAttribute("token")));
            return result;
        }
    }



}
