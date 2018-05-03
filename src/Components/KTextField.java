package Components;

import Core.App;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;

public class KTextField extends TextField{

    public KTextField(String text, String hint, boolean isPassword){
        super(text, hint);
        Style s = this.getAllStyles();
        s.setPadding(15,15,15,15);
        s.setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        s.setBorder(RoundRectBorder.create()
            .cornerRadius(1).stroke(2, false).strokeColor(0xcccccc)
        );
        if(isPassword)
            s.setFont(App.theme.getFont("password"));
        else
            s.setFont(App.theme.getFont("buttons"));
    }

    public KTextField(String text, String hint) {

        super(text, hint);
        Style s = this.getAllStyles();
        s.setPadding(15,15,15,15);
        s.setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        s.setBorder(RoundRectBorder.create()
                .cornerRadius(1).stroke(2, false).strokeColor(0xcccccc)
        );
        s.setFont(App.theme.getFont("buttons"));
    }
}
