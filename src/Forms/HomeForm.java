package Forms;

import Components.KButton;
import Controllers.*;
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
        App.sidemenu.addMaterialCommandToRightBar("", FontImage.MATERIAL_NOTIFICATIONS, e ->
            new NotificationController().init()
        );
        App.sidemenu.addCommandToSideMenu("   Acceuil", App.theme.getImage("home.png"), e -> new HomeController().init());
        App.sidemenu.addCommandToSideMenu("   Mes enfants", App.theme.getImage("kids.png"), e -> new KidsController().init());
        App.sidemenu.addCommandToSideMenu("   Language", null, e -> new KidsController().learnLanguage());
        App.sidemenu.addCommandToSideMenu("   Réclamations", null, e -> new ComplaintController().init());
        App.sidemenu.addCommandToSideMenu("   Articles", null, e -> new ArticleController().init());
        App.sidemenu.addCommandToSideMenu("   Babysitters", null, e -> new BabysitterController().init());
        App.sidemenu.addCommandToSideMenu("   Shopping", null, e -> new ProductController().init());
        App.sidemenu.addCommandToSideMenu("teachers", null, e -> new TeacherController().init());
        App.sidemenu.addCommandToSideMenu("videos", null, e -> new VideosController().init());
        App.sidemenu.addCommandToSideMenu("ecole", null, e -> new MapForm().showMeOnMap());

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
