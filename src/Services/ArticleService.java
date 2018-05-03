package Services;

import Core.AuthRequest;
import Core.Config;
import Entities.*;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ArticleService {

    Article article = null;

    public ArrayList<Article> findAll() {
        ArrayList<Article> articles = new ArrayList<>();
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
                        Article article = new Article();
                        JSONObject jo = ja.getJSONObject(i);
                        article.setId(jo.getInt("id"));
                        article.setTitle(jo.getString("title"));
                        article.setDescription(jo.getString("content"));
                        article.setSrc(jo.getString("src"));
                        article.setSubject(jo.getString("subject"));
                        article.setAuteur(jo.getString("auteur"));
                        JSONObject photo = jo.getJSONObject("image");


                        Photo p = new Photo();
                        p.setId(photo.getInt("id"));
                        p.setUrl(photo.getString("url"));
                        p.setAlt(photo.getString("alt"));
                        article.setPhoto(p);

                        articles.add(article);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/aide/article/read");
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public Article findArticle(int id) {
        AuthRequest req = new AuthRequest();
        req.addResponseListener(e -> {
            // Etape 1 : Conversion de la reponse en String
            String s = new String(req.getResponseData());
            // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
            try {
                article = new Article();
                JSONObject ja = new JSONObject(s);
                article.setId(id);
                article.setTitle(ja.getString("title"));
                article.setDescription(ja.getString("content"));
                article.setSrc(ja.getString("src"));
                article.setSubject(ja.getString("subject"));
                article.setAuteur(ja.getString("auteur"));
                JSONObject photo = ja.getJSONObject("image");

                Photo p = new Photo();
                p.setId(photo.getInt("id"));
                p.setUrl(photo.getString("url"));
                p.setAlt(photo.getString("alt"));
                article.setPhoto(p);

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        });

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/aide/article/more/" + id);
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return article;
    }


}
