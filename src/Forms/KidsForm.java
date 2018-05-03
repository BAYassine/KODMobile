package Forms;

import Controllers.KidsController;
import Core.App;
import Core.ImageExplorer;
import Entities.Child;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.painter.BackgroundPainter;
import javafx.scene.paint.Material;

import java.util.ArrayList;

public class KidsForm extends Form {

    public KidsForm(ArrayList<Child> kids){
        //layout vertical de la page
        super("Mes enfants", BoxLayout.y());
        this.setToolbar(App.sidemenu);
        Button add = new Button("");
        add.addActionListener(e -> {
            NewKidForm nkf = new NewKidForm();
            nkf.show();
        });
        FontImage.setMaterialIcon(add, FontImage.MATERIAL_ADD);
        BackgroundPainter bg = new BackgroundPainter(add);
        this.add(add);
        for (Child kid : kids) {
            // un Container horizontal pour chaque enfant
            Container c = new Container(BoxLayout.x());

            //Cette image existe par default dans le fichier theme.res
            //je vais la changer plutard pour contenir l'image de l'enfant

            Image photo = null;
            if (kid.getPhoto() != null)
                photo = ImageExplorer.getImage(kid.getPhoto());
            else if (!kid.getGender())
                photo = App.theme.getImage("girl.png");
            else if (kid.getGender())
                photo = App.theme.getImage("boy.png");

            //Je vais changer un peut pour afficher l'age au dessous du nom
            Container infos = new Container(BoxLayout.y());
            Label name = new Label("Nom : " + kid.getName());
            Label age = new Label("Age : " + kid.getAge());
            infos.addAll(name, age);

            //ajouter les composants dans le container
            c.add(photo);
            c.add(infos);
            name.addPointerPressedListener(e -> new KidsController().showActivity(kid.getId()));
            c.setLeadComponent(name);

            //et en fin ajouter le container a la page
            this.add(c);
        }

    }

}
