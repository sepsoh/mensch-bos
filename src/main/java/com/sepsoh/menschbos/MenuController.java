package com.sepsoh.menschbos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuController {
    @FXML
    ImageView loadingImageView;

    @FXML
    public void playOnline(){

        loadingImageView.setVisible(true);
    }
    @FXML
    public void playWithBots(){

        loadingImageView.setVisible(true);
    }
    @FXML
    public void exit(){


    }


}
