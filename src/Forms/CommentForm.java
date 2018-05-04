package Forms;

import Core.App;
import Entities.Commentaire;
import Entities.Thread;
import Services.ProductService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommentForm extends Form {
    public CommentForm(ArrayList<Commentaire> com) {
        super("Commentaires",BoxLayout.y());
        this.setToolbar(App.sidemenu);
        Label th= new Label( "Nombre de commentaire : "+"  "+com.get(0).getIdP().getNumComments()+"");
        this.add(th);
        Container c1 = new Container(BoxLayout.y());
        for (Commentaire c : com) {

            Container c2 = new Container(BoxLayout.x());


            com.codename1.ui.Button ajouter = new Button();
           ajouter.addActionListener(e -> {

           });
           this.add(ajouter);
            Label name = new Label(c.getBody());
            this.add(name);
            c1.add(c2);


        }


    }
}
//    private void AfficherComment(List<Commentaire> listCom , String ancestors){
//        while(listCom.size() != 0)
//        {
//            Commentaire comment = listCom.get(0);
//            if (comment.getAncestors().equals("") || comment.getAncestors().equals(null))
//                ancestors = comment.getId_commentaire()+"";
//            else
//                ancestors = comment.getAncestors() + "/" + comment.getId_commentaire()+"";
//            Container contCom = new Container(BoxLayout.y());
//            Label username = new Label(comment.getId_user()+"");
//            username.setUIID("LabelComment");
//            Label created_at = new Label(comment.getCreated_at()+"");
//            created_at.setUIID("LabelDate");
//            Label body = new Label(comment.getBody());
//            body.setUIID("LabelBody");
//
//            Container contNameDate = new Container(BoxLayout.x());
//            Container contDeleteUpdate = new Container(BoxLayout.x());
//            Container contReply = new Container(BoxLayout.y());
//
//            contNameDate.getStyle().setMargin(4, 0, 0, 0);
//            contNameDate.add(username).add(created_at);
//
//
//
//            contDeleteUpdate.getStyle().setMargin(0, 0, 0, 0);
//            contCom.add(contNameDate).add(contDeleteUpdate);
//            listCom.remove(0);
//            contCom.setUIID("containerCommentaire");
//            contCom.getStyle().setMarginLeft(comment.getAncestors().length() *3);
//
//
//        }







