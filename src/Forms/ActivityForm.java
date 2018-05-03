package Forms;


import Controllers.KidsController;
import Core.App;
import Core.ImageExplorer;
import Entities.Child;
import Entities.ChildGame;
import Services.KidsService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

// n'oubliez pas d'ajouter extends Form
public class ActivityForm extends Form{

    public ActivityForm(java.util.List<ChildGame> childGames, Child c) {
        super("Activité récente", BoxLayout.y());
        this.setToolbar(App.sidemenu);
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
            infos.setWidth(this.getInnerWidth() - 100);
            infos.add(new Label(cg.getGame().getName()));
            infos.add(new Label(cg.dateFormatted()));
            infos.add(new Label(cg.durationFormatted()));

            item.add(icon);
            item.add(infos);
            if (!c.getBlockedGames().contains(cg.getGame().getId())){
                Button blk = new Button(String.valueOf(FontImage.MATERIAL_BLOCK));
                blk.addActionListener(e -> {
                    new KidsService().blockGame(c.getId(), cg.getGame().getId());
                    new KidsController().showActivity(c.getId());
                });
                item.add(blk);
            }
            games_cont.add(item);
        }
        Tabs tabs = new Tabs();
        tabs.addTab("Jeux", games_cont);
        tabs.addTab("Musique", music_cont);
        this.add(tabs);
    }
}
