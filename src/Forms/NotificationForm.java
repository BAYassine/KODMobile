package Forms;

import Controllers.ComplaintController;
import Controllers.NotificationController;
import Core.App;
import Entities.Complaint;
import Entities.Notification;
import Services.AuthService;
import Services.NotificationService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class NotificationForm extends Form {
    public NotificationForm(ArrayList<Notification> notifications)

    {
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);
        for (Notification notification : notifications) {

            Container main = new Container(BoxLayout.y());
            Container c = new Container(BoxLayout.y());
            Container c1 = new Container(BoxLayout.x());
            SpanLabel message = new SpanLabel(notification.getMessage());
            //Toolbar toolbar= new Toolbar();
            c.add(message);

            Button delete = new Button();
            FontImage.setMaterialIcon(delete,FontImage.MATERIAL_CANCEL);
            delete.addPointerPressedListener(
                    e -> {
                        new NotificationService().deleteNotification(notification.getId());
                        new NotificationController().init();
                        Dialog.show("Succès", "La notification a été supprimée avec succès", "OK", null);
                    });

            c.add(delete);
            c1.add(c);
           // c.add(tb);
            main.add(c1);


            this.add(main);
        }


    }
}
