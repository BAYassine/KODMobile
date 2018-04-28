package Forms;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

public class LoginForm extends Form{


    private TextField password;
    private TextField login;
    private Button connect;
    private Label error;

    public LoginForm(boolean forworded){
        super(new FlowLayout(Component.CENTER, Component.CENTER));
        if(forworded)
            this.add(new Label("Vous devez être connecté"));
        error = new Label("Identifiants invalides");
        error.setVisible(false);
        login = new TextField("", "Nom d'utilisateur");
        password = new TextField("", "Mot de passe", 20, TextArea.PASSWORD);
        connect = new Button("Se connecter");
        Container content = new Container(BoxLayout.y());
        content.addAll(error, login, password, connect);
        this.add(content);

    }

    public TextField getPassword() {
        return password;
    }

    public TextField getLogin() {
        return login;
    }

    public Button getConnect() {
        return connect;
    }

    public void showError(){
        error.setVisible(true);
    }
}
