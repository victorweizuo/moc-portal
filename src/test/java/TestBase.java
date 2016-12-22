import com.google.gson.JsonObject;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import org.junit.Test;

/**
 * Created by Administrator on 2016/12/18.
 */
public class TestBase {
    public String getToken(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://sandbox.mobilityoncloud.com");
        requestModel.setPath("moc-server-sandbox/token/generaltokenget");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("appid","MoC-020009805154");
        requestModel.getRequest_body().put("appsecret","Eyqfmum50LGVe45HDAkYiC5lksEN03k8");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        JsonObject jsonObject=(remoteConnector.getInstance().requestJsonObject(requestModel));
        return (jsonObject.getAsJsonObject("response_body").get("generaltoken").getAsString());
    }
}
