package com.sepsoh.menschbos.playwithbots;


import com.sepsoh.menschbos.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FourPlayerBoardController implements Board {


    @FXML
    ImageView box0,box1,box2,box3,box4,box5;
    @FXML
    Label helperLabel;


    public void initialize(){
        Game game = new Game();
        game.init(this);
        game.start();


    }


    @Override
    public ArrayList<ImageView> getBoxes() {
        ArrayList<ImageView> boxes = new ArrayList<>();
        boxes.add(box0);
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
        boxes.add(box5);
        return boxes;
    }

    @Override
    public Label getHelperLabel() {
        return helperLabel;
    }
}
