package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Article;
import Entities.Babysitter;
import Entities.Notification;
import Entities.Photo;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BabysitterService {
    public ArrayList<Babysitter> findAll() {
        ArrayList<Babysitter> babysitters = new ArrayList<>();
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                // Etape 1 : Conversion de la reponse en String
                int chr ;
                String s ="";
                while ((chr = input.read())!= -1){
                    s = s.concat(((char) chr) + "");
                }
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
                try {
                    JSONArray ja = new JSONArray(s);
                    for (int i = 0; i< ja.length(); i++){
                        Babysitter g = new Babysitter();
                        JSONObject jo = ja.getJSONObject(i);
                        g.setId(jo.getInt("id"));
                        g.setAddress(jo.getString("address"));
                        g.setFirstName(jo.getString("first_name"));
                        g.setLastName(jo.getString("last_name"));
                        g.setPhone(jo.getString("phone"));
                        g.setPrice(jo.getInt("price"));
                        g.setState(jo.getBoolean("state"));
                        JSONObject photo = jo.getJSONObject("image");


                        Photo p = new Photo();
                        p.setId(photo.getInt("id"));
                        p.setUrl(photo.getString("url"));
                        p.setAlt(photo.getString("alt"));
                        g.setPhoto(p);

                        babysitters.add(g);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);

        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/Babysitter/read");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return babysitters;
    }

    public void updateState(Babysitter b) {
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                // Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets


//                    jpr.put("id",c.getId());
            }
        };

        JSONObject jpr = new JSONObject();

        try {
            jpr.put("id",b.getId());
            jpr.put("state",false);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        req.setHttpMethod("POST");
        req.setPost(true);
        req.setRequestBody(jpr.toString());
        System.out.println(jpr.toString());
        req.setUrl(Config.ServerPath + "api/Babysitter/update");
        NetworkManager.getInstance().addToQueueAndWait(req);


    }








}
