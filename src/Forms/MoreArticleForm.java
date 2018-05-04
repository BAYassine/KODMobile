package Forms;

import Components.Card;
import Controllers.ArticleController;
import Core.App;
import Core.ImageExplorer;
import Entities.Article;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.util.ArrayList;

public class MoreArticleForm extends Form {

    public MoreArticleForm(Article article) {
        super(new FlowLayout(Component.CENTER,Component.CENTER));
        this.setToolbar(App.sidemenu);
        Card card= new Card();
            Container c= new Container(BoxLayout.y());


            Image photo = null;
            if (article.getPhoto() != null)
                photo = ImageExplorer.getImage(article.getPhoto());
             card.setImage(photo);
             card.setTitle(article.getTitle());

            SpanLabel content = new SpanLabel("Contenu:      "+ article.getDescription());
            content.getTextAllStyles().setFont(App.theme.getFont("main font"));
            SpanLabel auteur = new SpanLabel("Auteur:       "+ article.getAuteur());
            c.add(auteur);
            c.add(content);
            card.addComponents(c);
            this.add(card);

    }



}
