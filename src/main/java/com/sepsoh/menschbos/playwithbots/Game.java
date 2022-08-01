package com.sepsoh.menschbos.playwithbots;

import com.sepsoh.menschbos.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private int turn  = 1;
    boolean isGameInitialized = false;
    public Timeline mainTimeline = new Timeline();
    private Board board;
    private HashMap<String,ImageView> characters = new HashMap<>();
    public void init(Board board){
        isGameInitialized = true;
        this.board = board;
        insertChars();
    }

    public void insertChars(){
        String blue = "sunglasses";
        String yellow = "expressionless";
        String green = "zany";
        String red = "money";

        characters.put("blueChar1",getChar(blue));
        characters.put("blueChar2",getChar(blue));
        characters.put("blueChar3",getChar(blue));
        characters.put("blueChar4",getChar(blue));

        characters.put("yellowChar1",getChar(yellow));
        characters.put("yellowChar2",getChar(yellow));
        characters.put("yellowChar3",getChar(yellow));
        characters.put("yellowChar4",getChar(yellow));

        characters.put("greenChar1",getChar(green));
        characters.put("greenChar2",getChar(green));
        characters.put("greenChar3",getChar(green));
        characters.put("greenChar4",getChar(green));

        characters.put("redChar1",getChar(red));
        characters.put("redChar2",getChar(red));
        characters.put("redChar3",getChar(red));
        characters.put("redChar4",getChar(red));

        characters.forEach((s, img) -> {
            img.setFitHeight(35);
            img.setFitWidth(35);
        });
    }
    private ImageView getChar(String charName){
        return new ImageView(new Image(Main.class.getResource("image/character/"+charName+".png").toString()));
    }

    public void start(){
        if(isGameInitialized) {
            mainTimeline.getKeyFrames().addAll(new KeyFrame(Duration.seconds(3), event -> next()));
            mainTimeline.setCycleCount(-1);
            mainTimeline.play();
        }else{
            System.out.println("you must first call init method");
        }

    }

    public void hideAllBoxes() {
        board.getBoxes().forEach(box->box.setImage(new Image(Main.class.getResource("image/hide.png").toString())));

    }
    public void changeCharPosition(String charName,int columnIndex,int rowIndex){
        ImageView img = characters.get(charName);
        board.getGridPane().add(img,1,11);
        board.getGridPane().getChildren().remove(img);
        board.getGridPane().add(img,2,11);
        GridPane.setHalignment(img, HPos.CENTER);
    }
    public void changeHelperLabel(){
        if(turn%4 ==1){
            board.getHelperLabel().getStyleClass().setAll("alert","alert-info");
            board.getHelperLabel().setText("It`s Your Turn ...");
        } else if (turn%4 ==2) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-warning");
            board.getHelperLabel().setText("Yellow`s Turn ...");
        } else if (turn%4 ==3) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-success");
            board.getHelperLabel().setText("Green`s Turn ...");
        } else if (turn%4 ==0) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-danger");
            board.getHelperLabel().setText("Red`s Turn ...");
        }
    }
    public void next(){
        hideAllBoxes();
        changeHelperLabel();

        Integer dice[] = {1,2,3,4,5,6};
        List<Integer> intList = Arrays.asList(dice);
        Collections.shuffle(intList);
        intList.toArray(dice);

        if(turn%4==1) {
            AtomicBoolean isPickedItem = new AtomicBoolean(false);
            board.getBoxes().forEach(box -> {
                box.setOnMouseEntered(t -> box.setStyle("-fx-opacity:0.5;"));
                box.setOnMouseExited(t -> box.setStyle("-fx-opacity:1;"));
                mainTimeline.pause();
                box.setOnMouseClicked(t -> {
                    if (!isPickedItem.get()) {
                        isPickedItem.set(true);
                        box.setImage(new Image(Main.class.getResource("image/dice" + dice[board.getBoxes().indexOf(box)] + ".png").toString()));

                        // remove reaction when user was picked an item
                        board.getBoxes().forEach(box2 -> box2.setOnMouseEntered(t2 -> box2.setStyle("-fx-opacity:1;")));
                        mainTimeline.setDelay(new Duration(3000));
                        mainTimeline.play();
                    }
                });

            });

        }else{
            System.out.println("dont be executed");
        }
        turn++;


    }
    public void botsTurn(){
        //ImageView box = boxes.get(ThreadLocalRandom.current().nextInt(0, 5 + 1));
        //box.setImage(new Image(Main.class.getResource("image/dice" + dice[boxes.indexOf(box)] + ".png").toString()));


    }


}
