import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonSerializer;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.json.JSONObject;

import javax.net.ssl.*;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/16.
 */
public class MoCServerConnector {
    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
        getAppidSecret2();
    }

    public static void register(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,Object> params=new HashMap<>();
        params.put("username","zuoweitest");
        params.put("password","zuowei123");
        params.put("email","victorzuo@outlook.com");
        params.put("phone","08618211268202");

        HttpResponse response=httpRequest.header("timestamp",1447644285294l)
                .body(new JsonSerializer().serialize(params))
                .post("https://120.24.253.247/moc-server-sandbox/usermanagement/register")
                .send();

        System.out.println(response);
    }

    public static void getAppidSecret2() throws NoSuchAlgorithmException, KeyManagementException {
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
        client.addFilter(new LoggingFilter());
        WebResource service = client.resource(url);
        service.header("timestamp","1447644285294");
        String paras="{\n" +
                "    \"request_header\":{\n" +
                "        \"timestamp\":\"1447644285294\"\n" +
                "    },\n" +
                "    \"request_body\":{\n" +
                "        \"username\":\"mobilityoncloudvip\",\n" +
                "        \"password\":\"skdjfRRR34dfe&\"\n" +
                "    }\n" +
                "}";
        JSONObject inputJsonObj = new JSONObject(paras);
//
//        inputJsonObj.put("username", "mobilityoncloudvip");
//        inputJsonObj.put("password", "skdjfRRR34dfe");
        ClientResponse resp=(service.path("moc-server-sandbox")
                .path("usermanagement")
                .path("appidsecret")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, inputJsonObj.toString()));
        String s=resp.getEntity(String.class);
        System.out.println(s);
    }
    public static void getAppidSecret(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,Object> params=new HashMap<>();
        params.put("username","mobilityoncloudvip");
        params.put("password","skdjfRRR34dfe");
        String paras="{\n" +
                "    \"request_header\":{\n" +
                "        \"timestamp\":\"1447644285294\"\n" +
                "    },\n" +
                "    \"request_body\":{\n" +
                "        \"username\":\"mobilityoncloudvip\",\n" +
                "        \"password\":\"skdjfRRR34dfe&\"\n" +
                "    }\n" +
                "}";
        HttpResponse response=httpRequest
                .header("timestamp",144764428529l)
                .form(params)
//                .bodyText(paras,"application/json")
                .mediaType("application/json")
                .contentType("application/json","utf8")
                .post("https://120.24.254.19/moc-server-sandbox/usermanagement/appidsecret")

                .send();


        System.out.println();
        System.out.println(response);
    }
}
