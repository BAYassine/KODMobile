package Forms;


import Core.App;
import Core.ImageExplorer;
import Entities.ChildGame;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

// n'oubliez pas d'ajouter extends Form
public class ActivityForm extends Form{

    public ActivityForm(ArrayList<ChildGame> childGames) {
        super(BoxLayout.y());
        Container games_cont = new Container(BoxLayout.y());
        Container music_cont = new Container(BoxLayout.y());
        for (ChildGame cg : childGames){
            Container item = new Container(BoxLayout.x());
            Image icon = null;
            if(cg.getGame().getIcon() != null)
                icon = ImageExplorer.getImage(cg.getGame().getIcon());
            else icon = App.theme.getImage("default_game.png");
            icon = icon.scaled(50, 50);
            Container infos = new Container(BoxLayout.y());
            infos.add(new Label(cg.getGame().getName()));
            infos.add(new Label(cg.dateFormatted()));
            infos.add(new Label(cg.durationFormatted()));

            item.add(icon);
            item.add(infos);
            games_cont.add(item);
        }
        Tabs tabs = new Tabs();
        tabs.addTab("Jeux", games_cont);
        tabs.addTab("Musique", music_cont);
        this.add(tabs);
    }
}
