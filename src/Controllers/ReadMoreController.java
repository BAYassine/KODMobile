package Controllers;

import Core.App;
import Entities.Event;
import Forms.ActivityForm;
import Forms.EventsForm;
import Forms.ReadMoreForm;
import Services.ReadMoreService;

import java.util.ArrayList;

public class ReadMoreController {

    public void init() {

        if (App.isConnected()) {
            ArrayList<Event> Events = new ReadMoreService().findAll();
            EventsForm rm = new EventsForm(Events);
            rm.show();
        } else {
            new AuthController().init(true);
        }

    }
    public void showDetails(int id){
        //Event event = new ReadMoreService().find(id);
        ReadMoreForm af = new ReadMoreForm(id);
        af.show();
    }
}