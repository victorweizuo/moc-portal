import com.google.gson.JsonObject;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import org.junit.Test;

/**
 * Created by Administrator on 2016/12/18.
 */
public class VehicleTest extends TestBase {
    @Test
    public void testVehicleLevel(){
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/vehicleinfo/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "batterylevel");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);
        System.out.println(jsonObject.getAsJsonObject("response_body"));
    }

    @Test
    public void testVehicleMileage(){
        RequestModel requestModel = new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/vehicleinfo/servermappingget");
        requestModel.getRequest_header().put("accesstoken", getToken());
        requestModel.getRequest_header().put("timestamp", "1447644285294");
        requestModel.getRequest_body().put("deviceuuid", "2dcc5cdcddb3449d98ad5c8fcbdb0c0f");
        requestModel.getRequest_body().put("propertyname", "mileage");
        RemoteConnector remoteConnector = RemoteConnector.getInstance();
        JsonObject jsonObject = remoteConnector.getInstance().requestJsonObject(requestModel);
        System.out.println(jsonObject.getAsJsonObject("response_body"));
    }
}
