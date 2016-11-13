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
    public Device getDevice(String devuuid) {
        return null;
    }

    @Override
    public List<Device> getDevices(String token) {
        JsonObject jsonObject=RemoteConnector.getInstance().requestJsonObject(RequestModel.retrieveAllDevices(token));
        Gson gson=new Gson();
        List<Device> result=gson.fromJson(jsonObject.getAsJsonObject("response_body").get("resultlist").getAsString(),List.class);
        return result;
    }

    @Override
    public Location getDeviceLocation(String devuuid, Object token) {
        return null;
    }
}
