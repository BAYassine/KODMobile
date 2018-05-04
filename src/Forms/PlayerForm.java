package Forms;

import Controllers.VideosController;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MediaPlayer;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

import java.io.IOException;

import static com.codename1.ui.CN.callSerially;

public class PlayerForm extends Form {

    private MediaPlayer mp;

    public PlayerForm(String url) {
        super(new BorderLayout());
        this.getToolbar().setBackCommand("", e -> {
            if (mp != null) {
                mp.getMedia().cleanup();
            }
            new VideosController().init();
        });
        this.add(TOP, new InfiniteProgress());
        try {
            System.out.println(url);
            Media video = MediaManager.createMedia(url, true, null);
            video.prepare();
            callSerially(() -> {
                if (mp != null) {
                    mp.getMedia().cleanup();
                }
                mp = new MediaPlayer(video);
                mp.setAutoplay(true);
                video.setNativePlayerMode(true);
                this.add(CENTER,mp);
                this.revalidate();
            });
            this.removeAll();
        } catch (IOException e) {

            e.printStackTrace();
        }



    }

}
