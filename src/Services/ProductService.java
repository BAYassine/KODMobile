package Services;

import Core.App;
import Core.AuthRequest;
import Core.Config;
import Entities.*;
import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductService {

    private Database db;
    private boolean created;

    public ArrayList<Product> findAll() {

        ArrayList<Product> products = new ArrayList<>();
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                // Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                System.out.println(s);
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
                try {
                    JSONArray ja = new JSONArray(s);
                    for (int i = 0; i < ja.length(); i++) {
                        Product p = new Product();
                        JSONObject jo = ja.getJSONObject(i);
                        p.setName(jo.getString("name"));
                        p.setId(jo.getInt("id"));
                        p.setDescription(jo.getString("description"));
                        p.setPrice(jo.getInt("price"));
                        JSONObject photo = jo.getJSONObject("img");
                        Photo ph = new Photo();
                        ph.setId(photo.getInt("id"));
                        ph.setUrl(photo.getString("url"));
                        ph.setAlt(photo.getString("alt"));
                        p.setImg(ph);
                        products.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/products/category/2");
        req.addRequestHeader("Authorization", "Bearer " + AuthRequest.getToken());
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }

    public void addFavoris(Product p) {
        created = Database.exists("2cinfos");

        try {
            db = Database.openOrCreate("2cinfos");
            db = Database.openOrCreate("Drop Table favorois");
            db.execute("CREATE TABLE IF NOT EXISTS favoris (name text, desc text);");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            db.execute("INSERT INTO favoris(id, desc) VALUES('" + p.getName() + "', '" + p.getDescription() + "')");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public void Panier() {
        JSONArray ja = new JSONArray();
        for (LigneCommande la : App.panier) {
            JSONObject jo = new JSONObject();
            try {
                JSONObject jpr = new JSONObject();
                jpr.put("id", la.getProduct().getId());
                jo.put("product", jpr);
                jo.put("quantity", la.getQuantite());
                ja.put(jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            AuthRequest authRequest = new AuthRequest();
            authRequest.setHttpMethod("POST");
            authRequest.setPost(true);
            authRequest.setRequestBody(ja.toString());
            authRequest.setUrl(Config.ServerPath + "api/orders/create");
            NetworkManager.getInstance().addToQueueAndWait(authRequest);
        }

    }

    public ArrayList<Product> selectFavoris() {
        Product p = null;
        ArrayList<Product> myList = new ArrayList<>();
        if (App.isConnected()) {
            try {
                db = Database.openOrCreate("2cinfos");
                Cursor c = db.executeQuery("SELECT * FROM favoris");
                while (c.next()) {
                    Row r = c.getRow();
                    p = new Product();
                    p.setName(r.getString(0));
                    p.setDescription(r.getString(1));
                    myList.add(p);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myList;
    }

    public ArrayList<Commande> findOrders() {

        ArrayList<Commande> orders = new ArrayList<>();
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                // Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                System.out.println(s);
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
                try {
                    JSONArray ja = new JSONArray(s);
                    for (int i = 0; i < ja.length(); i++) {
                        Commande c = new Commande();
                        JSONObject jo = ja.getJSONObject(i);
                        c.setUser_id(jo.getInt("id"));
                        c.setId(jo.getInt("id"));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.GERMANY);
                        String date = jo.getString("date");
                        c.setDate(formatter.parse(date));
                        c.setId(c.getId());
                        System.out.println(c.getId());
                        c.setTotal(jo.getInt("total"));
                        orders.add(c);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/confirm_orders");
        req.addRequestHeader("Authorization", "Bearer " + AuthRequest.getToken());
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return orders;
    }


    public ArrayList<Commentaire> findComment(int idP) {
        ArrayList<Commentaire> listComment = new ArrayList<>();
        AuthRequest req = new AuthRequest() {

            @Override
            protected void readResponse(InputStream input) throws IOException {

                // Etape 1 : Conversion de la reponse en String
                int chr;
                String s = "";
                while ((chr = input.read()) != -1) {
                    s = s.concat(((char) chr) + "");
                }
                System.out.println(s);
                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
                try {

                    JSONArray ja = new JSONArray(s);
                    for (int i = 0; i < ja.length(); i++) {
                        Commentaire comment = new Commentaire();
                        JSONObject jo = ja.getJSONObject(i);
                        JSONObject jth = jo.getJSONObject("thread");
                        comment.setId_commentaire(jo.getInt("id"));
                        comment.setAncestors("" + jo.get("ancestors").toString());
                        System.out.println(".actionPerformed() : " + comment.getAncestors());
                        comment.setBody(jo.get("body").toString());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.GERMANY);
                        String date = jo.getString("created_at");
                        comment.setCreated_at(formatter.parse(date));
                        comment.setId_user(Integer.parseInt(jo.get("id").toString()));
                        Entities.Thread th = new Entities.Thread();
                        th.setId(jth.getString("id"));
                        th.setNumComments(jth.getInt("num_comments"));
                        comment.setIdP(th);
                        Product p = new Product();
                        th.setId(p.getId() + "");
                        listComment.add(comment);

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(Config.ServerPath + "api/comments/" + idP);
        req.addRequestHeader("Authorization", "Bearer " + AuthRequest.getToken());
        //Envoyer la requête et attender la reponse
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listComment;
    }


    public void payer() {


        ConnectionRequest c = new ConnectionRequest();
        c.setHttpMethod("POST");
        c.setPost(true);

        c.addArgument("amount", "2000");
        c.addArgument("currency", "usd");
        c.addArgument("description", "Charge for Abir lahmer");
        c.addArgument("source", "tok_mastercard");
        c.setContentType("application/x-www-form-urlencoded");
        c.addRequestHeader("Authorization", "Basic " + Base64.encodeNoNewline(("sk_test_gYEImAomVJT9zdXQkqANFHH0:").getBytes()));
        c.addResponseListener(e -> {
            String s = new String(c.getResponseData());
            System.out.println(s);
        });
        c.setUrl("https://api.stripe.com/v1/charges");
        NetworkManager.getInstance().addToQueueAndWait(c);
        App.panier.clear();
        ToastBar.showMessage("Achat effectuée avec succes  Merci", FontImage.MATERIAL_ANNOUNCEMENT, 20000);

    }


}
//
//    public int findThread(int idP) {
//        ArrayList<Entities.Thread> listThread = new ArrayList<>();
//        AuthRequest req = new AuthRequest() {
//
//            @Override
//            protected void readResponse(InputStream input) throws IOException {
//
//                // Etape 1 : Conversion de la reponse en String
//                int chr;
//                String s = "";
//                while ((chr = input.read()) != -1) {
//                    s = s.concat(((char) chr) + "");
//                }
//                System.out.println(s);
//                // Etape 2 : Conversion de la chaine json en Objet ou Tableau d'objets
//                try {
//                    JSONArray ja = new JSONArray(s);
//                    for (int i = 0; i < ja.length(); i++) {
//                        Entities.Thread th = new Entities.Thread();
//                        JSONObject jo = ja.getJSONObject(i);
//                        th.setNumComments(Integer.parseInt(jo.get("num_comments").toString()));
//                        listThread.add(th);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        };
//        req.setPost(false);
//        req.setHttpMethod("GET");
//        req.setUrl(Config.ServerPath + "api/commentsnbre/" + idP);
//        req.addRequestHeader("Authorization", "Bearer " + AuthRequest.getToken());
//        //Envoyer la requête et attender la reponse
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        int i = 0;
//        for (Entities.Thread r : listThread) {
//            return r.getNumComments();
//
//        }
//
//             return i;
//    }

//}
