package moc.mocremote;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import lombok.extern.slf4j.Slf4j;
import moc.utils.JsonUtil;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.json.JSONObject;

import javax.net.ssl.*;
import javax.ws.rs.core.MediaType;
import java.security.cert.X509Certificate;
import java.util.Map;


/**
 * Created by Administrator on 2016/10/23.
 */
@Slf4j
public class RemoteConnector {
    private static RemoteConnector instance;

    public static RemoteConnector getInstance() {
        if (instance == null) {
            instance = new RemoteConnector();
        }
        return instance;
    }

    private RemoteConnector() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier

    }

    public Map<String, String> requestMap(RequestModel requestModel) {
        String resultString = requestString(requestModel);
        Map<String, String> result = JsonUtil.decode(resultString, Map.class);
        return result;
    }

    public String requestString(RequestModel requestModel) {
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(MOXyJsonProvider.class);
        Client client = Client.create(config);
        WebResource service = client.resource(requestModel.getUrl());
        String paras = JsonUtil.encode(requestModel);
        JSONObject inputJsonObj = new JSONObject(paras);

        String[] paths = requestModel.getPath().split("/");
        for (String path : paths) {
            service = service.path(path);
        }
        ClientResponse resp = service.type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, inputJsonObj.toString());
        String resultString = resp.getEntity(String.class);
        return resultString;
    }

    public JsonObject requestJsonObject(RequestModel requestModel) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestString(requestModel), JsonObject.class);
        log.info(jsonObject.toString());
        return jsonObject;
    }

}
