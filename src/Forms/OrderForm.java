package Forms;

import Controllers.ProductController;
import Core.App;
import Entities.Commande;
import Entities.Commentaire;
import Entities.LigneCommande;
import Entities.Product;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import javax.swing.table.TableModel;
import java.util.ArrayList;

public class OrderForm extends  Form {


    public OrderForm(ArrayList<Commande> orders) throws IOException {
        super("Liste des commandes :"+" ",BoxLayout.y());
        System.out.println(orders.size());
        Container c1 = new Container(BoxLayout.y());
        for (Commande c : orders) {
            Container c2 = new Container(BoxLayout.x());


            com.codename1.ui.Label l = new com.codename1.ui.Label(c.getTotal()+"");
            com.codename1.ui.Label l1 = new com.codename1.ui.Label(c.getUser_id()+"");
            com.codename1.ui.Label l2 = new com.codename1.ui.Label(c.getDate()+"");

          this.add(l);
          this.add(l1);
          this.add(l2);


            Button payer= new Button("Payer");
            this.add(payer);

            c1.add(c2);
            payer.addActionListener(evt -> {
                        try {
                            new ProductController().payment();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );



        }
        this.show();

    }
}
