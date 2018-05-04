package Controllers;

import Core.App;
import Entities.Complaint;
import Entities.Notification;
import Forms.AddComplaintForm;
import Forms.ComplaintForm;
import Services.AuthService;
import Services.ComplaintService;
import Services.NotificationService;
import com.codename1.components.ToastBar;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;

import java.io.IOException;
import java.util.ArrayList;

public class ComplaintController {
    public void init() {
        if (App.isConnected()) {
            ArrayList<Notification> notifications = new NotificationService().findAll(new AuthService().getConnected());
            System.out.println(notifications.toString());

            for (int i = 0; i < new NotificationService().findAll(new AuthService().getConnected()).size(); i++) {

                if (notifications.get(i).getState() == false) {

                    Media video = null;
                    try {
                        video = MediaManager.createMedia("src\\notification.mp3", true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    video.play();
                    ToastBar.showMessage("Vous avez une nouvelle notification", FontImage.MATERIAL_ANNOUNCEMENT, 20000, e -> {

                        System.out.println("salut");
                        new NotificationController().init();
                    });


                    notifications.get(i).setState(true);
                    new NotificationService().updateState(notifications.get(i));
                }
            }


            ArrayList<Complaint> complaints = new ComplaintService().findAll(new AuthService().getConnected());
            ComplaintForm bf = new ComplaintForm(complaints);
            bf.show();
        } else {
            new AuthController().init(true);
        }
    }

    public void addComplaint() {
        if (App.isConnected()) {

            AddComplaintForm bf = new AddComplaintForm();
            bf.show();
        } else {
            new AuthController().init(true);
        }
    }
}
