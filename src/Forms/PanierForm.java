package Forms;

import Controllers.ProductController;
import Core.App;
import Core.ImageExplorer;
import Entities.Commande;
import Entities.LigneCommande;
import Services.ProductService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import java.io.IOException;

public class PanierForm extends Form {

    public static int PrixTotalStatic = 0;
    Form f;
    private int cmpt;
    Label PrixTotal = new Label();


    public PanierForm()  throws IOException {
        f = new Form("Your Cart", BoxLayout.y());
        Container c10 = new Container(BoxLayout.x());
        f.setToolbar(App.sidemenu);
        Button checkout = new Button("Checkout");


        //**********************************instanciation du panier********************************************************


        int prix = 0;
        //********************************Parcourir le panier**************************************************************
        ComponentGroup cg = new ComponentGroup();
        ComponentGroup c6 = new ComponentGroup();
        for (LigneCommande c : App.panier){
            Container c4 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c1 = new Container(BoxLayout.y());
            Container c5 = new Container(BoxLayout.y());
            Container c8 = new Container(BoxLayout.x());
            Container c9 = new Container(BoxLayout.x());
            Container c11 = new Container(BoxLayout.x());
            Label Quantite = new Label("Quantité : ");
            Label prixLabel = new Label("Prix : ");
            prix += c.getQuantite() * c.getProduct().getPrice();
            c8.add(Quantite);
            c9.add(prixLabel);

            PrixTotal.setText(Integer.toString(prix));
            PrixTotalStatic = Integer.parseInt(PrixTotal.getText());
            //***************************les elements du containers************************************************************
            Image photo = null;
            if (c.getProduct().getImg() != null)
                photo = ImageExplorer.getImage(c.getProduct().getImg()).scaled(80, 80);


            Label PP = new Label(Float.toString(c.getProduct().getPrice() * c.getQuantite())+"DT");
            Label PQ = new Label(Integer.toString(c.getQuantite()));
            //c8.add(PQ);
            Button bt = new Button("X");

            bt.addActionListener(e -> {
               // App.panier.removeLine(c);
                c4.remove();
                PrixTotal.remove();
                f.revalidate();
                c10.add(PrixTotal);
                System.out.println("LE NOUVEAU PRIX APRES + ===>" + PrixTotalStatic);
                PrixTotal.setText(Integer.toString(PrixTotalStatic -= c.getProduct().getPrice() * c.getQuantite())+"DT");

            });
            //********************les boutons de modif quantite******************************************
            Button plus = new Button("+");
            //plus.setWidth(10);
            Button minus = new Button("-");
            final int price2 = prix;
            plus.addActionListener(e -> {
                cmpt = c.getQuantite();
                System.out.println(cmpt);
                cmpt++;
                    c.setQuantite(cmpt);
                    System.out.println("quantite : " + c.getQuantite());
                    PQ.remove();
                    PP.remove();
                    PrixTotal.remove();
                    f.revalidate();
                    PQ.setText(String.valueOf(cmpt));

                    //c2.add(PQ);

                    c8.add(PQ);
                    PP.setText(Float.toString(c.getProduct().getPrice() * cmpt)+"DT");
                    c10.add(PrixTotal);

                    System.out.println("Nouveau prix + : " + PrixTotalStatic);
                    PrixTotal.setText(Integer.toString(PrixTotalStatic += c.getProduct().getPrice())+"DT");
                    //c2.add(PP);
                    c9.add(PP);


            });

            minus.addActionListener(e -> {

                //ProduitServices ps = new ProduitServices();
                //Produit pfound = ps.searchByName(c.getProduct().getId());
                cmpt = c.getQuantite();
                cmpt--;
                if (cmpt <= 0) {
                  App.panier.remove(c);
                    c4.remove();
                    PQ.remove();
                    PP.remove();
                    plus.remove();
                    minus.remove();
                    c8.remove();
                    c9.remove();
                    cg.remove();
                    f.revalidate();
                } else if (cmpt >0) {
                    c.setQuantite(cmpt);
                    PQ.remove();
                    PP.remove();
                    PrixTotal.remove();
                    f.revalidate();
                    PQ.setText(String.valueOf(cmpt));
                    PP.setText(Float.toString(c.getProduct().getPrice() * cmpt)+"DT");
                    c10.add(PrixTotal);

                    System.out.println("Nouveau prix - ===>" + PrixTotalStatic);
                    PrixTotal.setText(Integer.toString(PrixTotalStatic -= c.getProduct().getPrice())+"DT");

                    c8.add(PQ);
                    //c2.add(PP);
                    c9.add(PP);
                }
                f.revalidate();
            });

            bt.getStyle().setPadding(0, 0, 0, 0);
            plus.getStyle().setPadding(0, 0, 0, 0);
            minus.getStyle().setPadding(0, 0, 0, 0);

            //*****************************mettre le bouton X au milieu****************************************************
            Label lb1 = new Label(".");
            Label lb2 = new Label(".");
            lb1.setVisible(false);
            lb2.setVisible(false);
            Label lb3 = new Label(".");
            Label lb4 = new Label(".");
            lb3.setVisible(false);
            lb4.setVisible(false);
            c1.add(lb1);
            c1.add(bt);
            c1.add(lb2);
            c8.add(PQ);
            c11.add(c8);
            c11.add(plus);
            c11.add(minus);

            c4.add(c1);
            c4.add(photo);
            c2.add(c5);

            c9.add(PP);

            //c2.add(b2);
            //c3.add(new SpanLabel(c.getProduct().getName()));
            c3.add(c2);

            c4.add(c3);
            //c4.add(PP);

            cg.add(c4);
            //cg.add(c8);
            cg.add(c9);
            cg.add(c11);

        }

        Label prixTot=new Label("Prix total : ");
        c10.add(prixTot);
        c10.add(PrixTotal);
        c6.add(c10);
        f.add(cg);
        f.add(c6);

        f.add(checkout);
        //f.add(b);

        checkout.addActionListener(e -> {
            Dialog.show("Confirm", "Are you sure ?", "Ok", "Cancel");
            System.out.println("Mon panier ==>" + App.panier);
            new ProductService().Panier();
            Commande c = new Commande();

            new ProductController().showOrders();
        });
f.show();
    }



    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}