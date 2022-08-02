package com.sepsoh.menschbos.playwithbots;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class Character {
    private ImageView img;
    private String charName;

    private Path path ;

    public static HashMap<String,Character> characters = new HashMap<>();
    public Character(String name,String charName,ImageView img) {
        this.img = img;
        this.charName = charName;
        this.path =  new FourPlayerPath(name,characters.size());
        Character.characters.put(name,this);
    }
    public String getCharName() {
        return charName;
    }
    public ImageView getImg() {
        return img;
    }

    public Position getPosition() {
        return path.getCurrentPosition();
    }
    public Position getNextPosition(){
        return path.getNextPosition();
    }
}
