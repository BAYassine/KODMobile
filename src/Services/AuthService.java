package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Child;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;

public class AuthService {

    boolean retour = false;
    public boolean login(String user, String pass){
        ConnectionRequest req = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {
                // Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
                if(this.getResponseCode() == 200){
                    try {
                        JSONObject ja = new JSONObject(s);
                        String token = ja.getString("token");
                        AuthRequest.setToken(token);
                        retour = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        req.setPost(true);
        req.setHttpMethod("POST");
        req.addArgument("_username", user);
        req.addArgument("_password", pass);
        req.setUrl(Config.ServerPath + "api/login_check");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return retour;
    }

    public boolean logout(){

        ConnectionRequest req = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {
                // Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets

            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "/logout");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        AuthRequest.setToken("");
        return retour;
    }


}
