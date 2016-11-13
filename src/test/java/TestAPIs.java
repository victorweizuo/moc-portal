import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import moc.mocremote.RequestModel;
import moc.utils.JsonUtil;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.json.JSONObject;
import org.junit.Test;

import javax.net.ssl.*;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Created by Administrator on 2016/10/23.
 */
public class TestAPIs {

    @Test
    public void getTokens() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
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
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        String url="https://120.24.254.19";
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(MOXyJsonProvider.class);
        Client client = Client.create(config);
        WebResource service = client.resource(url);
//        service.header("timestamp","1447644285294");
        RequestModel requestModel=new RequestModel();
        requestModel.getRequest_header().put("timestamp","1478605046000");
        requestModel.getRequest_body().put("appid","MoC-020009805154");
        requestModel.getRequest_body().put("appsecret","Eyqfmum50LGVe45HDAkYiC5lksEN03k8");
        String paras= JsonUtil.encode(requestModel);
        System.out.println(paras);
        JSONObject inputJsonObj = new JSONObject(paras);
        ClientResponse resp=(service.path("moc-server-sandbox")
                .path("token")
                .path("generaltokenget")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, inputJsonObj.toString()));
        String s=resp.getEntity(String.class);
        System.out.println(s);
    }
}
