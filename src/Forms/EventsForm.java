package Forms;


import Controllers.ReadMoreController;
import Core.App;
import Core.ImageExplorer;
import Entities.Event;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.util.ArrayList;



public class EventsForm  extends Form {


    public EventsForm(ArrayList<Event> events) {
        super("Mes evenements" ,BoxLayout.y());
        this.setToolbar(App.sidemenu);

        for (Event event : events) {

            Container c = new Container();

            Image img = null;
            if(event.getPhoto() != null)
                img = ImageExplorer.getImage(event.getPhoto());

            else img = App.theme.getImage("round.png");
            c.add(img);
            Container infos = new Container(BoxLayout.x());
            Label d = new Label();
            Label title = new Label(event.getTitle());
            c.add(new Label(event.getTitle()));

            infos.add(new Label(String.valueOf(event.getStartDate())));

            c.add(infos);
            this.add(c);
            title.addPointerPressedListener(e -> {
                new ReadMoreController().showDetails(event.getId());
                System.out.println(event.getId());
            }
            );
            c.setLeadComponent(title);


        }
    }


}