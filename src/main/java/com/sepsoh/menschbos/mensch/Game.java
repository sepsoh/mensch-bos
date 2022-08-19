package com.sepsoh.menschbos.mensch;

import com.sepsoh.menschbos.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private int turn  = -1;

    private Board board;
    private int yourDiceNumber = 0;
    private boolean hasGift = false;
    private int lastMovedCharId ;
    public void start(Board board, String yourChar,int gameSpeedMils){
        this.board = board;

        Character.characters.forEach(chr -> {
            // set yourChar onClick :
            if(chr.getName().startsWith(yourChar)) {
                chr.getImg().setOnMouseClicked(event -> clickedOn(chr));
                chr.getImg().setOnMouseEntered(event -> chr.getImg().setOpacity(0.5));
                chr.getImg().setOnMouseExited(event -> chr.getImg().setOpacity(1));
            }
            //initialize first place :
            changeCharPosition(chr,chr.getPath().getCurrentPosition());
        });

        next();




    }
    private void clickedOn(Character chr) {
        if(yourDiceNumber != 0) {
            ArrayList<Position> pos = chr.getPath().getPosition(yourDiceNumber);
            if(!pos.isEmpty())
                changeCharPosition(chr,pos.get(pos.size()-1));

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
    private void changeCharPosition(Character chr,Position pos){
        if(pos != null) {
            ImageView img = chr.getImg();
            board.getGridPane().getChildren().remove(img);
            board.getGridPane().add(img, pos.getColumn(), pos.getRow());
            GridPane.setHalignment(img, HPos.CENTER);
        }else{
            System.out.println("position is null");
        }
    }
    private void changeHelperLabel(){
        if(turn%4 ==0){
            board.getHelperLabel().getStyleClass().setAll("alert","alert-info");
            board.getHelperLabel().setText("It`s Your Turn ...");
        } else if (turn%4 ==1) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-warning");
            board.getHelperLabel().setText("Yellow`s Turn ...");
        } else if (turn%4 ==2) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-success");
            board.getHelperLabel().setText("Green`s Turn ...");
        } else if (turn%4 ==3) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-danger");
            board.getHelperLabel().setText("Red`s Turn ...");
        }
    }
    private void changeHelperLabel(String msg){
        if(turn%4 ==0){
            board.getHelperLabel().getStyleClass().setAll("alert","alert-info");
            board.getHelperLabel().setText(msg);
        } else if (turn%4 ==1) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-warning");
            board.getHelperLabel().setText(msg);
        } else if (turn%4 ==2) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-success");
            board.getHelperLabel().setText(msg);
        } else if (turn%4 ==3) {
            board.getHelperLabel().getStyleClass().setAll("alert","alert-danger");
            board.getHelperLabel().setText(msg);
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
        if(isGameOvered())
            return;

        if (!hasGift)
            turn++;

        hideAllBoxes();
        changeHelperLabel();

        if(turn%4==0)
            yourTurn();
        else
            botsTurn();

        //System.out.println("Turn : "+turn);

    }
    private void botsTurn(){
        ImageView box = board.getBoxes().get(ThreadLocalRandom.current().nextInt(0, 5 + 1));
        int dice = dice()[board.getBoxes().indexOf(box)];

        box.setImage(new Image(Main.class.getResource("image/dice" + dice + ".png").toString()));


        if(!hasGift)
            lastMovedCharId = ThreadLocalRandom.current().nextInt((turn%4)*4,(turn%4)*4+4);
        hasGift = dice == 6;

        Character chr = Character.characters.get(lastMovedCharId);
        ArrayList<Position> pos = chr.getPath().getPosition(dice);
        if(!pos.isEmpty())
            changeCharPosition(chr,pos.get(pos.size()-1));


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),event ->  next() ));
        timeline.setCycleCount(1);
        timeline.play();
        }
    private void yourTurn(){
        board.getBoxes().forEach(box -> {
            box.setOnMouseEntered(t -> box.setStyle("-fx-opacity:0.5;"));
            box.setOnMouseExited(t -> box.setStyle("-fx-opacity:1;"));
            box.setOnMouseClicked(t -> {
                if (yourDiceNumber == 0 && turn%4 == 0) {
                    yourDiceNumber = dice()[board.getBoxes().indexOf(box)];
                    if (yourDiceNumber == 6)
                        hasGift = true;
                    else
                        hasGift = false;

                    box.setImage(new Image(Main.class.getResource("image/dice" + yourDiceNumber+ ".png").toString()));
                    // remove reaction when user was picked an item
                    board.getBoxes().forEach(box2 -> box2.setOnMouseEntered(t2 -> box2.setStyle("-fx-opacity:1;")));

                    if(!areYouQualified()){
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2),event ->
                        {
                            yourDiceNumber = 0;
                            next();
                        }));
                        timeline.setCycleCount(1);
                        timeline.play();

                    }

                }
            });


        });



    }

    private boolean areYouQualified(){
        // in first, you must take 6 dice number to bring a character to the game.
        for(int i=0;i<4;i++)
            if(!Character.characters.get(i).getPath().isInBase())
                return true;
        return false;
    }
    private boolean isGameOvered(){
        boolean areInTarget = true;
        int winingTeam = -1;

        for(int i=0;i<16;i++){
            areInTarget = areInTarget && Character.characters.get(i).getPath().isInTarget();
            if(i%4 ==3 /* last char in each team */ && areInTarget)
                winingTeam = i/4;
            else if(i%4 ==3)
                areInTarget =true;

        }
        if (winingTeam ==0)
            changeHelperLabel("You Win ...");
        else if (winingTeam ==1)
            changeHelperLabel("Yellow's Win ...");
        else if (winingTeam ==2)
            changeHelperLabel("Green's Win ...");
        else if (winingTeam == 3)
            changeHelperLabel("Red's Win ...");
        else
            return false;
        return true;

    }

}
