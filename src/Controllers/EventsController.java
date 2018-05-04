package Controllers;

import Core.App;
import Entities.Event;
import Forms.EventsForm;
import Services.EventsService;

import java.util.ArrayList;

public class EventsController {

    public void init() {
        if (App.isConnected()) {
            ArrayList<Event> Events = new EventsService().findAll();
            EventsForm ef = new EventsForm(Events);
            ef.show();
        } else {

            new AuthController().init(true);

        }

//        public void showDetails(int id){
//            ArrayList<Event> Event = new EventsService().findAll();
//            ActivityForm af = new ActivityForm(events);
//            af.show();
//        }

    }


}