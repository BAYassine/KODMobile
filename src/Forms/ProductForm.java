package Forms;

import Controllers.ProductController;
import Core.App;
import Core.ImageExplorer;
import Entities.*;
import Entities.Thread;
import Services.ProductService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.sun.media.jfxmedia.control.VideoFormat;

import java.awt.*;
import java.awt.Dialog;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class ProductForm extends Form {
    private EncodedImage e;
    private ImageViewer imgv;
    private Image img;
    Form f;

    public ProductForm(ArrayList<Product> products) {

        super();
        Toolbar.setGlobalToolbar(true);
        this.setToolbar(App.sidemenu);
        for (Product product : products) {
            System.out.println(product.getName());
            Label name = new Label(product.getName());
            Label price = new Label(product.getPrice() + "" + "DT");
            Form form3 = new Form(BoxLayout.y());


            Image photo = null;
            if (product.getImg() != null)
                photo = ImageExplorer.getImage(product.getImg());

            name.addPointerPressedListener(new com.codename1.ui.events.ActionListener() {
                @Override
                public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {

                    Form f3 = new Form();
                    Toolbar tb = f3.getToolbar();

                    tb.addCommandToOverflowMenu("add", null, e -> {
                        ProductService ps = new ProductService();
                        ps.addFavoris(product);
                     //   ToastBar.showMessage("Merci d'ajouter un produit a la liste des favoris", FontImage.MATERIAL_ANNOUNCEMENT, 20000);
                    });
                    tb.addCommandToOverflowMenu("Select", null, e -> {

                        Form favoris = new Form(BoxLayout.y());
                        favoris.setToolbar(App.sidemenu);
                        ArrayList<Product> favoriss = new ProductService().selectFavoris();
                        for (Product product : favoriss) {
                            System.out.println("hello");
                            Container c2 = new Container(BoxLayout.y());
                            Label name = new Label(product.getName());
                            Label descr = new Label(product.getDescription());
                            c2.add(name);
                            c2.add(descr);
                            favoris.add(c2);
                        }
                        favoris.show();
                    });

                    Label description = new Label(product.getDescription());
                    Image photo = null;
                    if (product.getImg() != null)
                        photo = ImageExplorer.getImage(product.getImg());
                    Label price = new Label("Prix " + "" + product.getPrice() + "" + "DT");
                    f3.add(photo);

                    f3.add(description);
                    f3.add(price);
                    Button com = new Button("Commentaires");
                    f3.add(com);
                    com.addActionListener(evt1 -> {

                        Commentaire c = new Commentaire();
                        Thread th = new Thread();
                        th.setId(product.getId() + "");
                        c.setIdP(th);
                        ProductController pc = null;
                        try {
                            pc = new ProductController();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        pc.showComment(product.getId());

                    });


                    Button ToCart = new Button("AddToCart");
                    Container cBtoCart = new Container(new FlowLayout(Component.RIGHT, Component.TOP));
                    cBtoCart.add(ToCart);
                    f3.add(cBtoCart);


                    ToCart.addActionListener(e -> {

                        LigneCommande c;
                        c = new LigneCommande(product, 1);
                        c.setUserID(2);
                        c.setProviderId(product.getProviderId());
                        Panier p1 = new Panier();

                        if (p1.search(c) == false) {
                            System.out.println("Quantite Premier Ajout==>" + c.getQuantite());
                            App.panier.add(c);
                        } else {
                            for (LigneCommande c1 : App.panier) {

                                if (c1.getProduct().getName().compareTo(c.getProduct().getName()) == 0) {
                                    c1.setQuantite(c1.getQuantite() + 1);
                                    System.out.println("Quantite Deuxieme Ajout==>" + c1.getQuantite());
                                }
                            }
                        }

                        try {
                            PanierForm p = new PanierForm();
                            p.getF();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        //new LigneCommandesController().afficherPanier();

                    });


                    f3.show();


                }

            });


            Container c = new Container(new FlowLayout(Component.LEFT, Component.CENTER));
            c.add(photo);
            c.add(price);
            c.add(name);
            c.add(form3);
            this.add(c);

        }

    }


}