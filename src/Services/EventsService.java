package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Event;
import Entities.Photo;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Label;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventsService {


    public ArrayList<Event> findAll() {
        ArrayList<Event> events= new ArrayList<>();
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
                        Event e = new Event();
                        JSONObject jo = ja.getJSONObject(i);
                        e.setId(jo.getInt("id"));
                        e.setType(jo.getString("type"));
                        e.setTitle(jo.getString("title"));
                        e.setLocation(jo.getString("location"));
                        if(jo.has("photo")){
                            JSONObject photo = jo.getJSONObject("photo");
                            Photo p = new Photo();
                            p.setId(photo.getInt("id"));
                            p.setUrl(photo.getString("url"));
                            p.setAlt(photo.getString("alt"));
                            e.setPhoto(p);
                        }
                        e.setAge(jo.getInt("age"));
                        e.setPlaces(jo.getInt("places"));
                        e.setLeft_Places(jo.getInt("left_places"));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.GERMANY);
                        String date = jo.getString("start_date");
                        try {
                            e.setStartDate(formatter.parse(date));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        events.add(e);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/parent/events/list");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    public void updateEvent(int id, int places) {
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
            jpr.put("id",id);
            jpr.put("places",places);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        req.setHttpMethod("POST");
        req.setPost(true);
        req.setRequestBody(jpr.toString());
        System.out.println(jpr.toString());
        req.setUrl(Config.ServerPath + "api/parent/events/reserv");
        NetworkManager.getInstance().addToQueueAndWait(req);


    }


}
