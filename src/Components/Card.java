package Components;

import Core.App;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.util.regex.RE;

import java.util.Random;

public class Card extends Container{

    public static final int YELLOW = 0xf0c24b;
    public static final int GREEN = 0xb5d56a;
    public static final int RED = 0xea7066;
    public static final int BLUE = 0x84bed6;
    public static final int PURPLE = 0xa597e7;
    public static final int PINK = 0xea77ad;

    private static final int[] colors = {YELLOW, GREEN, RED, BLUE, PURPLE, PINK };

    private ImageViewer iv;
    private Label title;
    private Container body;

    public Card(int color){
        super(BoxLayout.y());
        getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(color)
                .cornerRadius(2).stroke(3,false));
        getAllStyles().setMargin(10 ,10,10,10 );
        iv = new ImageViewer();
        iv.setPreferredSize(new Dimension(300,100));
        this.add(iv);
        title = new Label("");
        title.getAllStyles().setFont(App.theme.getFont("Titles"));
        title.getAllStyles().setFgColor(ColorUtil.WHITE);

        body = new Container(BoxLayout.y());
        body.getAllStyles().setBgColor(color);
        body.getAllStyles().setBgTransparency(255);
        body.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        body.getAllStyles().setPadding(20,20,20,20);
        body.add(title);
        body.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(color)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
        add(body);
    }

    public Card(boolean randomColor){
        super(BoxLayout.y());
        Random random = new Random();
        int color = colors[random.nextInt(6)];
        getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(color)
                .cornerRadius(2).stroke(3,false));
        getAllStyles().setMargin(10 ,10,10,10 );
        iv = new ImageViewer();
        iv.setPreferredSize(new Dimension(300,100));
        this.add(iv);
        title = new Label("");
        title.getAllStyles().setFont(App.theme.getFont("Titles"));
        title.getAllStyles().setFgColor(ColorUtil.WHITE);

        body = new Container(BoxLayout.y());
        body.getAllStyles().setBgColor(color);
        body.getAllStyles().setBgTransparency(255);
        body.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        body.getAllStyles().setPadding(20,20,20,20);
        body.add(title);
        body.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(color)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
        add(body);
    }

    public Card(){
        super(BoxLayout.y());
        getAllStyles().setMargin(10 ,10,10,10 );
        getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(ColorUtil.WHITE)
                .cornerRadius(2)
                .bottomOnlyMode(true).stroke(3,false));
        iv = new ImageViewer();
        iv.setPreferredSize(new Dimension(300,100));
        iv.getAllStyles().setBorder(Border.createUnderlineBorder(5,0xf0c24b));
        this.add(iv);
        title = new Label("");
        title.getAllStyles().setFont(App.theme.getFont("Titles"));
        title.getAllStyles().setFgColor(0xf0c24b);

        body = new Container(BoxLayout.y());
        body.getAllStyles().setBgColor(ColorUtil.rgb(248,248,248));
        body.getAllStyles().setBgTransparency(255);
        body.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        body.getAllStyles().setPadding(20,20,20,20);
        body.add(title);
        body.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(ColorUtil.rgb(248,248,248))
                .stroke(5, false ).strokeOpacity(255)
                .cornerRadius(2)
                .topOnlyMode(true));
        add(body);
    }

    public void setImage(Image image) {
        iv.setImage(image);
    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void addComponents(Component c){
        body.add(c);
    }
}
