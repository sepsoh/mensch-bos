package com.sepsoh.menschbos;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;

public class MenuController {
    @FXML
    ImageView loadingImageView;

    @FXML
    public void playOnline(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadingImageView.setVisible(false)));
        loadingImageView.setImage(new Image(Main.class.getResource("image/loading.gif").toString()));
        loadingImageView.setVisible(true);
        timeline.setCycleCount(1);
        timeline.play();

    }
    @FXML
    public void playWithBots(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               Main.changeScene("playWithBots.fourPlayerBoard.fxml");



            }
        }));
        loadingImageView.setImage(new Image(Main.class.getResource("image/loading.gif").toString()));
        loadingImageView.setVisible(true);

        timeline.setCycleCount(1);
        timeline.play();
    }
    @FXML
    public void exit(){
        Main.stage.close();
    }


}
