package com.sepsoh.menschbos.mensch;

import com.sepsoh.menschbos.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private int turn  = 0;

    private Board board;
    private int yourDiceNumber = 0;

    public void start(Board board, String yourChar,int gameSpeedMils){
        this.board = board;

        Character.characters.forEach((charName, chr) -> {
            // set yourChar onClick :
            if(charName.startsWith(yourChar)) {
                chr.getImg().setOnMouseClicked(event -> clickedOn(charName));
                chr.getImg().setOnMouseEntered(event -> chr.getImg().setOpacity(0.5));
                chr.getImg().setOnMouseExited(event -> chr.getImg().setOpacity(1));
            }
            //initialize first place :
            changeCharPosition(charName,chr.getPath().getCurrentPosition());
        });

        next();




    }
    private void clickedOn(String on) {
        if(yourDiceNumber != 0) {
            ArrayList<Position> pos = Character.characters.get(on).getPath().getPosition(yourDiceNumber);
            if(!pos.isEmpty())
                changeCharPosition(on,pos.get(pos.size()-1));

            yourDiceNumber = 0;
            next();
        }

    }
    public Character buildChar(String name,String charName){
        Character chr =  new Character(name,charName,new ImageView(new Image(Main.class.getResource("image/character/"+charName+".png").toString())));
        chr.getImg().setFitHeight(35);
        chr.getImg().setFitWidth(35);
        return chr;
    }
    private void hideAllBoxes() {
        board.getBoxes().forEach(box->box.setImage(new Image(Main.class.getResource("image/hide.png").toString())));

    }
    private void changeCharPosition(String charName,Position pos){
        if(pos != null) {
            ImageView img = Character.characters.get(charName).getImg();
            board.getGridPane().getChildren().remove(img);
            board.getGridPane().add(img, pos.getColumn(), pos.getRow());
            GridPane.setHalignment(img, HPos.CENTER);
        }else{
            System.out.println("position is null");
        }
    }
    private void changeHelperLabel(){
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
    private Integer[] dice(){
        Integer[] dice = {1,2,3,4,5,6};
        List<Integer> intList = Arrays.asList(dice);
        Collections.shuffle(intList);
        intList.toArray(dice);
        return dice;
    }
    private void next(){
        turn++;
        hideAllBoxes();
        changeHelperLabel();

        if(turn%4==1)
            yourTurn();
        else
            botsTurn();


        //changeCharPosition("blueChar2",Character.characters.get("blueChar2").getPath().getNextPosition());


    }
    private void botsTurn(){
        ImageView box = board.getBoxes().get(ThreadLocalRandom.current().nextInt(0, 5 + 1));
        int dice = dice()[board.getBoxes().indexOf(box)];
        box.setImage(new Image(Main.class.getResource("image/dice" + dice + ".png").toString()));
        //changeCharPosition(Character.characters.);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),event -> next()));
        timeline.setCycleCount(1);
        timeline.play();
        }
    private void yourTurn(){
        board.getBoxes().forEach(box -> {
            box.setOnMouseEntered(t -> box.setStyle("-fx-opacity:0.5;"));
            box.setOnMouseExited(t -> box.setStyle("-fx-opacity:1;"));
            box.setOnMouseClicked(t -> {
                if (yourDiceNumber == 0 && turn%4 == 1) {
                    yourDiceNumber = dice()[board.getBoxes().indexOf(box)];
                    box.setImage(new Image(Main.class.getResource("image/dice" + yourDiceNumber+ ".png").toString()));
                    // remove reaction when user was picked an item
                    board.getBoxes().forEach(box2 -> box2.setOnMouseEntered(t2 -> box2.setStyle("-fx-opacity:1;")));
                }
            });


        });

    }


}
