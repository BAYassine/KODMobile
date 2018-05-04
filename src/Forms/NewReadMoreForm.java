package Forms;

import Core.App;
import Entities.Event;
import Services.EventsService;
import Services.ReadMoreService;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

public class NewReadMoreForm extends Form {

    public NewReadMoreForm(int id) {
        super();
        this.setToolbar(App.sidemenu);
        TextField nb = new TextField("", "Nombre de participants");
        Container c = new Container(BoxLayout.y());
        Button ok = new Button("Ok");
        ok.addActionListener(e -> {
            new EventsService().updateEvent(id, Integer.parseInt(nb.getText()));
        });
        c.add(nb);
        c.add(ok);
        this.add(c);
    }


}

