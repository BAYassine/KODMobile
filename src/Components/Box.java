package Components;

import Core.App;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;


public class Box extends Container {

    private Container content;

    public Box(String header){
        super(BoxLayout.y());
        Label title = new Label(header);
        title.getAllStyles().setMargin(0,0,0,0);
        title.getAllStyles().setPadding(1,1,2,2);
        title.getAllStyles().setBgColor(ColorUtil.rgb(240,194,75));
        title.getAllStyles().setBorder(RoundRectBorder.create().cornerRadius(2).bottomOnlyMode(true));
        title.getAllStyles().setBgTransparency(255);
        title.getAllStyles().setFont(App.theme.getFont("Titles"));
        title.getAllStyles().setFgColor(ColorUtil.WHITE);
        this.getAllStyles().setBorder(RoundBorder.createRoundBorder(20,20, 0xcccccc));
        this.getAllStyles().setMargin(10,10,10,10);

        content = new Container(BoxLayout.y());
        Style contentStyle = content.getAllStyles();
        contentStyle.setPadding(10,10,10,10);
        contentStyle.setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        this.addAll(title);
        this.add(content);
    }

    public void addComponents(Component c){
        content.add(c);
    }

}
