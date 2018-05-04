package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Event;

import Entities.Reservation;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ReservationService {


    private Event e;

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservations= new ArrayList<>();
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
                        Reservation r = new Reservation();
                        JSONObject jo = ja.getJSONObject(i);

                        r.setId(jo.getInt("id"));
                        r.setId(jo.getInt("event_id"));
                        r.setUser_id(jo.getInt("user_id"));
                        r.setParticipants(jo.getInt("participants"));


                        reservations.add(r);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "/parent/events/reserv/{id}");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;

}

}
