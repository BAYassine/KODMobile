package Forms;

import Components.Card;
import Components.HCard;
import Components.KButton;
import Core.App;
import Core.ImageExplorer;
import Entities.Game;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.io.IOException;
import java.util.ArrayList;

public class GamesForm  extends Form{

    public GamesForm(ArrayList<Game> games){
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);

        for (Game game : games) {
            Card card = new Card();
            Image img;
            if(game.getIcon() != null)
               img = ImageExplorer.getImage(game.getIcon());
            else img = App.theme.getImage("default_game.png");
            card.setImage(img);
            card.setTitle(game.getName());
            KButton cart = new KButton("",true);
            FontImage.setMaterialIcon(cart, FontImage.MATERIAL_ADD_SHOPPING_CART);
            SpanLabel text = new SpanLabel("Idée reçue : “Les enfants uniques sont égoïstes”");
            text.getTextAllStyles().setFont(App.theme.getFont("main font"));
//            card.addComponents(text);
            this.add(card);
        }
    }

}
