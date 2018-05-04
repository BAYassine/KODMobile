package Controllers;


import Entities.Video;

import Forms.PlayerForm;
import Forms.VideoForm;
import Forms.MapForm;

import Services.VideoService;

import java.util.ArrayList;

public class VideosController {

    public void init() {
        ArrayList<Video>  videos = new VideoService().findAll();
        VideoForm gf = new VideoForm(videos);
        gf.show();
    }

    public void showVideo(String url){
        PlayerForm vf = new PlayerForm(url);
        vf.show();
    }

    public void showMeOnMap(){
        MapForm vf = new MapForm();
        vf.show();
    }

}
