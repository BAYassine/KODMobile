package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.Child;
import Entities.ChildGame;
import Entities.Game;
import Entities.Photo;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class KidsService {

    public ArrayList<Child> findMyKids() {
        ArrayList<Child> kids = new ArrayList<>();
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                 //Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets

                if(this.getResponseCode() == 200)
                    try {
                        JSONArray ja = new JSONArray(s);
                        for (int i = 0; i < ja.length(); i++) {
                            Child c = new Child();
                            JSONObject jo = ja.getJSONObject(i);
                            c.setName(jo.getString("name"));
                            c.setId(jo.getInt("id"));
                            c.setAge(jo.getInt("age"));
                            JSONObject photo = jo.getJSONObject("photo");
                            Photo p = new Photo();
                            p.setId(photo.getInt("id"));
                            p.setUrl(photo.getString("url"));
                            p.setAlt(photo.getString("alt"));
                            c.setPhoto(p);
                            kids.add(c);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                else if (this.getResponseCode() == 401)
                    System.out.println(this.getResponseErrorMessage());
            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/parent/kids");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return kids;
    }

    public ArrayList<ChildGame> findChildGames(int id) {
        ArrayList<ChildGame> childGames = new ArrayList<>();
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                //Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets

                if(this.getResponseCode() == 200)
                    try {
                        JSONObject jo = new JSONObject(s);
                            // Dans ma réponse plays est un tableau donc je le met dans JSONArray
                            JSONArray plays = jo.getJSONArray("plays");
                            // Child est un objet -> JSONObject
                            JSONObject jc = jo.getJSONObject("child");
                            Child c = new Child();
                            c.setId(jc.getInt("id"));
                            c.setName(jc.getString("name"));

                            //la suite est semblable à la création des objets dans les services du Projet javafx

                            for(int i =0; i< plays.length(); i++){
                                JSONObject jcg = (JSONObject) plays.get(i);
                                ChildGame cg = new ChildGame();
                                Game g = new Game();
                                JSONObject jg = (JSONObject) jcg.get("game");
                                g.setId(jg.getInt("id"));
                                g.setName(jg.getString("name"));
                                Photo p = null;
                                if (jg.has("icon")){
                                    JSONObject jp = jg.getJSONObject("icon");
                                    p = new Photo();
                                    p.setId(jp.getInt("id"));
                                    p.setUrl(jp.getString("url"));//l"url et l'id sont nécessaires pour afficher l'image
                                }
                                g.setIcon(p);
                                cg.setGame(g);
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.GERMANY);
                                String date = jcg.getString("date");
                                String duraion = jcg.getString("duration");
                                cg.setDate(formatter.parse(date));
                                cg.setDuration(formatter.parse(duraion));
                                cg.setChildId(c.getId());
                                childGames.add(cg);
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                else if (this.getResponseCode() == 401)
                    System.out.println(this.getResponseErrorMessage());
            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/parent/kids/activity/" + id);
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return childGames;
    }
}
