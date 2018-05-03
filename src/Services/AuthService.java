package Services;

import Core.App;
import Core.AuthRequest;
import Core.Config;
import Entities.Child;
import Entities.User;
import Entities.UserInfos;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;

public class AuthService {

    private Database db;
    private boolean created;

    boolean retour = false;
    public boolean login(String user, String pass){

        created = Database.exists("kidz");

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

                        try {
                            db = Database.openOrCreate("kidz");
                            db.execute("CREATE TABLE IF NOT EXISTS user (id integer, username text, email text, nom text, prenom text);");

                            AuthRequest authRequest = new AuthRequest(){
                                @Override
                                protected void readResponse(InputStream input) throws IOException {
                                    int chr;
                                    String s1 = "";
                                    while ((chr = input.read()) != -1) {
                                        s1 = s1.concat(((char) chr) + "");
                                    }
                                    try {
                                        JSONObject jo = new JSONObject(s1);
                                        int id = jo.getInt("id");
                                        String username = jo.getString("username");
                                        String email = jo.getString("email");
                                        JSONObject userInfos = jo.getJSONObject("infos");
                                        String nom = userInfos.getString("lastname");
                                        String prenom = userInfos.getString("firstname");
                                        db.execute("INSERT INTO user(id, username, email, nom, prenom) " +
                                                "VALUES('" + id + "','" + username + "','" + email + "','" + nom + "','" + prenom + "');");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            };
                            authRequest.setUrl(Config.ServerPath + "api/user/get");
                            NetworkManager.getInstance().addToQueueAndWait(authRequest);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

    public User getConnected(){
        User u = null;
        if(App.isConnected()){
            try {
                db = Database.openOrCreate("kidz");
                Cursor c = db.executeQuery("SELECT * FROM user");
                while (c.next()){
                    Row r = c.getRow();
                    u = new User();
                    u.setId(r.getInteger(0));
                    u.setUsername(r.getString(1));
                    u.setEmail(r.getString(2));
                    UserInfos ui = new UserInfos();
                    ui.setLastname(r.getString(3));
                    ui.setFirstname(r.getString(4));
                    u.setUserInfos(ui);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return u;
    }


}
