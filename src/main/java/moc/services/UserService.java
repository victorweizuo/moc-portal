package moc.services;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2016/9/17.
 */
public interface UserService {
    public JsonObject validate(String username, String password);

    String changePassword(String username,String password, String passwordreset);
}
