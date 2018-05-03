package Forms;

import Components.KButton;
import Controllers.AuthController;
import Controllers.GamesController;
import Controllers.HomeController;
import Controllers.KidsController;
import Core.App;
import Core.AuthRequest;
import Services.AuthService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class HomeForm extends Form{

    public HomeForm() {
        super("                              " ,BoxLayout.y());

        App.sidemenu = this.getToolbar();
        Toolbar.setGlobalToolbar(true);
        App.sidemenu.addCommandToSideMenu("   Acceuil", App.theme.getImage("home.png"), e -> new HomeController().init());
        App.sidemenu.addCommandToSideMenu("   Mes enfants", App.theme.getImage("kids.png"), e -> new KidsController().init());
        App.sidemenu.addCommandToSideMenu("   Language", null, e -> new KidsController().learnLanguage());
        App.sidemenu.addCommandToSideMenu("   Games", null, e -> new GamesController().init());

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
