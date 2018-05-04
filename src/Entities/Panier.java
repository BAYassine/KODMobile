package Entities;

import Core.App;

import java.util.ArrayList;
import java.util.List;

public class Panier {


    public static Panier instance;

    public List<LigneCommande> p = new ArrayList();

    public Panier() {
    }



    public void addLine(LigneCommande c) {


        if (search(c) == false) {
            System.out.println("Quantite Premier Ajout==>" + c.getQuantite());
            p.add(c);
        } else {
            for (LigneCommande c1 : p)
            {

                if (c1.getProduct().getName().compareTo(c.getProduct().getName())==0)
                {
                    c1.setQuantite(c1.getQuantite() + 1);
                    System.out.println("Quantite Deuxieme Ajout==>" + c1.getQuantite());
                }
            }
        }

    }

    public void deleteCart() {
        p.removeAll(p);

    }

    public void removeLine(LigneCommande c) {
        p.remove(c);

    }

    public void show() {

        for (LigneCommande c : p) {
            System.out.println(c.toString());
        }
    }

    public boolean search(LigneCommande c) {
        for (LigneCommande c1 : App.panier) {
            if (c1.getProduct().getName().compareTo(c.getProduct().getName()) == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Panier{" + "p=" + p + '}';
    }
}
