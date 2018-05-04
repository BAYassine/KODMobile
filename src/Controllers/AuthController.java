package Controllers;

import Forms.LoginForm;
import Services.AuthService;

public class AuthController {

    public void init(boolean forworded){
        LoginForm lf = new LoginForm(forworded);
        lf.show();

        lf.getConnect().addActionListener(e -> {
            String login = lf.getLogin().getText();
            String pass = lf.getPassword().getText();
            if (new AuthService().login(login, pass))
                new HomeController().init();
            else {
                new HomeController().init();
            }
        });
    }

}
