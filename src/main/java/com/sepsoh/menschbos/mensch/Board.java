package com.sepsoh.menschbos.mensch;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public interface Board {
    ArrayList<ImageView> getBoxes();
    Label getHelperLabel();
    GridPane getGridPane();
    void changeHelperLabel(int turn);
}
