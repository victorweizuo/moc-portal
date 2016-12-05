import com.google.gson.Gson;
import moc.entities.Device;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23.
 */
public class DeviceTest {

    @Test
    public void testRetrieveAllDevices(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/retrieveall");
        requestModel.getRequest_header().put("accesstoken","71979549501470190CACB2B8BDECBF5A62A92BBFD8FF0BF936C5974277DFD1A263E7E58C889374D6");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("page","0");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        Gson gson=new Gson();
        List<Device> result= gson.fromJson(remoteConnector.getInstance().requestJsonObject(requestModel).getAsJsonObject("response_body").get("resultlist").getAsString(),List.class);
        System.out.println(result.get(0));
    }

    @Test
    public void testGPRSChannel(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken","FE51DCE57A372DAB6DB8A854AD95A73272E1485696A3A6565FA4A3D2CD8B6DBB00561813CDD4C448");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname","gpscurlocation");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetOneDevice(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicemanagement/retrieveone");
        requestModel.getRequest_header().put("accesstoken","95C668D19AAF34936475856CCFA7125EF23A6C953D0E687EBF4377C643699CE6AB16237FB3465C3A");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceRFID(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/deviceconfig/servermappingget");
        requestModel.getRequest_header().put("accesstoken","95C668D19AAF34936475856CCFA7125EF23A6C953D0E687EBF4377C643699CE6AB16237FB3465C3A");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname","rfid");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceCentralLock(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken","95C668D19AAF34936475856CCFA7125EF23A6C953D0E687EBF4377C643699CE6AB16237FB3465C3A");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname","centrallock");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceImmobilizer(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken","95C668D19AAF34936475856CCFA7125EF23A6C953D0E687EBF4377C643699CE6AB16237FB3465C3A");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname","immobilizer");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }

    @Test
    public void testGetDeviceBTConnectedDevice(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/devicestatus/servermappingget");
        requestModel.getRequest_header().put("accesstoken","95C668D19AAF34936475856CCFA7125EF23A6C953D0E687EBF4377C643699CE6AB16237FB3465C3A");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname","btcurcondevaddr");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }
}
