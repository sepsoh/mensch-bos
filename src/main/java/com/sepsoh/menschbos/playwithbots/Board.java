package com.sepsoh.menschbos.playwithbots;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public interface Board {
    ArrayList<ImageView> getBoxes();
    Label getHelperLabel();
    GridPane getGridPane();
}
