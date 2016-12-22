package moc.services;

import com.google.gson.JsonObject;
import moc.entities.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/23.
 */
public interface DeviceService {
    public Device getDevice(String uuid,String token);
    public List<Device> getDevices(String token);

    Location getDeviceLocation(String devuuid, String token);

    List<DeviceStatus> getDeviceStatus(String devuuid, String token);

    List<Map<String,String>> getDeviceEvents(String token);

    List<DeviceStatistic> getDeviceStatisticsGrid(String devuuid, String token);

    String saveDevice(String devuuid, String smsno, String blemac, String sppmac, String token);

    String addDevice(String serialno, String platvin,String smsno, String blemac, String sppmac, String token);

    List<Map<String,String>> getDeviceEventsByTime(String startDate, String endDate, String token);

    List<DeviceStatistic>  getDeviceStatisticsByTime(String startDate, String endDate, String token);

    List<Map<String,String>> getFleetEventsOverview(String startDate,String endDate,String token);
}
