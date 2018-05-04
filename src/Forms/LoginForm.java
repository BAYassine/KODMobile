package Forms;

import Components.Box;
import Components.KButton;
import Components.KTextField;
import Core.App;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class LoginForm extends Form{


    private KTextField password;
    private KTextField login;
    private Button connect;
    private Label error;

    public LoginForm(boolean forworded){
//        super(new FlowLayout(Component.CENTER, Component.CENTER));
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);
        if(forworded)
            this.add(new Label("Vous devez être connecté"));
        error = new Label("Identifiants invalides");
        error.setVisible(false);
        login = new KTextField("", "Nom d'utilisateur");
        password = new KTextField("", "Mot de passe", true);


        login.setText("admin");
        password.setText("admin");

        connect = new KButton("Se connecter", KButton.RED);
        Box b = new Box("Connecter");
        b.addComponents(error);
        b.addComponents(login);
        b.addComponents(password);
        b.addComponents(connect);
        this.add(b);
    }

    public KTextField getPassword() {
        return password;
    }

    public KTextField getLogin() {
        return login;
    }

    public Button getConnect() {
        return connect;
    }

    public void showError(){
        error.setVisible(true);
    }
}
