package Forms;



import Core.App;
import Services.ProductService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;

import static Forms.PanierForm.PrixTotalStatic;


public class PaymentForm  extends Form {

    public PaymentForm() throws IOException {


        super("", BoxLayout.y());
       this.setToolbar(App.sidemenu);
       TextField card = new TextField();
        Container c1 = new Container();
       TextField total= new TextField();
        TextField cvv= new TextField();
        TextField exp_m= new TextField();
        TextField exp_y= new TextField();
       total.setText(String.valueOf(PrixTotalStatic));
        Button Submit = new Button("Checkout");
        Label prix = new Label("Price");
        Label Card = new Label("Card Number");
        Label mot_de_passe= new Label("Mot de passe");
        Label exp_month= new Label("exp_month");
        Label exp_year= new Label("exp_year");
      Submit.addActionListener(evt -> {

          ProductService ps = new ProductService();
          ps.payer();
      });
        c1.add(prix);
        c1.add(total);
        c1.add(Card);
        c1.add(card);

        c1.add(mot_de_passe);
        c1.add(cvv);
        c1.add(exp_month);
        c1.add(exp_m);
        c1.add(exp_year);
        c1.add( exp_y);
        c1.add(Submit);
        // f.add(prix);
        this.add(c1);

    }
}