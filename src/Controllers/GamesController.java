package Controllers;

import Entities.Game;
import Forms.GamesForm;
import Services.GamesService;

import java.util.ArrayList;

public class GamesController {

    public void init() {
        ArrayList<Game> games = new GamesService().findAll();
        GamesForm gf = new GamesForm(games);
        gf.show();
    }
}
