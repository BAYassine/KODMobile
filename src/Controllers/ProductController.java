package Controllers;

import Core.App;
import Entities.ChildGame;
import Entities.Commande;
import Entities.Commentaire;
import Entities.Product;
import Entities.Thread;
import Forms.*;
import Services.KidsService;
import Services.ProductService;

import java.io.IOException;
import java.util.ArrayList;

public class ProductController {

    public void init() {
        if (App.isConnected()) {
            ArrayList<Product> products = new ProductService().findAll();
            ProductForm pf = new ProductForm(products);
            pf.show();
        } else {
            new AuthController().init(true);
        }
    }

    public void showOrders() {
        ArrayList<Commande> orders = new ProductService().findOrders();
        OrderForm of = null;
        try {
            of = new OrderForm(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
        of.show();
    }

    public void showComment(int idP) {
        ArrayList<Commentaire> comm = new ProductService().findComment(idP);

        CommentForm fc = new CommentForm(comm);
        fc.show();

    }

    public void payment() throws IOException {
        PaymentForm f = new PaymentForm();
        f.show();

    }





}
