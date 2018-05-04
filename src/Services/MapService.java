package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.School;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MapService {

    public ArrayList<School> findAll() {
        ArrayList<School> Schools = new ArrayList<>();
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
                        School v = new School();
                        JSONObject jo = ja.getJSONObject(i);
                        v.setXschool(jo.getDouble("_xschool"));
                        v.setYschool(jo.getDouble("_yschool"));
                        Schools.add(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/map");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Schools;
    }


}
