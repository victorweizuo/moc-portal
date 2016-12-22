import com.google.gson.JsonObject;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/6.
 */
public class UserTest {
    @Test
    public void testValidateUserLogin(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/usermanagement/appidsecret");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("username","mobilityoncloudvip");
        requestModel.getRequest_body().put("password","skdjfRRR34dfe&");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        JsonObject jsonObject= (remoteConnector.getInstance().requestJsonObject(requestModel));

        System.out.println(jsonObject.getAsJsonObject("response_body").get("appid").getAsString());
    }

    @Test
    public void testChangePassword(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/usermanagement/passwordchange");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("username","mobilityoncloudvip");
        requestModel.getRequest_body().put("password","skdjfRRR34dfe&");
        requestModel.getRequest_body().put("passwordchange","skdjfRRR34dfe&");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        JsonObject jsonObject= (remoteConnector.getInstance().requestJsonObject(requestModel));
    }
}
