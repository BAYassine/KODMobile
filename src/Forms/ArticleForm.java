package Forms;
import Components.Card;
import Controllers.ArticleController;
import Controllers.KidsController;
import Core.App;
import Core.ImageExplorer;
import Entities.Article;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.util.ArrayList;

public class ArticleForm extends Form {
    public ArticleForm(ArrayList<Article> articles)

    {
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);
        for (Article article : articles)
        {
            Card card= new Card();
            Container c= new Container(BoxLayout.y());
            SpanLabel corps= new SpanLabel();
          corps.setText(article.getDescription().substring(0,article.getDescription().length()/10)+"...");
           Button plus = new Button("Plus");
           card.setTitle(article.getTitle());

            Image photo = null;
            if (article.getPhoto() != null)
                photo = ImageExplorer.getImage(article.getPhoto());
            card.setImage(photo);
                plus.addPointerPressedListener(e -> new ArticleController().moreArticle(article.getId()));
//
//
//
//
//
//
//
//
           c.add(corps);
           c.add(plus);
                 card.addComponents(c);
                this.add(card);
            }


        }

    }



