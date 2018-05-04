package Forms;

import Core.App;
import Entities.School;
import Services.MapService;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;


public class MapForm extends Form {

    private Form main;
    private Coord lastLocation;
    public MapForm() {
        super("Maps Demo", new BoxLayout(BoxLayout.Y_AXIS));
        this.setToolbar(App.sidemenu);
        Button b = new Button("afficher map");
        this.addComponent(b);
        b.addActionListener(evt -> showMeOnMap());
    }
    public void showMeOnMap() {
        Form map = new Form("Map");
        map.setToolbar(App.sidemenu);
        map.setLayout(new BorderLayout());
        final MapComponent mc = new MapComponent();
        putMeOnMap(mc,new MapService().findAll());
        //mc.zoomToLayers();
        Label url = new Label("videourlwxfwxfwxfwxf ");

        map.add(BorderLayout.CENTER,mc);
        map.show();
    }
    private void putMeOnMap(MapComponent map,ArrayList<School> schools) {
        for(School school : schools) {

//            lastLocation = new Coord(10.19362, 36.83753);
             lastLocation = new Coord(school.getXschool(),  school.getYschool());
            System.out.println("mes");
            map.setScrollable(true);

            Image i = App.theme.getImage("marker.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(lastLocation, "", i);
            p.setDisplayName(true);
            pl.addPoint(p);
            pl.addActionListener(evt -> {
                PointLayer p1 = (PointLayer) evt.getSource();
                Dialog.show("votre ecole ba7dha esprit :*", "" + "\n" + school.getSchoolname() + "|" + school.getAddress(), "Ok", null);
            });
            map.addLayer(pl, 0, 10);
            map.setZoomLevel(9);
            map.zoomTo(lastLocation, 9);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        }
    }
}
