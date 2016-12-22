import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import moc.entities.Device;
import moc.entities.DeviceEvent;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/10/23.
 */
public class DeviceTest extends TestBase {

    @Test
    public void testRetrieveAllDevices() {
        String token = getToken();
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://sandbox.mobilityoncloud.com");
        requestModel.setPath("moc-server-sandbox/devicemanagement/retrieveall");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("page", "0");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        Gson gson = new Gson();
        JsonObject result_json = remoteConnector.getInstance().requestJsonObject(requestModel);
        System.out.println(result_json.getAsJsonObject("response_body").get("resultlist").toString());
        Type type = new TypeToken<List<Device>>() {

        }.getType();
        List<Device> result = gson.fromJson(result_json.getAsJsonObject("response_body").get("resultlist").toString(), type);
        System.out.println(result.get(0));
    }

    @Test
    public void testGPRSChannel() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "gpscurlocation");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetOneDevice() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/retrieveone");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetWrongOneDevice() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/retrieveone");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f1");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testSetOneDevice() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://sandbox.mobilityoncloud.com");
        requestModel.setPath("moc-server-sandbox/devicemanagement/modify");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("devicebtblemac", "000e0b13014f");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceRFID() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/deviceconfig/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "rfid");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceBTModule() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "btmodulestatus");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceOBD() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "obdmodulestatus");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceGPRSModule() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "gprsmodulestatus");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceCentralLock() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "centrallock");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceImmobilizer() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "immobilizer");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceBTConnectedDevice() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "btcurcondevaddr");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        String resultString = JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceEvents() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/deviceevent/get");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("startdate", "2015-11-10T10:00:00Z");
        requestModel.getRequest_body().put("enddate", "2017-11-12T10:00:00Z");
        requestModel.getRequest_body().put("page", "1");

        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);

        System.out.println(jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist"));

    }

    @Test
    public void testGetHeartBeats() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/deviceevent/get");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("startdate", "2015-11-10T10:00:00Z");
        requestModel.getRequest_body().put("enddate", "2017-11-12T10:00:00Z");
        requestModel.getRequest_body().put("page", "1");

        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);
        Type type = new TypeToken<List<DeviceEvent>>() {
        }.getType();
        Gson gson = new Gson();
        List<DeviceEvent> deviceEvents = gson.fromJson(jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").toString(), type);
        deviceEvents = deviceEvents.stream().filter(deviceEvent -> deviceEvent.getPropertyName().equals("heartbeat")).collect(Collectors.toList());
        System.out.println(deviceEvents.size());
    }

    @Test
    public void testAddDevice(){
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/add");
        requestModel.getRequest_header().put("accesstoken", "8934588299894B1749EDED48CE258A30C2F6E5CCECE20FA0C2498B9882E89707E5945CDA82ECDEBB");
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceserialno", "2016060100001");
        requestModel.getRequest_body().put("plate_vin", "008618688860259");

        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);
        System.out.println(jsonObject);
    }
}
