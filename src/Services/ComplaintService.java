package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Babysitter;
import Entities.Complaint;
import Entities.Photo;
import Entities.User;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ComplaintService {
    public ArrayList<Complaint> findAll(User user) {
        ArrayList<Complaint> complaints = new ArrayList<>();
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
                        Complaint g = new Complaint();
                        JSONObject jo = ja.getJSONObject(i);
                        g.setId(jo.getInt("id"));
                        g.setDescription(jo.getString("description"));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                        String date=jo.getString("date");
                        g.setDate(formatter.parse(date));
                        //g.setCategory(jo.getString("category"));
                        g.setParent(user);
                        g.setState(jo.getString("state"));
                        g.setSubject(jo.getString("subject"));


                        complaints.add(g);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);

        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/Complaint/read");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return complaints;
    }

    public void addComplaint(Complaint c) {
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
        JSONObject jo= new JSONObject();
        JSONObject ju= new JSONObject();
        JSONObject ji= new JSONObject();
        try {
            jpr.put("id",c.getId());


            jo.put("id",c.getCategory().getId());
            jo.put("name",c.getCategory().getName());
            jo.put("type",c.getCategory().getType());
            jpr.put("category", jo);


            jpr.put("description", c.getDescription());
            jpr.put("subject", c.getSubject());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            jpr.put("date", formatter.format(c.getDate()));
            jpr.put("state", c.getState());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        req.setHttpMethod("POST");
        req.setPost(true);
        req.setRequestBody(jpr.toString());
        System.out.println(jpr.toString());
        req.setUrl(Config.ServerPath + "api/Parent/Complaint/create");
        NetworkManager.getInstance().addToQueueAndWait(req);


    }
}






