package Forms;

import Controllers.GamesController;
import Core.App;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.io.IOException;

public class HomeForm  extends Form{

    public HomeForm() {
        super(BoxLayout.y());
        Image img = App.theme.getImage("slider-1.jpg");
        img = img.scaledWidth(300);
//        ImageViewer iv = new ImageViewer(img);
//        iv.set
        Toolbar tb = this.getToolbar();
        tb.addCommandToSideMenu("Jeux", null, e -> new GamesController().init());
        this.add(img);
        this.add(new Label("Na3ref t7ebbouhom hethom XD"));
    }

}
