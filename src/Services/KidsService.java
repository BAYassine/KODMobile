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
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KidsService {

    private boolean correct = false;
    private String result = "";

    public ArrayList<Child> findMyKids() {
        ArrayList<Child> kids = new ArrayList<>();
        AuthRequest req = new AuthRequest() {
        };
        req.addResponseListener(e -> {
            String response = new String(req.getResponseData());
            // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets

            if (req.getResponseCode() == 200)
                try {
                    JSONArray ja = new JSONArray(response);
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
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            else if (req.getResponseCode() == 401)
                System.out.println(req.getResponseErrorMessage());
        });

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/parent/kids");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return kids;
    }

    public Pair<Child, List<ChildGame>> findChildGames(int id) {
        ArrayList<ChildGame> childGames = new ArrayList<>();
        Child c = new Child();
        AuthRequest req = new AuthRequest();
        req.addResponseListener(e -> {

            String response = new String(req.getResponseData());
            if (req.getResponseCode() == 200)
                try {
                    JSONObject jo = new JSONObject(response);
                    // Dans ma réponse plays est un tableau donc je le met dans JSONArray
                    JSONArray plays = jo.getJSONArray("plays");
                    // Child est un objet -> JSONObject
                    JSONObject jc = jo.getJSONObject("child");
                    c.setId(jc.getInt("id"));
                    c.setName(jc.getString("name"));
                    JSONArray jbg = jc.getJSONArray("blocked_g");
                    for (int i = 0; i < jbg.length(); i++) {
                        int gameid = jbg.getInt(i);
                        c.getBlockedGames().add(gameid);
                    }

                    //la suite est semblable à la création des objets dans les services du Projet javafx

                    for (int i = 0; i < plays.length(); i++) {
                        JSONObject jcg = (JSONObject) plays.get(i);
                        ChildGame cg = new ChildGame();
                        Game g = new Game();
                        JSONObject jg = (JSONObject) jcg.get("game");
                        g.setId(jg.getInt("id"));
                        g.setName(jg.getString("name"));
                        Photo p = null;
                        if (jg.has("icon")) {
                            JSONObject jp = jg.getJSONObject("icon");
                            p = new Photo();
                            p.setId(jp.getInt("id"));
                            p.setUrl(jp.getString("url"));//l"url et l'id sont nécessaires pour afficher l'image
                        }
                        g.setIcon(p);
                        cg.setGame(g);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.GERMANY);
                        String date = jcg.getString("date");
                        cg.setDate(formatter.parse(date));
                        String duraion = jcg.getString("duration");
                        cg.setDuration(formatter.parse(duraion));
                        cg.setChildId(c.getId());
                        childGames.add(cg);
                    }
                } catch (JSONException | ParseException ex) {
                    ex.printStackTrace();
                }
            else if (req.getResponseCode() == 401)
                System.out.println(req.getResponseErrorMessage());
        });

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/parent/kids/activity/" + id);
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return new Pair<>(c, childGames);
    }

    public void blockGame(int cid, int gid) {
        ArrayList<Child> kids = new ArrayList<>();
        AuthRequest req = new AuthRequest();
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/parent/block?child=" + cid + "&game=" + gid);
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public boolean spellCheck(String word, String file) {
        correct = false;
        result = "";
        MultipartRequest request = new MultipartRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                try {
                    JSONObject jo = new JSONObject(s);
                    int jobId = jo.getInt("id");
                    int time = 0;
                    if (!jo.isNull("check_wait"))
                        time = jo.getInt("check_wait");
                    try {
                        Thread.sleep((time + 1) * 1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    ConnectionRequest request1 = new ConnectionRequest() {
                        @Override
                        protected void readResponse(InputStream input1) throws IOException {
                            int chr;
                            String s1 = "";
                            while ((chr = input1.read()) != -1) {
                                s1 = s1.concat(((char) chr) + "");
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(s1);
                                JSONArray wordsArray = jsonObject.getJSONArray("words");
                                for (int j = 0; j < wordsArray.length(); j++) {
                                    JSONObject wordObject = (JSONObject) wordsArray.get(j);
                                    if (j > 0 && j < wordsArray.length() - 1)
                                        result += " ";
                                    if (j < wordsArray.length() - 1)
                                        result += wordObject.getString("name");
                                }
                                correct = (result.compareToIgnoreCase(word) == 0);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    };
                    request1.setUrl("https://api.speechmatics.com/v1.0/user/42084/jobs/" + jobId + "/transcript?auth_token=YmM5NmZhYWItN2Y5NC00Y2U1LTkyMmYtYTFlNDNhY2FhZDU4");
                    request1.setHttpMethod("GET");
                    request1.setPost(false);
                    NetworkManager.getInstance().addToQueueAndWait(request1);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };
        request.setUrl("https://api.speechmatics.com/v1.0/user/42084/jobs/?auth_token=YmM5NmZhYWItN2Y5NC00Y2U1LTkyMmYtYTFlNDNhY2FhZDU4");
        request.addArgument("model", "fr");
        request.addArgument("notification", "none");
        request.setHttpMethod("POST");
        request.setPost(true);
        try {
            request.addData("data_file", file, "audio/*");
            request.setFilename("data_file", "record.wav");
            NetworkManager.getInstance().addToQueueAndWait(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return correct;
    }

}
