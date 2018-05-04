package Components;

import Core.App;
import com.codename1.ui.Label;

public class KLabel extends Label{

    public KLabel(String text){
        super(text);
        getAllStyles().setFont(App.theme.getFont("main font"));
    }

}
