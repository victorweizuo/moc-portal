package moc.mocremote;

import jodd.json.meta.JSON;
import lombok.Data;
import moc.entities.Device;
import moc.main.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/23.
 */

@Data
@JSON(strict = true)
public class RequestModel {
    @JSON
    private Map<String, String> request_header = new HashMap<>();
    private String path;
    private String url;
    @JSON
    private Map<String, String> request_body = new HashMap<>();

    public static RequestModel login(String usename, String password) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/usermanagement/appidsecret");
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("username", usename);
        requestModel.getRequest_body().put("password", password);
        return requestModel;
    }

    public static RequestModel retrieveAllDevices(String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicemanagement/retrieveall");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("page", "0");
        return requestModel;
    }

    public static RequestModel getToken(String appid, String appsecret) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/token/generaltokenget");
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("appid", appid);
        requestModel.getRequest_body().put("appsecret", appsecret);
        return requestModel;

    }

    public static RequestModel getDevices(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicemanagement/retrieveall");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("page", "0");
        return requestModel;
    }

    public static RequestModel getDeviceLocation(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", "gpscurlocation");
        return requestModel;
    }

    public static RequestModel getDevice(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicemanagement/retrieveone");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        return requestModel;
    }

    public static RequestModel getDeviceCentralLock(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", "centrallock");
        return requestModel;
    }

    public static RequestModel getDeviceImmobilizer(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", "immobilizer");
        return requestModel;
    }

    public static RequestModel getDeviceBTConnectedDevice(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", "btcurcondevaddr");
        return requestModel;
    }

    public static RequestModel getDeviceRFID(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/deviceconfig/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", "rfid");
        return requestModel;
    }

    public static RequestModel getDeviceEvents(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/deviceevent/get");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("startdate", "2015-11-10T10:00:00Z");
        requestModel.getRequest_body().put("enddate", "2017-11-12T10:00:00Z");
        requestModel.getRequest_body().put("page", "1");
        return requestModel;
    }

    public static RequestModel getDeviceAPICalls(String devuuid, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/apicallhistory/get");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("startdate", "2015-11-10T10:00:00Z");
        requestModel.getRequest_body().put("enddate", "2017-11-12T10:00:00Z");
        requestModel.getRequest_body().put("page", "1");
        return requestModel;
    }

    public static RequestModel getVehicleInformation(String devuuid, String token, String propertyName) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/vehicleinfo/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", propertyName);
        return requestModel;
    }


    public static RequestModel saveDevice(String devuuid, String propertyName, String smsno, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/modify");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put(propertyName, smsno);
        return requestModel;
    }

    public static RequestModel addDevice( String serialno,String platvin, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/add");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceserialno", serialno);
        requestModel.getRequest_body().put("plate_vin", platvin);
        return requestModel;
    }

    public static RequestModel getDeviceStatus(String devuuid, String propertyName, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        requestModel.getRequest_body().put("propertyname", propertyName);
        return requestModel;
    }

    public static RequestModel getDeviceEventsByTime(String devuuid,String startDate,String endDate, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/deviceevent/get");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        Date start=new Date(Long.parseLong(startDate));
        Date end=new Date(Long.parseLong(endDate));
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        requestModel.getRequest_body().put("startdate", format.format(start));
        requestModel.getRequest_body().put("enddate", format.format(end));
        requestModel.getRequest_body().put("page", "1");
        return requestModel;
    }

    public static RequestModel getDeviceAPICallsByTime(String devuuid, String startDate, String endDate, String token) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath + "/apicallhistory/get");
        requestModel.getRequest_header().put("accesstoken", token);
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", devuuid);
        Date start=new Date(Long.parseLong(startDate));
        Date end=new Date(Long.parseLong(endDate));
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        requestModel.getRequest_body().put("startdate", format.format(start));
        requestModel.getRequest_body().put("enddate", format.format(end));
        requestModel.getRequest_body().put("page", "1");
        return requestModel;
    }
}
