package Components;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;


public class KButton extends Button{

    public static final int YELLOW = 0xf0c24b;
    public static final int GREEN = 0x449d44;
    public static final int RED = 0xea7066;

    public KButton(String text) {
        super(text);
        setCapsText(true);
        Style s = getAllStyles();
        s.setBgColor(ColorUtil.WHITE);
        s.setFgColor(0x666666);
        s.setPadding(15,15,20,20);
        s.setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        s.setBgTransparency(255);
    }

    public KButton(String text, int color) {
        super(text);
        setCapsText(true);
        Style s = getAllStyles();
        s.setFgColor(ColorUtil.WHITE);
        s.setBgColor(color);
        s.setPadding(15,15,20,20);
        s.setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        s.setBgTransparency(255);
    }

    public KButton(String text, boolean outline ){
        super(text);
        setCapsText(true);
        Style s = getAllStyles();
        s.setBgColor(ColorUtil.WHITE);
        s.setFgColor(0xea7066);
        s.setPadding(15,15,20,20);
        s.setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        s.setBgTransparency(255);
        s.setBorder(RoundRectBorder.create()
                .stroke(2,false)
                .cornerRadius(1).strokeColor(0xea7066).strokeOpacity(255));
    }
}
