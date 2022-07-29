package com.sepsoh.menschbos.playwithbots;


import com.sepsoh.menschbos.Main;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FourPlayerBoardController {


    @FXML
    ImageView box0,box1,box2,box3,box4,box5;


    public void initialize(){
        Game game = new Game();

        game.addBox(box0);
        game.addBox(box1);
        game.addBox(box2);
        game.addBox(box3);
        game.addBox(box4);
        game.addBox(box5);

        game.start();

    }


}
