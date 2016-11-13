package moc.services;

import com.google.gson.JsonObject;
import moc.entities.Device;
import moc.entities.Location;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */
public interface DeviceService {
    public Device getDevice(String uuid);
    public List<Device> getDevices(String token);

    Location getDeviceLocation(String devuuid, Object token);
}
