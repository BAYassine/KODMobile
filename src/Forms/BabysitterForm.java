package Forms;

import Components.Card;
import Components.KButton;
import Controllers.ArticleController;
import Controllers.BabysitterController;
import Core.App;
import Core.ImageExplorer;
import Entities.Article;
import Entities.Babysitter;
import Services.BabysitterService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class BabysitterForm extends Form {
    public BabysitterForm(ArrayList<Babysitter> babysitters)

    {
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);
        for (Babysitter babysitter : babysitters) {
            Card card = new Card(true);
            Container main = new Container(BoxLayout.y());
            Container c = new Container(BoxLayout.y());
            Container c1 = new Container(BoxLayout.x());


            Label phone = new Label("Telephone : " + babysitter.getPhone());
            Label price = new Label("Prix : " + babysitter.getPrice() + "");
            Label address = new Label("Adresse : " + babysitter.getAddress());
            KButton contacter = new KButton("Contacter");
            contacter.setAlignment(Component.CENTER);
            Image photo = null;
            if (babysitter.getPhoto() != null)
                photo = ImageExplorer.getImage(babysitter.getPhoto());
            contacter.addPointerPressedListener(e -> {
                if (babysitter.getState()) {
                    new BabysitterController().contacter(babysitter.getPhone());
                    babysitter.setState(false);
                    new BabysitterService().updateState(babysitter);

                } else
                    Dialog.show("Erreur", "Le babysitter est occupé vous ne pourriez pas le contacter", "OK", null);

            });
            card.setTitle(babysitter.getFirstName() + " " + babysitter.getLastName());


            c.add(phone);
            c.add(price);
            c.add(address);
            c.add(contacter);
            c1.add(c);
            main.add(c1);
            card.setImage(photo);
            card.addComponents(main);
            this.add(card);


        }

    }
}