package Forms;

import Components.KButton;
import Controllers.*;
import Core.App;
import Core.AuthRequest;
import Services.AuthService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;

public class HomeForm extends Form{

    public HomeForm() {
        super(BoxLayout.y());
        App.sidemenu = this.getToolbar();

        App.sidemenu.addCommandToSideMenu("Acceuil", null, e -> new HomeController().init());
        App.sidemenu.addCommandToSideMenu("Jeux", null, e -> new GamesController().init());
      //  App.sidemenu.addCommandToSideMenu("Mes enfants", null, e -> new KidsController().init());
        App.sidemenu.addCommandToSideMenu("Shop", null, e -> {
            try {
                new ProductController().init();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        Image img = App.theme.getImage("slider-1.jpg");
        img = img.scaledWidth(300);
        KButton login = new KButton("Connecter", KButton.YELLOW);
        KButton logout = new KButton("Déconnecter", KButton.YELLOW);
        login.addActionListener(e -> new AuthController().init(false));
        logout.addActionListener(e -> new AuthService().logout());
        this.add(img);
        if(AuthRequest.getToken().equals("")){
            this.add(login);
        }
        else {
            this.add(logout);
            this.add(new Label(new AuthService().getConnected().getFullname()));
        }
        this.add(new Label("Na3ref t7ebbouhom hethom XD"));
    }

}
