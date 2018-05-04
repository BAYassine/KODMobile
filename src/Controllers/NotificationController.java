package Controllers;

import Core.App;
import Entities.Complaint;
import Entities.Notification;
import Forms.ComplaintForm;
import Forms.NotificationForm;
import Services.AuthService;
import Services.ComplaintService;
import Services.NotificationService;

import java.util.ArrayList;

public class NotificationController {


    public void init() {
        if (App.isConnected()) {
            ArrayList<Notification> notifications = new NotificationService().findAll(new AuthService().getConnected());
            NotificationForm bf = new NotificationForm(notifications);
            bf.show();
        } else {
            new AuthController().init(true);
        }
    }
}
