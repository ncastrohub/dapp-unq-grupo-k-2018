package utils;

import model.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import services.PublishService;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;

public class GetUserFromHeaders {

    public static User getCurrentUserFromHeaders(HttpHeaders headers, PublishService service){
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://tpi-desapp2.auth0.com/userinfo");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        HttpEntity entity = response.getEntity();
        try {
            InputStream instream = entity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = null;
        try {
            json = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert json != null;
        JSONObject jsonObj = new JSONObject(json);

        String email = jsonObj.get("email").toString();
        return service.getUserService().getByEmail(email);
    }
}
