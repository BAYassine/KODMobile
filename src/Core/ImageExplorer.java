package Core;

import Entities.Photo;
import com.codename1.io.Storage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;

import java.io.IOException;

public class ImageExplorer {

    public static URLImage getImage(Photo p){
        EncodedImage EI = null;
        try {
            EI = EncodedImage.create("/placeholder.png");
        } catch (IOException ex) {
        }
        URLImage img = URLImage.createToStorage(EI, p.getName(), Config.imagesPath + p.getName() , URLImage.RESIZE_SCALE);
        return img;
    }

}
