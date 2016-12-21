package moc.controllers;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import moc.services.VehicleService;
import moc.services.impl.VehicleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/12/18.
 */
@RestController
@RequestMapping("/vehicle")
@Slf4j
public class VehicleController {
    private VehicleService vehicleService=new VehicleServiceImpl();
    @RequestMapping(method = RequestMethod.POST, path = "/getvehicleinformations")
    public String getVehicleInformations(String devuuid,HttpSession httpSession) {
        if (httpSession.getAttribute("token") == null) {
            log.info("failed");
            return "failed";
        } else {
            String token = (String) httpSession.getAttribute("token");
            Gson gson = new Gson();
            String result = gson.toJson(vehicleService.getVehicleInformations(devuuid,token));
            return result;
        }
    }
}
