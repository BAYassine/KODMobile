package Controllers;

import Core.App;
import Entities.Child;
import Entities.ChildGame;
import Forms.ActivityForm;
import Forms.HomeForm;
import Forms.KidsForm;
import Services.KidsService;

import java.util.ArrayList;

public class KidsController {

    public void init(){
        if(App.isConnected()){
            ArrayList<Child> kids = new KidsService().findMyKids();
            KidsForm kf = new KidsForm(kids);
            kf.show();
        }
        else {
            new AuthController().init(true);
        }
    }

    public void showActivity(int id){
        ArrayList<ChildGame> childGames = new KidsService().findChildGames(id);
        ActivityForm af = new ActivityForm(childGames);
        af.show();
    }

}
