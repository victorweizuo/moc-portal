package moc.services.impl;

import com.google.gson.JsonObject;
import moc.mocremote.RemoteConnector;
import moc.mocremote.RequestModel;
import moc.services.UserService;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/22.
 */
public class UserServiceImpl implements UserService {
    @Override
    public JsonObject validate(String username, String password) {
        JsonObject result = RemoteConnector.getInstance().requestJsonObject(RequestModel.login(username, password));
        if (result.getAsJsonObject("response_header").get("rspdesc").getAsString().equals("Success")) {
            return result;
        }
        return null;
    }
}
