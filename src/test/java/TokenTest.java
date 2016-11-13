
import com.google.gson.JsonObject;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TokenTest {

    @Test
    public void testGetTokens(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/token/generaltokenget");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("appid","MoC-020009805154");
        requestModel.getRequest_body().put("appsecret","Eyqfmum50LGVe45HDAkYiC5lksEN03k8");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        JsonObject jsonObject=(remoteConnector.getInstance().requestJsonObject(requestModel));
        System.out.println(jsonObject.getAsJsonObject("response_body").getAsJsonObject("api"));
//        Assert.assertTrue(resultString.contains("Success"));

    }


}
