package Forms;

import Controllers.TeacherController;
import Core.App;
import Core.ImageExplorer;
import Entities.Teacher;
import com.codename1.components.ShareButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class TeacherForm extends Form {

    public TeacherForm (ArrayList<Teacher> Teachers ) {
        super("enseignants",BoxLayout.y());
        this.setToolbar(App.sidemenu);

        Image photo = null ;


        this.setToolbar(App.sidemenu);


        for(Teacher teacher : Teachers){


            Container C = new Container (BoxLayout.x());
            System.out.println(teacher.getPhoto().getId());
            System.out.println(teacher.getPhoto().getUrl());
            photo = ImageExplorer.getImage(teacher.getPhoto());

            Container infos = new Container (BoxLayout.y());
            Label name  = new Label( "nom : "   +teacher.getName());
            Label ex  = new Label ("telephone : " + teacher.getPhone());
            ShareButton share = new ShareButton();
            FontImage.setMaterialIcon(share, FontImage.MATERIAL_SHARE, 8);
            share.setText("");
            infos.addAll(name,ex);
            Button b = new Button("details");
            infos.add(b);
            C.add(photo);
            C.add(infos);
            b.addActionListener(e -> new TeacherController().showid(teacher.getId()));
            this.add(C);

        }

    }



    public TeacherForm (Teacher teacher) {
        super("enseignants",BoxLayout.y());
        this.setToolbar(App.sidemenu);

        Image photo = null ;


        this.setToolbar(App.sidemenu);

        Container C = new Container (BoxLayout.x());
//        System.out.println(teacher.getPhoto().getId());
//        System.out.println(teacher.getPhoto().getUrl());
//        photo = ImageExplorer.getImage(teacher.getPhoto());

        Container infos = new Container (BoxLayout.y());
        Label name  = new Label( "nom : "   +teacher.getName());
        Label ex  = new Label ("telephone : " + teacher.getPhone());
        Label ex1  = new Label ("Experience : " + teacher.getExperience());
        Label ex2  = new Label ("Account : " + teacher.getLastname());

        ShareButton share = new ShareButton();
        FontImage.setMaterialIcon(share, FontImage.MATERIAL_SHARE, 8);
        share.setText("");
        infos.addAll(name,ex,ex1,ex2);
        C.add(photo);
        C.add(infos);
        this.add(C);

    }
}
