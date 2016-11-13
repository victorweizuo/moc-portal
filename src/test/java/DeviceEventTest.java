import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2016/10/23.
 */
public class DeviceEventTest {
    @Test
    public void testGetDeviceEvent(){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl("https://120.24.254.19");
        requestModel.setPath("moc-server-sandbox/deviceevent/get");
        requestModel.getRequest_header().put("accesstoken","5B2FFF9CD5D8C02CED2B1E8B02BE86646BAE00307DE935665C4BA37503D79E85F549D114C10EEB56");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("deviceuuid","c5271d2d3ace42e9bc52310e30bc3e98");
        requestModel.getRequest_body().put("startdate ","2015-11-10 10:00:00");
        requestModel.getRequest_body().put("enddate","2015-11-13 10:00:00");
        requestModel.getRequest_body().put("page","1");
        requestModel.getRequest_body().put("gmt","GMT+8");
        RemoteConnector remoteConnector=RemoteConnector.getInstance();
        String resultString= JsonUtil.encode(remoteConnector.getInstance().requestMap(requestModel));
        System.out.println(resultString);
    }
}
