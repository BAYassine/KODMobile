package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.*;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NotificationService {
    public ArrayList<Notification> findAll(User user) {
        ArrayList<Notification> notifications = new ArrayList<>();
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
                try {
                    JSONArray ja = new JSONArray(s);
                    for (int i = 0; i < ja.length(); i++) {
                        Notification g = new Notification();
                        JSONObject jo = ja.getJSONObject(i);
                        g.setId(jo.getInt("id"));
                        g.setUser(user);
                        g.setState(jo.getBoolean("state"));
                        g.setMessage(jo.getString("message"));


                        notifications.add(g);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);

        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/complaint/notification");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notifications;
    }

    public void updateState(Notification n) {
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
            jpr.put("id",n.getId());
            jpr.put("message",n.getId());
            jpr.put("state",true);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        req.setHttpMethod("POST");
        req.setPost(true);
        req.setRequestBody(jpr.toString());
        System.out.println(jpr.toString());
        req.setUrl(Config.ServerPath + "api/Notification/update");
        NetworkManager.getInstance().addToQueueAndWait(req);


    }
    public Notification deleteNotification(int id) {
        Notification notification = new Notification();
        AuthRequest req = new AuthRequest(){

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
                    JSONObject ja = new JSONObject(s);

                    Notification g = new Notification();

                    g.setId(id);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };


        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/Notification/delete/" + id);
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notification;
    }




}
