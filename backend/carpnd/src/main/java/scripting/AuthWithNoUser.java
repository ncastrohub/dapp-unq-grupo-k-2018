package scripting;

import com.auth0.jwt.algorithms.Algorithm;
import javafx.util.Pair;
import javassist.NotFoundException;
import model.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import services.UserService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@AuthRequired
@Provider
public class AuthWithNoUser implements ContainerRequestFilter {


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Algorithm algorithmHS = Algorithm.HMAC256("LC56bhf4P1IOpr0xByM_d8-GYPi4rztH5IF1FXuW0XpJtvG-clkcB5_yL8xSNNZS");

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://tpi-desapp2.auth0.com/userinfo");
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        String json = EntityUtils.toString(entity);
        JSONObject jsonObj = new JSONObject(json);

        if (json.equals("Unauthorized")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }



        String email = jsonObj.get("email").toString();

        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return () -> email;
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return AUTHENTICATION_SCHEME;
            }
        });



        try {
            User currentUser = userService.findByEmail(email);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED
                    ).entity(new Pair<>("error","Already user with that email")).build());
        }catch (NotFoundException ignored) {}

    }

}
