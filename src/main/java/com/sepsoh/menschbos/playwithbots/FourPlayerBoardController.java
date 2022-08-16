package com.sepsoh.menschbos.playwithbots;


import com.sepsoh.menschbos.mensch.Board;
import com.sepsoh.menschbos.mensch.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;

public class FourPlayerBoardController implements Board {


    @FXML
    ImageView box0,box1,box2,box3,box4,box5;
    @FXML
    Label helperLabel;
    @FXML
    GridPane gridPane;
    public void initialize(){
        Game game = new Game();

        String blue = "sunglasses";
        String yellow = "money";
        String green = "zany";
        String red = "expressionless";

        game.buildChar("blueChar1",blue);
        game.buildChar("blueChar2",blue);
        game.buildChar("blueChar3",blue);
        game.buildChar("blueChar4",blue);

        game.buildChar("yellowChar1",yellow);
        game.buildChar("yellowChar2",yellow);
        game.buildChar("yellowChar3",yellow);
        game.buildChar("yellowChar4",yellow);

        game.buildChar("greenChar1",green);
        game.buildChar("greenChar2",green);
        game.buildChar("greenChar3",green);
        game.buildChar("greenChar4",green);

        game.buildChar("redChar1",red);
        game.buildChar("redChar2",red);
        game.buildChar("redChar3",red);
        game.buildChar("redChar4",red);

        game.start(this,"blue",200);



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
    @Override
    public GridPane getGridPane(){
        return gridPane;
    }


}
