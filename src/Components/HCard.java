package Components;


import Core.App;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;

public class HCard extends Container {

    private Label title;
    private ImageViewer iv;

    public HCard(){
        super(new BorderLayout());
        Style s = getAllStyles();
        s.setMargin(10 ,10,10,10 );
        s.setBorder(
                RoundRectBorder.create().cornerRadius(2)
        );
        s.setBgColor(ColorUtil.rgb(248,248,248));
        s.setBgTransparency(255);
        iv = new ImageViewer();
        iv.setPreferredSize(new Dimension(80,100));
        this.add(BorderLayout.WEST,iv);
        Container body = new Container(BoxLayout.y());
        title = new Label();
        title.getAllStyles().setFont(App.theme.getFont("Titles"));
        title.getAllStyles().setFgColor(0xf0c24b);
        body.add(title);
        body.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        body.getAllStyles().setPadding(20,20,20,20);
        this.add(BorderLayout.CENTER, body);
    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void setImage(Image image) {
        iv.setImage(image);
    }

    public void setCenter(Component c){
        this.add(BorderLayout.CENTER, c);
    }

    public void setRight(Component c){
        c.getAllStyles().setMargin(30,30,10,10);
        c.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        c.getAllStyles().setPadding(8,8,8,8);
        c.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        this.add(BorderLayout.EAST, c);
    }


}
