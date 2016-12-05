package moc.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import moc.entities.Device;
import moc.entities.Location;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.services.DeviceService;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class DeviceServiceImpl implements DeviceService {

    //Device模型尚未确定
    @Override
    public Device getDevice(String devuuid,String token) {
        JsonObject jsonObject=RemoteConnector.getInstance().requestJsonObject(RequestModel.getDevice(devuuid,token));
        JsonObject result=jsonObject.getAsJsonObject("response_body");
        String devicebtblemac=result.get("devicebtsppmac").getAsString();
        String devicesmsno=result.get("devicesmsno").getAsString();
        String devicebtsppmac=result.get("devicebtsppmac").getAsString();
        String inuse=result.get("inuse").getAsString();
        String deviceserialno=result.get("deviceserialno").getAsString();
        return new Device(devicebtblemac,devicebtsppmac,devicesmsno,deviceserialno,devuuid,inuse);
    }

    @Override
    public List<Device> getDevices(String token) {
        JsonObject jsonObject=RemoteConnector.getInstance().requestJsonObject(RequestModel.retrieveAllDevices(token));
        Gson gson=new Gson();
        List<Device> result=gson.fromJson(jsonObject.getAsJsonObject("response_body").get("resultlist").getAsString(),List.class);
        return result;
    }

    @Override
    public Location getDeviceLocation(String devuuid, String token) {

        JsonObject jsonObject=RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceLocation(devuuid,token));
        String result=jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        double lat=(double)Integer.parseInt(result.substring(0,8),16)/100000;
        double lon=(double)Integer.parseInt(result.substring(8,16),16)/100000;
        Location location=new Location(lat,lon,devuuid);
        return location;
    }
}
