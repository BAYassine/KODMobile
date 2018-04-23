package Forms;

import Entities.Game;
import com.codename1.ui.*;
import com.codename1.ui.layouts.FlowLayout;

import java.io.IOException;
import java.util.ArrayList;

public class GamesForm  extends Form{

    public GamesForm(ArrayList<Game> games){
        super();
        for (Game game : games) {
            System.out.println(game.getName());
            Image img = null;
            try {
                img = Image.createImage("/assets/images/default-game.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Container c = new Container(new FlowLayout(Component.LEFT, Component.CENTER));
            c.add(img);
            c.add(new Label(game.getName()));
            this.add(c);
        }
    }

}
