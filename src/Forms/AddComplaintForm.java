package Forms;
import Controllers.ComplaintController;
import Core.App;
import Entities.Article;
import Entities.Category;
import Entities.Complaint;
import Services.AuthService;
import Services.CategoryService;
import Services.ComplaintService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class AddComplaintForm extends Form {
    public AddComplaintForm()
    {
        super(new FlowLayout(Component.CENTER,Component.CENTER));
        this.setToolbar(App.sidemenu);
        Container c= new Container();
        Label title= new Label("Ecrivez votre reclamation");
        TextField subject= new TextField("","Sujet");
        //TextField categorie= new TextField("","Catégorie");
        TextField description= new TextField("","Ecrivez votre message");
        Date date = new Date(new java.util.Date().getTime());
        ComboBox <Category> categories= new ComboBox<>();
        for (int i=0;i< new CategoryService().findAll().size();i++)
        {
            categories.addItem(new CategoryService().findAll().get(i));
        }



        Button ajouter= new Button("Envoyer");
        c.add(title);
        c.add(subject);
        c.add(categories);
        c.add(description);
        c.add(ajouter);

        ajouter.addPointerPressedListener(
                e -> {
                   if (subject.getText().isEmpty())
                   {
                       Dialog.show("Sujet vide", "Veuillez saisir un sujet", "OK", null);
                   }
                   if (description.getText().isEmpty())
                   {
                       Dialog.show("Message vide", "Veuillez saisir un message", "OK", null);
                   }

                  else {
                       Complaint comp= new Complaint(description.getText().toString(),subject.getText().toString(),date,"non_traitee",new AuthService().getConnected(),categories.getSelectedItem()) ;
                       new ComplaintService().addComplaint(comp);
                       System.out.println(comp);
                       System.out.println(new CategoryService().findAll());
                       new ComplaintController().init();
                   }

                }
        );

        this.add(c);
    }




}
