package Core;

import com.codename1.io.ConnectionRequest;

public class AuthRequest extends ConnectionRequest {

    private static String token = "";

    public AuthRequest(){
        super();
        this.addRequestHeader("Authorization", "Bearer " + token);
    }

    public static void setToken(String token) {
        AuthRequest.token = token;
    }

    public static String getToken() {
        return token;
    }
}
