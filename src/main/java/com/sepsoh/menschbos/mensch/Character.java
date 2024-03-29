package com.sepsoh.menschbos.mensch;

import com.sepsoh.menschbos.playwithbots.FourPlayerPath;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class Character {
    private ImageView img;
    private String charName;
    private String name;
    private Path path ;
    public static ArrayList<Character> characters = new ArrayList<>();

    public Character(String name,String charName,ImageView img) {
        this.img = img;
        this.charName = charName;
        this.path =  new FourPlayerPath(characters.size());
        this.name = name;
        Character.characters.add(this);
    }
    public String getCharName() {
        return charName;
    }
    public String getName() {
        return name;
    }
    public ImageView getImg() {
        return img;
    }


    public Path getPath() {
        return path;
    }

}
