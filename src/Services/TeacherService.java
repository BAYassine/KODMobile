package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Photo;
import Entities.Teacher;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TeacherService {


    public ArrayList<Teacher> findAll() {
        ArrayList<Teacher> Teachers = new ArrayList<>();
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
                        Teacher T = new Teacher();
                        JSONObject jo = ja.getJSONObject(i);
                        T.setId(jo.getInt("id"));
                        T.setName(jo.getString("name"));
                        T.setPhone(jo.getInt("phone"));
                        JSONObject photo = jo.getJSONObject("photo");
                        Photo p = new Photo();
                        p.setId(photo.getInt("id"));
                        p.setUrl(photo.getString("url"));
                        p.setAlt(photo.getString("alt"));
                        T.setPhoto(p);
                        Teachers.add(T);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/teachers/read");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Teachers;
    }


    public Teacher findteacherid(int id) {
        Teacher t = new Teacher();
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
                    t.setId(ja.getInt("id"));
                    t.setPhone(ja.getInt("phone"));
                    t.setName(ja.getString("name"));
                    t.setAccount(ja.getString("src"));
                    t.setExperience(ja.getString("auteur"));
                    JSONObject photo = ja.getJSONObject("image");
                    Photo p = new Photo();
                    p.setId(photo.getInt("id"));
                    p.setUrl(photo.getString("url"));
                    p.setAlt(photo.getString("alt"));
                    t.setPhoto(p);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/more/" + id);
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
    }


//title.addPointerPressedListener(e -> new ArticleController().moreArticle(Teacher.getId()));


            }
