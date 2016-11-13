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
        requestModel.getRequest_header().put("accesstoken","09F54EACA751F95551B8FE98D675518517B11C9ECA937BBB10ADDD11D7CBC663E0D31AE7E58C5CA0");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","c5271d2d3ace42e9bc52310e30bc3e98");
        requestModel.getRequest_body().put("propertyname","gpscurlocation");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }
}
