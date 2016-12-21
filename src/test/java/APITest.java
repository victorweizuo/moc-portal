import com.google.gson.JsonObject;
import moc.entities.DeviceAPI;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */
public class APITest extends TestBase{
    @Test
    public void testAPIStatistic() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://sandbox.mobilityoncloud.com/");
        requestModel.setPath("moc-server-sandbox/apicallhistory/get");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("startdate", "2015-11-10T10:00:00Z");
        requestModel.getRequest_body().put("enddate", "2017-11-12T10:00:00Z");
        requestModel.getRequest_body().put("page", "1");

        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);
        List<DeviceAPI> deviceAPIs=JsonUtil.decode(jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").toString(), List.class);

        System.out.println(deviceAPIs.size());
    }

    @Test
    public void testEventsStatistic() {
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://sandbox.mobilityoncloud.com/");
        requestModel.setPath("moc-server-sandbox/apicallhistory/get");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("startdate", "2015-11-10T10:00:00Z");
        requestModel.getRequest_body().put("enddate", "2017-11-12T10:00:00Z");
        requestModel.getRequest_body().put("page", "1");

        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);
        List<DeviceAPI> deviceAPIs=JsonUtil.decode(jsonObject.getAsJsonObject("response_body").getAsJsonArray("resultlist").toString(), List.class);

        System.out.println(deviceAPIs.size());
    }
}
