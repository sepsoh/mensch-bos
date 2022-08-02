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
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private int turn  = 1;
    boolean isGameInitialized = false;
    public Timeline mainTimeline = new Timeline();
    private Board board;
    public void init(Board board){
        isGameInitialized = true;
        this.board = board;
        insertChars();
    }

    public void insertChars(){

        String blue = "sunglasses";
        String yellow = "money";
        String green = "zany";
        String red = "expressionless";

        buildChar("blueChar1",blue);
        buildChar("blueChar2",blue);
        buildChar("blueChar3",blue);
        buildChar("blueChar4",blue);

        buildChar("yellowChar1",yellow);
        buildChar("yellowChar2",yellow);
        buildChar("yellowChar3",yellow);
        buildChar("yellowChar4",yellow);

        buildChar("greenChar1",green);
        buildChar("greenChar2",green);
        buildChar("greenChar3",green);
        buildChar("greenChar4",green);

        buildChar("redChar1",red);
        buildChar("redChar2",red);
        buildChar("redChar3",red);
        buildChar("redChar4",red);

        Character.characters.forEach((charName, character) -> {
            character.getImg().setFitHeight(35);
            character.getImg().setFitWidth(35);
            if(charName.equals("blueChar1") || charName.equals("blueChar2") || charName.equals("blueChar3") || charName.equals("blueChar4"))
            {
                character.getImg().setOnMouseClicked(event -> clickedOn(charName));
                character.getImg().setOnMouseEntered(event -> character.getImg().setOpacity(0.5));
                character.getImg().setOnMouseExited(event -> character.getImg().setOpacity(1));
            }
            changeCharPosition(charName,character.getPosition());

        });
        //changeCharPosition("redChar1", buildChar());
    }

    private void clickedOn(String on)
    {
        System.out.println("cliced on : " +on);
    }


    private Character buildChar(String name,String charName){
        return new Character(name,charName,new ImageView(new Image(Main.class.getResource("image/character/"+charName+".png").toString())));
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
    public void changeCharPosition(String charName,Position pos){
        ImageView img = Character.characters.get(charName).getImg();
        board.getGridPane().getChildren().remove(img);
        board.getGridPane().add(img,pos.getColumn(),pos.getRow());
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
        changeCharPosition("blueChar1",Character.characters.get("blueChar1").getNextPosition());

        turn++;


    }
    public void botsTurn(){
        //ImageView box = boxes.get(ThreadLocalRandom.current().nextInt(0, 5 + 1));
        //box.setImage(new Image(Main.class.getResource("image/dice" + dice[boxes.indexOf(box)] + ".png").toString()));


    }


}
