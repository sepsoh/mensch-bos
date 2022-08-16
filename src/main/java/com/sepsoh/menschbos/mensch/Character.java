package com.sepsoh.menschbos.mensch;

import com.sepsoh.menschbos.playwithbots.FourPlayerPath;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class Character {
    private ImageView img;
    private String charName;


    private Path path ;

    public static HashMap<String,Character> characters = new HashMap<>();
    public Character(String name,String charName,ImageView img) {
        this.img = img;
        this.charName = charName;
        this.path =  new FourPlayerPath(characters.size());
        Character.characters.put(name,this);
    }
    public String getCharName() {
        return charName;
    }
    public ImageView getImg() {
        return img;
    }


    public Path getPath() {
        return path;
    }

}
