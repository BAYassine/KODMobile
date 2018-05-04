package Forms;

import Controllers.KidsController;
import Core.App;
import Core.ImageExplorer;
import Entities.Event;
import Services.EventsService;
import Services.ReadMoreService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.painter.BackgroundPainter;

import java.util.ArrayList;

public class ReadMoreForm extends Form {


    public ReadMoreForm(int id) {
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);
        Button add = new Button("Reserver");
        add.addActionListener(e -> {

            NewReadMoreForm nrm = new NewReadMoreForm(id);
            nrm.show();
        });

        FontImage.setMaterialIcon(add, FontImage.MATERIAL_ADD);
        BackgroundPainter bg = new BackgroundPainter(add);
        this.add(add);
        Event event= new ReadMoreService().find(id);




            Image img = null;
            if( event.getPhoto() != null)
                img = ImageExplorer.getImage(event.getPhoto());
            else img = App.theme.getImage("round.png");
            add(img);



            Label title = new Label(event.getTitle());
            add(title);

            SpanLabel type = new SpanLabel("Description: " +event.getType());
            add(type);

             Label lieu = new Label("Lieu: " +event.getLocation());
             add(lieu);

             Label places  = new Label("Nombre de Participants: " +event.getPlaces());
             add(places);

             Label dispo = new Label("Places disponibles: " +event.getLeft_Places());
             add(dispo);

            Label date = new Label("Date: " +event.getStartDate());
            add(date);



             Label age = new Label("Age: " +event.getAge());
             add(age);








             Label y = new Label("date:" + String.valueOf(event.getStartDate()));
             add(y);



            Label ar = new Label(String.valueOf(event.getPlaces()));
            add(ar);






        Button b = new Button("Reserver");
        b.addActionListener(actionEvent -> {

        });
    }
}


