package com.sepsoh.menschbos.playwithbots;

import com.sepsoh.menschbos.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private int turn  = 1;
    private ArrayList<ImageView> boxes = new ArrayList<>();

    public void addBox(ImageView box) {
        boxes.add(box);
    }
    public void next(){

        Integer dice[] = {1,2,3,4,5,6};
        List<Integer> intList = Arrays.asList(dice);
        Collections.shuffle(intList);
        intList.toArray(dice);
        AtomicBoolean isPickedItem = new AtomicBoolean(false);
        boxes.forEach(box -> {
            if(turn % 4 == 1) {
                box.setOnMouseEntered(t -> box.setStyle("-fx-opacity:0.5;"));
                box.setOnMouseExited(t -> box.setStyle("-fx-opacity:1;"));
                box.setOnMouseClicked(t -> {
                    if (!isPickedItem.get()) {
                        isPickedItem.set(true);
                        box.setImage(new Image(Main.class.getResource("image/dice" + dice[boxes.indexOf(box)] + ".png").toString()));
                    }
                });
            }
        });
        turn++;
    }
    public void start(){
        next();

    }


}
