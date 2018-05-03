package Controllers;

import Core.App;
import Entities.Article;
import Entities.Babysitter;
import Forms.ArticleForm;
import Forms.BabysitterForm;
import Services.ArticleService;
import Services.AuthService;
import Services.BabysitterService;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BabysitterController {

    public void init() {
        if (App.isConnected()) {
            ArrayList<Babysitter> babysitters = new BabysitterService().findAll();
            BabysitterForm bf = new BabysitterForm(babysitters);
            bf.show();
        } else {
            new AuthController().init(true);
        }
    }

    public void contacter(String phone) {
        try {
            // Construct data
            String apiKey = "apikey=" + "";
            String message = "&message=" + "Le parent " + new AuthService().getConnected().getFullname() + " souhaite vous contacter";
            String sender = "&sender=" + /*txtsender.getText()*/ "KIDS";
            String numbers = "&numbers=" + "+216" + phone;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                //stringBuffer.append(line);
                JOptionPane.showMessageDialog(null, "message" + line);
            }
            rd.close();

            //return stringBuffer.toString();
        } catch (Exception e) {
            //System.out.println("Error SMS "+e);
            JOptionPane.showMessageDialog(null, e);
            //return "Error "+e;
        }

    }


}

