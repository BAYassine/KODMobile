package Forms;

import Controllers.VideosController;
import Core.App;
import Entities.Video;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;


public class VideoForm extends Form {

    public VideoForm(ArrayList<Video> videos) {

        super("videos",BoxLayout.y());
        this.setToolbar(App.sidemenu);
        Container content = new Container(BoxLayout.y());
        //Image photo = null ;
        for(Video video : videos){

            Container c = new Container (BoxLayout.y());
            Label url  = new Label ("videourl : " +video.getTitre());

            url.addPointerPressedListener(e -> new VideosController().showVideo(video.getUrl()));

            Button btnpartage= new Button("Partage fb");

            c.addAll(url);
            c.add(btnpartage);

            btnpartage.addActionListener((l) -> {
                String token = "EAAayMZCipBkEBAKPvD4gVIZCzZCezxnjmFOaLRDwJDvfmQs21WntS5w3fIUbIvY1u36MJx9YAAt63i074sdwvry9oEh9n2Wohoe0K9xBZC6YPzgnZAi941MMnUwuKMOL50BgrqZCHFULBVB0PNQZA7WWR9IOrtRC9HZBHQQZAtkYGXPZAjXV7bCfMFSiSKjm8KJHsZD";
                ConnectionRequest request = new ConnectionRequest();
                request.setUrl("https://graph.facebook.com/v2.12/me/feed");
                request.addRequestHeader("Authorization", "Bearer " + token);
                request.addResponseListener(e -> {
                    System.out.println(new String(request.getResponseData()));
                });
                request.addArgument("message", ""+video.geturlyout());
                request.setPost(true);
                request.setHttpMethod("POST");
                NetworkManager.getInstance().addToQueueAndWait(request);
//                 FacebookClient fb = new DefaultFacebookClient(token);
//                FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", "test"));
//                System.out.println("fb.com" + r.getId());
            }) ;
           // C.add(photo);
            content.add(c);
        }
        this.getToolbar().addSearchCommand(e -> {
            String t = (String)e.getSource();
            if(t == null) {
                t = "";
            } else {
                t = t.toLowerCase();
            }
            for(int i = 0; i< videos.size(); i++) {
                String finalT = t;
                Component video = content.getComponentAt(i);
                boolean show =  videos.get(i).getTitre().equals(t);
                System.out.println(show);
                video.setVisible(show);
                video.setHidden(!show);
            }
            this.animateLayout(200);
        }, 3);
        this.add(content);




    }
}


