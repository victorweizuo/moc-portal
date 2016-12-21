package moc.services.impl;

import com.google.gson.JsonObject;
import moc.entities.Location;
import moc.entities.VehicleInformation;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.services.VehicleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
public class VehicleServiceImpl implements VehicleService{
    @Override
    public List<VehicleInformation> getVehicleInformations(String devuuid,String token) {
        List<VehicleInformation> result=new ArrayList<>();
        VehicleInformation batteryLevel=getVehicleInformation(devuuid,token,"batterylevel","Battery Level");
        result.add(batteryLevel);
        VehicleInformation mileage=getVehicleInformation(devuuid,token,"mileage","Mileage");
        result.add(mileage);
        return result;
    }

    private VehicleInformation getVehicleInformation(String devuuid,String token,String propertyName,String title){
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getVehicleInformation(devuuid, token,propertyName));
        String result = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        VehicleInformation vehicleInformation=new VehicleInformation();
        vehicleInformation.setParameter(title);
        vehicleInformation.setStatus(result);
        return vehicleInformation;
    }
}
