package moc.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import moc.entities.*;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.services.DeviceService;
import moc.utils.JsonUtil;
import netscape.javascript.JSUtil;
import org.springframework.core.convert.TypeDescriptor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/11/8.
 */
public class DeviceServiceImpl implements DeviceService {

    //Device模型尚未确定
    @Override
    public Device getDevice(String devuuid, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDevice(devuuid, token));

        JsonObject result = jsonObject.getAsJsonObject("response_body");
        if (!result.has("devicebtsppmac")) return null;
        String devicebtblemac = result.get("devicebtsppmac").getAsString();
        String devicesmsno = result.get("devicesmsno").getAsString();
        String devicebtsppmac = result.get("devicebtsppmac").getAsString();
        String inuse = result.get("inuse").getAsString();
        String deviceserialno = result.get("deviceserialno").getAsString();
//        String plate_vin = result.get.get("plate_vin").getAsString();
        return new Device(devicebtblemac, devicebtsppmac, devicesmsno, deviceserialno, devuuid, inuse);
    }

    @Override
    public List<Device> getDevices(String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.retrieveAllDevices(token));
        Gson gson = new Gson();
        Type type = new TypeToken<List<Device>>() {
        }.getType();
        List<Device> result = gson.fromJson(jsonObject.getAsJsonObject("response_body").get("resultlist"), type);
        return result;
    }

    @Override
    public Location getDeviceLocation(String devuuid, String token) {

        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceLocation(devuuid, token));
        String result = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        double lat = (double) Integer.parseInt(result.substring(0, 8), 16) / 100000;
        double lon = (double) Integer.parseInt(result.substring(8, 16), 16) / 100000;
        Location location = new Location(lat, lon, devuuid);
        return location;
    }

    @Override
    public List<DeviceStatus> getDeviceStatus(String devuuid, String token) {

        List<DeviceStatus> result = new ArrayList<>();
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceCentralLock(devuuid, token));
        String centralLock_result = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        DeviceStatus centralLock = new DeviceStatus();
        centralLock.setPropertyName(centralLock_result);
        centralLock.setParameter("Central Lock");
        centralLock.setStatus(centralLock_result);
        result.add(centralLock);

        jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceImmobilizer(devuuid, token));
        String immobilizer_result = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        DeviceStatus immobilizer = new DeviceStatus();
        immobilizer.setParameter("Immobilizer");
        immobilizer.setPropertyName(immobilizer_result);
        immobilizer.setStatus(immobilizer_result);
        result.add(immobilizer);

        jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceBTConnectedDevice(devuuid, token));
        String btConnectedDevice_result = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        DeviceStatus btConnectedDevice = new DeviceStatus();
        btConnectedDevice.setPropertyName(btConnectedDevice_result);
        btConnectedDevice.setParameter("BT Current Address");
        btConnectedDevice.setStatus(btConnectedDevice_result);
        result.add(btConnectedDevice);

        jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceRFID(devuuid, token));
        String rfid_result = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        DeviceStatus rfid = new DeviceStatus();
        rfid.setPropertyName(rfid_result);
        rfid.setParameter("RFID");
        rfid.setStatus(rfid_result);
        result.add(rfid);

        result.add(getDeviceStatus("btmodulestatus", "BT Module", devuuid, token));
        result.add(getDeviceStatus("obdmodulestatus", "OBD module", devuuid, token));
        result.add(getDeviceStatus("gprsmodulestatus", "GPRS module", devuuid, token));

        return result;
    }

    private DeviceStatus getDeviceStatus(String propertyname, String parameter, String devuuid, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceStatus(devuuid, propertyname, token));
        String status = jsonObject.getAsJsonObject("response_body").get("propertyvalue").getAsString();
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.setParameter(parameter);
        deviceStatus.setPropertyName(propertyname);
        deviceStatus.setStatus(status);
        return deviceStatus;
    }

    @Override
    public List<Map<String, String>> getDeviceEvents(String token) {
        List<Device> devices = getDevices(token);
        List<Map<String, String>> result = new ArrayList<>();
        for (Device device : devices) {
            JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceEvents(device.getDevuuid(), token));
            if (!jsonObject.has("response_body")) continue;
            if (!jsonObject.getAsJsonObject("response_body").has("resultlist")) continue;
            if (!jsonObject.getAsJsonObject("response_body").get("resultlist").isJsonArray()) continue;
            if (jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").size() == 0) continue;
            String requestResult = jsonObject.getAsJsonObject("response_body").get("resultlist").toString();
            List<DeviceEvent> deviceEventsResult = JsonUtil.decode(requestResult, List.class);
            Map<String, String> eventCalls = new HashMap<>();
            eventCalls.put("devuuid", device.getDevuuid());
            eventCalls.put("eventcount", Integer.toString(deviceEventsResult.size()));
            result.add(eventCalls);
        }
        return result;
    }

    @Override
    public List<DeviceStatistic> getDeviceStatisticsGrid(String devuuid, String token) {
        List<DeviceStatistic> result = new ArrayList<>();
        List<DeviceEvent> deviceEventsResult = getDeviceEvents(devuuid, token);
        DeviceStatistic eventStatistic = new DeviceStatistic();
        eventStatistic.setParameter("Events");
        eventStatistic.setTotal(Integer.toString(deviceEventsResult.size()));
        eventStatistic.setPerday(Integer.toString(deviceEventsResult.size()));
        result.add(eventStatistic);

        List<DeviceAPI> deviceAPIResults = getDeviceAPICalls(devuuid, token);
        DeviceStatistic apiStatistic = new DeviceStatistic();
        apiStatistic.setParameter("API Calls");
        apiStatistic.setTotal(Integer.toString(deviceAPIResults.size()));
        apiStatistic.setPerday(Integer.toString(deviceAPIResults.size()));
        result.add(apiStatistic);

        List<DeviceEvent> heartBeatsResults = deviceEventsResult.stream().filter(deviceEvent -> deviceEvent.getPropertyName().equals("heartbeat")).collect(Collectors.toList());
        DeviceStatistic heartBeatStatistic = new DeviceStatistic();
        heartBeatStatistic.setParameter("Heart Beats");
        heartBeatStatistic.setTotal(Integer.toString(heartBeatsResults.size()));
        heartBeatStatistic.setPerday(Integer.toString(heartBeatsResults.size()));
        result.add(heartBeatStatistic);
        return result;
    }

    @Override
    public String saveDevice(String devuuid, String smsno, String blemac, String sppmac, String token) {

        String saveSmsno = RemoteConnector.getInstance().requestJsonObject(RequestModel.saveDevice(devuuid, "devicesmsno", smsno, token))
                .getAsJsonObject("response_body").get("resultdesc").getAsString();
        if (!saveSmsno.contains("success")) return saveSmsno;
        String devicebtsppmac = RemoteConnector.getInstance().requestJsonObject(RequestModel.saveDevice(devuuid, "devicebtsppmac", sppmac, token))
                .getAsJsonObject("response_body").get("resultdesc").getAsString();
        if (!devicebtsppmac.contains("success")) return saveSmsno;
        String devicebtblemac = RemoteConnector.getInstance().requestJsonObject(RequestModel.saveDevice(devuuid, "devicebtblemac", blemac, token))
                .getAsJsonObject("response_body").get("resultdesc").getAsString();
        if (!devicebtblemac.contains("success")) return saveSmsno;
        return "Success";
    }

    @Override
    public String addDevice(String serialno, String platvin, String smsno, String blemac, String sppmac, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.addDevice(serialno, platvin, token))
                .getAsJsonObject("response_body");
        String resultcode = jsonObject.get("resultcode").getAsString();
        if (resultcode.contains("10016")) {
            return jsonObject.get("resultdesc").getAsString();
        } else {
            String devuuid = jsonObject.get("deviceuuid").getAsString();
            return saveDevice(devuuid, smsno, blemac, sppmac, token);
        }
    }

    @Override
    public List<Map<String, String>> getDeviceEventsByTime(String startDate, String endDate, String token) {
        List<Device> devices = getDevices(token);
        List<Map<String, String>> result = new ArrayList<>();
        for (Device device : devices) {
            JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceEventsByTime(device.getDevuuid(), startDate, endDate, token));
            if (!jsonObject.has("response_body")) continue;
            if (!jsonObject.getAsJsonObject("response_body").has("resultlist")) continue;
            if (!jsonObject.getAsJsonObject("response_body").get("resultlist").isJsonArray()) continue;
            if (jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").size() == 0) continue;
            String requestResult = jsonObject.getAsJsonObject("response_body").get("resultlist").toString();
            List<DeviceEvent> deviceEventsResult = JsonUtil.decode(requestResult, List.class);
            Map<String, String> eventCalls = new HashMap<>();
            eventCalls.put("devuuid", device.getDevuuid());
            eventCalls.put("eventcount", Integer.toString(deviceEventsResult.size()));
            result.add(eventCalls);
        }
        return result;
    }

    @Override
    public List<DeviceStatistic> getDeviceStatisticsByTime(String startDate, String endDate, String token) {
        List<DeviceStatistic> result = new ArrayList<>();
        List<Device> devices = getDevices(token);

        DeviceStatistic eventStatistic = new DeviceStatistic();
        eventStatistic.setParameter("Events");
        for (Device device : devices) {
            String devuuid = device.getDevuuid();
            List<DeviceEvent> deviceEventsResult = getDeviceEventsByTime(devuuid, startDate, endDate, token);
            if(deviceEventsResult==null){
                eventStatistic.setTotal("0");
            }
            else eventStatistic.setTotal(Integer.parseInt(eventStatistic.getTotal()) + Integer.toString(deviceEventsResult.size()));
        }
        eventStatistic.setPerday((eventStatistic.getTotal()));
        result.add(eventStatistic);


        DeviceStatistic apiStatistic = new DeviceStatistic();
        apiStatistic.setParameter("API Calls");
        for (Device device : devices) {
            String devuuid = device.getDevuuid();
            List<DeviceAPI> deviceAPIResults = getDeviceAPICallsByTime(devuuid, startDate, endDate, token);
            apiStatistic.setTotal(Integer.parseInt(apiStatistic.getTotal()) + Integer.toString(deviceAPIResults.size()));
        }
        apiStatistic.setPerday((apiStatistic.getTotal()));
        result.add(apiStatistic);

        DeviceStatistic heartBeatStatistic = new DeviceStatistic();
        heartBeatStatistic.setParameter("Heart Beats");
        for (Device device : devices) {
            String devuuid = device.getDevuuid();
            List<DeviceEvent> heartBeatsResults = getDeviceEventsByTime(devuuid, startDate, endDate, token).stream()
                    .filter(deviceEvent -> deviceEvent.getPropertyName().equals("heartbeat")).collect(Collectors.toList());
            heartBeatStatistic.setTotal(Integer.parseInt(heartBeatStatistic.getTotal()) + Integer.toString(heartBeatsResults.size()));
        }
        heartBeatStatistic.setPerday((heartBeatStatistic.getTotal()));
        result.add(heartBeatStatistic);
        return result;
    }


    private List<DeviceAPI> getDeviceAPICallsByTime(String devuuid, String startDate, String endDate, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceAPICallsByTime(devuuid, startDate, endDate, token));
        if (!jsonObject.has("response_body")) return null;
        if (!jsonObject.getAsJsonObject("response_body").has("resultlist")) return null;
        if (!jsonObject.getAsJsonObject("response_body").get("resultlist").isJsonArray()) return null;
        if (jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").size() == 0) return null;
        String requestResult = jsonObject.getAsJsonObject("response_body").get("resultlist").toString();
        Type type = new TypeToken<List<DeviceAPI>>() {
        }.getType();
        List<DeviceAPI> deviceEventsResult = JsonUtil.decode(requestResult, type);
        return deviceEventsResult;
    }


    private List<DeviceEvent> getDeviceEvents(String devuuid, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceEvents(devuuid, token));
        if (!jsonObject.has("response_body")) return null;
        if (!jsonObject.getAsJsonObject("response_body").has("resultlist")) return null;
        if (!jsonObject.getAsJsonObject("response_body").get("resultlist").isJsonArray()) return null;
        if (jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").size() == 0) return null;
        String requestResult = jsonObject.getAsJsonObject("response_body").get("resultlist").toString();
        Type type = new TypeToken<List<DeviceEvent>>() {
        }.getType();
        List<DeviceEvent> deviceEventsResult = JsonUtil.decode(requestResult, type);
        return deviceEventsResult;
    }

    private List<DeviceEvent> getDeviceEventsByTime(String devuuid, String startDate, String endDate, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceEventsByTime(devuuid, startDate, endDate, token));
        if (!jsonObject.has("response_body")) return null;
        if (!jsonObject.getAsJsonObject("response_body").has("resultlist")) return null;
        if (!jsonObject.getAsJsonObject("response_body").get("resultlist").isJsonArray()) return null;
        if (jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").size() == 0) return null;
        String requestResult = jsonObject.getAsJsonObject("response_body").get("resultlist").toString();
        Type type = new TypeToken<List<DeviceEvent>>() {
        }.getType();
        List<DeviceEvent> deviceEventsResult = JsonUtil.decode(requestResult, type);
        return deviceEventsResult;
    }

    private List<DeviceAPI> getDeviceAPICalls(String devuuid, String token) {
        JsonObject jsonObject = RemoteConnector.getInstance().requestJsonObject(RequestModel.getDeviceAPICalls(devuuid, token));
        if (!jsonObject.has("response_body")) return null;
        if (!jsonObject.getAsJsonObject("response_body").has("resultlist")) return null;
        if (!jsonObject.getAsJsonObject("response_body").get("resultlist").isJsonArray()) return null;
        if (jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").size() == 0) return null;
        String requestResult = jsonObject.getAsJsonObject("response_body").get("resultlist").toString();
        Type type = new TypeToken<List<DeviceAPI>>() {
        }.getType();
        List<DeviceAPI> deviceEventsResult = JsonUtil.decode(requestResult, type);
        return deviceEventsResult;
    }
}
