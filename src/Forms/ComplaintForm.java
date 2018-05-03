package Forms;

import Components.Card;
import Controllers.BabysitterController;
import Controllers.ComplaintController;
import Core.App;
import Core.ImageExplorer;
import Entities.Babysitter;
import Entities.Complaint;
import Services.AuthService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class ComplaintForm extends Form{
    public ComplaintForm(ArrayList<Complaint> complaints)

    {
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);



        for (Complaint complaint : complaints) {
            Card card = new Card();
            Container main = new Container(BoxLayout.y());
            card.setTitle(complaint.getDate().toString());
            System.out.println(complaint.getDate());
            Label fullname = new Label(new AuthService().getConnected().getFullname());
            Label description = new Label("Contenu: " + complaint.getDescription());
            Label date = new Label("Date: " + complaint.getDate().toString());
            Label state = new Label("Etat: " + complaint.getState());


            main.add(fullname);
            main.add(description);
            main.add(date);
            main.add(state);


            card.addComponents(main);
            this.add(card);
        }
        Button ajouter = new Button("Ajouter une réclamation");
        ajouter.addPointerPressedListener(
                e -> new ComplaintController().addComplaint()
        );


        this.add(ajouter);


    }

    }

