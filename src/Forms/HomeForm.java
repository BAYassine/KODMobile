package Forms;

import Controllers.AuthController;
import Controllers.GamesController;
import Controllers.HomeController;
import Controllers.KidsController;
import Core.App;
import Core.AuthRequest;
import Services.AuthService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class HomeForm  extends Form{

    public HomeForm() {
        super(BoxLayout.y());
        App.sidemenu = this.getToolbar();

        App.sidemenu.addCommandToSideMenu("Acceuil", null, e -> new HomeController().init());
        App.sidemenu.addCommandToSideMenu("Jeux", null, e -> new GamesController().init());
        App.sidemenu.addCommandToSideMenu("Mes enfants", null, e -> new KidsController().init());

        Image img = App.theme.getImage("slider-1.jpg");
        img = img.scaledWidth(300);
        Button login = new Button("Connecter");
        Button logout = new Button("Déconnecter");
        login.addActionListener(e -> new AuthController().init(false));
        logout.addActionListener(e -> new AuthService().logout());
        this.add(img);
        if(AuthRequest.getToken().equals(""))
            this.add(login);
        else this.add(logout);
        this.add(new Label("Na3ref t7ebbouhom hethom XD"));
    }

}
