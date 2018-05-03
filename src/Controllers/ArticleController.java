package Controllers;

import Core.App;
import Entities.Article;
import Entities.ChildGame;
import Forms.ActivityForm;
import Forms.ArticleForm;
import Forms.MoreArticleForm;
import Services.ArticleService;
import Services.KidsService;

import java.util.ArrayList;

public class ArticleController {


    public void init() {
        if (App.isConnected()) {
            ArrayList<Article> articles = new ArticleService().findAll();
            ArticleForm af = new ArticleForm(articles);
            af.show();
        } else {
            new AuthController().init(true);
        }
    }

    public void moreArticle(int id) {
        if (App.isConnected()) {
            Article article = new ArticleService().findArticle(id);
            MoreArticleForm af = new MoreArticleForm(article);

            af.show();
        } else {
            new AuthController().init(true);
        }
    }


}
