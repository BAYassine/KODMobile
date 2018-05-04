package Controllers;

import Core.App;
import Entities.Child;
import Entities.ChildGame;
import Forms.ActivityForm;
import Forms.HomeForm;
import Forms.KidsForm;
import Forms.LanguageForm;
import Services.KidsService;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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
        Pair<Child, List<ChildGame>> data = new KidsService().findChildGames(id);
        ActivityForm af = new ActivityForm(data.getValue(), data.getKey());
        af.show();
    }

    public void learnLanguage() {
        LanguageForm lf = new LanguageForm();
        lf.show();
    }
}
