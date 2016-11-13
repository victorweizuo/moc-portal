package moc.mocremote;

import jodd.json.meta.JSON;
import lombok.Data;
import moc.entities.Device;
import moc.main.Config;

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
    private Map<String,String> request_header=new HashMap<>();
    private String path;
    private String url;
    @JSON
    private Map<String,String> request_body=new HashMap<>();

    public static RequestModel login(String usename,String password){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath+"/usermanagement/appidsecret");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("username",usename);
        requestModel.getRequest_body().put("password",password);
        return requestModel;
    }

    public static RequestModel retrieveAllDevices(String token){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath+"/devicemanagement/retrieveall");
        requestModel.getRequest_header().put("accesstoken",token);
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("page","0");
        return requestModel;
    }

    public static RequestModel getToken(String appid,String appsecret){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath+"/token/generaltokenget");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("appid",appid);
        requestModel.getRequest_body().put("appsecret",appsecret);
        return requestModel;

    }

    public static RequestModel getDevices(String devuuid,String token){
        RequestModel requestModel=new RequestModel();
        requestModel.setUrl(Config.host);
        requestModel.setPath(Config.hostpath+"/devicemanagement/retrieveall");
        requestModel.getRequest_header().put("accesstoken","1DE66F89489A3FE44F3FBA15F1124AE709D7B9CBAC9A0EF70CFD1114CA848844A63331779E69AF5E");
        requestModel.getRequest_header().put("timestamp","1447644285294");
        requestModel.getRequest_body().put("page","0");
        return requestModel;
    }

}
