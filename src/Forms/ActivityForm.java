package Forms;


import Components.HCard;
import Components.KButton;
import Components.KLabel;
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
            HCard item = new HCard();
            Image icon;
            if(cg.getGame().getIcon() != null)
                icon = ImageExplorer.getImage(cg.getGame().getIcon());
            else icon = App.theme.getImage("default_game.png");
            icon = icon.scaled(50, 50);
            item.setImage(icon);
            Container infos = new Container(BoxLayout.y());
            infos.setWidth(this.getInnerWidth() - 100);
            KLabel title = new KLabel(cg.getGame().getName());
            title.getAllStyles().setFont(App.theme.getFont("Titles"));
            title.getAllStyles().setFgColor(0xea7066);
            infos.add(title);
            infos.add(new KLabel(cg.dateFormatted()));
            infos.add(new KLabel(cg.durationFormatted()));

            item.setCenter(infos);
            KButton blk = new KButton("",true);
            if (!c.getBlockedGames().contains(cg.getGame().getId())){
                FontImage.setMaterialIcon(blk, FontImage.MATERIAL_BLOCK);
                blk.addActionListener(e -> {
                    new KidsService().updateGame(c.getId(), cg.getGame().getId());
                    new KidsController().showActivity(c.getId());
                });
            }else {
                FontImage.setMaterialIcon(blk, FontImage.MATERIAL_LOCK_OPEN);
                blk.addActionListener(e -> {
                    new KidsService().updateGame(c.getId(), cg.getGame().getId());
                    new KidsController().showActivity(c.getId());
                });
            }
            item.setRight(blk);
            games_cont.add(item);
        }
        Tabs tabs = new Tabs();
        tabs.addTab("Jeux", games_cont);
        tabs.addTab("Musique", music_cont);
        this.add(tabs);
    }
}
