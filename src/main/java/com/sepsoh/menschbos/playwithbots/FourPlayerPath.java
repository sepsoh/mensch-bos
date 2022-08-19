
package com.sepsoh.menschbos.playwithbots;


import com.sepsoh.menschbos.mensch.Character;
import com.sepsoh.menschbos.mensch.Path;
import com.sepsoh.menschbos.mensch.Position;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class FourPlayerPath implements Path {

    private final ArrayList<Position> route = new ArrayList<>();
    private final ArrayList<Position> blueBase      = new ArrayList<>();
    private final ArrayList<Position> yellowBase    = new ArrayList<>();
    private final ArrayList<Position> greenBase     = new ArrayList<>();
    private final ArrayList<Position> redBase       = new ArrayList<>();

    private final ArrayList<Position> bluePath      = new ArrayList<>();
    private final ArrayList<Position> yellowPath    = new ArrayList<>();
    private final ArrayList<Position> greenPath     = new ArrayList<>();
    private final ArrayList<Position> redPath       = new ArrayList<>();

    private final ArrayList<Position> blueTarget      = new ArrayList<>();
    private final ArrayList<Position> yellowTarget    = new ArrayList<>();
    private final ArrayList<Position> greenTarget     = new ArrayList<>();
    private final ArrayList<Position> redTarget       = new ArrayList<>();


    private final ArrayList<Position> blueRoute      = new ArrayList<>();
    private final ArrayList<Position> yellowRoute    = new ArrayList<>();
    private final ArrayList<Position> greenRoute     = new ArrayList<>();
    private final ArrayList<Position> redRoute       = new ArrayList<>();

    private int index = 0;
    private int id; //blue : 0-3  ,yellow : 4-7  ,green : 8-11  ,red : 12-15

    public FourPlayerPath(int id){
        this.id = id;
        this.index = id%4;  //Base Cell init

        //blue's base
        blueBase.add(new Position(2,11));
        blueBase.add(new Position(1,11));
        blueBase.add(new Position(1,10));
        blueBase.add(new Position(2,10));
        //yellow's base
        yellowBase.add(new Position(2,2));
        yellowBase.add(new Position(1,2));
        yellowBase.add(new Position(1,1));
        yellowBase.add(new Position(2,1));
        //green's base
        greenBase.add(new Position(11,2));
        greenBase.add(new Position(10,2));
        greenBase.add(new Position(10,1));
        greenBase.add(new Position(11,1));
        //red's base
        redBase.add(new Position(11,11));
        redBase.add(new Position(10,11));
        redBase.add(new Position(10,10));
        redBase.add(new Position(11,10));
        //blue's path
        bluePath.add(new Position(5,11));
        bluePath.add(new Position(5,10));
        bluePath.add(new Position(5,9));
        bluePath.add(new Position(5,8));
        bluePath.add(new Position(5,7));
        bluePath.add(new Position(4,7));
        bluePath.add(new Position(3,7));
        bluePath.add(new Position(2,7));
        bluePath.add(new Position(1,7));
        bluePath.add(new Position(1,6));
        //yellow's path
        yellowPath.add(new Position(1,5));
        yellowPath.add(new Position(2,5));
        yellowPath.add(new Position(3,5));
        yellowPath.add(new Position(4,5));
        yellowPath.add(new Position(5,5));
        yellowPath.add(new Position(5,4));
        yellowPath.add(new Position(5,3));
        yellowPath.add(new Position(5,2));
        yellowPath.add(new Position(5,1));
        yellowPath.add(new Position(6,1));
        //green's path
        greenPath.add(new Position(7,1));
        greenPath.add(new Position(7,2));
        greenPath.add(new Position(7,3));
        greenPath.add(new Position(7,4));
        greenPath.add(new Position(7,5));
        greenPath.add(new Position(8,5));
        greenPath.add(new Position(9,5));
        greenPath.add(new Position(10,5));
        greenPath.add(new Position(11,5));
        greenPath.add(new Position(11,6));
        //red's path
        redPath.add(new Position(11,7));
        redPath.add(new Position(10,7));
        redPath.add(new Position(9,7));
        redPath.add(new Position(8,7));
        redPath.add(new Position(7,7));
        redPath.add(new Position(7,8));
        redPath.add(new Position(7,9));
        redPath.add(new Position(7,10));
        redPath.add(new Position(7,11));
        redPath.add(new Position(6,11));
        //blue's target
        blueTarget.add(new Position(6,10));
        blueTarget.add(new Position(6,9));
        blueTarget.add(new Position(6,8));
        blueTarget.add(new Position(6,7));
        //yellow's target
        yellowTarget.add(new Position(2,6));
        yellowTarget.add(new Position(3,6));
        yellowTarget.add(new Position(4,6));
        yellowTarget.add(new Position(5,6));
        //green's target
        greenTarget.add(new Position(6,2));
        greenTarget.add(new Position(6,3));
        greenTarget.add(new Position(6,4));
        greenTarget.add(new Position(6,5));
        //red's target
        redTarget.add(new Position(10,6));
        redTarget.add(new Position(9,6));
        redTarget.add(new Position(8,6));
        redTarget.add(new Position(7,6));




        // Routes :

        blueRoute.addAll(blueBase);
        blueRoute.addAll(bluePath);
        blueRoute.addAll(yellowPath);
        blueRoute.addAll(greenPath);
        blueRoute.addAll(redPath);
        blueRoute.addAll(blueTarget);

        yellowRoute.addAll(yellowBase);
        yellowRoute.addAll(yellowPath);
        yellowRoute.addAll(greenPath);
        yellowRoute.addAll(redPath);
        yellowRoute.addAll(bluePath);
        yellowRoute.addAll(yellowTarget);

        greenRoute.addAll(greenBase);
        greenRoute.addAll(greenPath);
        greenRoute.addAll(redPath);
        greenRoute.addAll(bluePath);
        greenRoute.addAll(yellowPath);
        greenRoute.addAll(greenTarget);

        redRoute.addAll(redBase);
        redRoute.addAll(redPath);
        redRoute.addAll(bluePath);
        redRoute.addAll(yellowPath);
        redRoute.addAll(greenPath);
        redRoute.addAll(redTarget);


        // Removing unuseful arraylists

    }


    private ArrayList<Position> getRoute(){
        if(id/4 == 0)
            return blueRoute;
        else if(id/4 ==1)
            return yellowRoute;
        else if(id/4 ==2)
            return greenRoute;
        else if(id/4 ==3)
            return redRoute;

        return null;

    }
    private boolean isCellFree(int length){
        //check that first dice is 6
        if(index <4 && length != 6)
            return false;

        // check that it does not hit another
        int indexCopy = index;
        int newIndex ;
        for(int i= 0 ;i<length; i++)
            indexUp();
        newIndex = index;
        index = indexCopy;
        AtomicBoolean ret = new AtomicBoolean(false);
        Character.characters.forEach(chr-> {
            if(chr.getPath().getCurrentPosition().isEqual(getRoute().get(newIndex)));
                ret.set(true);
        });
        return ret.get();
    }

    public ArrayList<Position> getPosition(int length){
        ArrayList<Position> ret = new ArrayList<>();
        if(isCellFree(length)) {
            if(length==6 && index <4)
                length =1;
            for (int i = 0; i < length; i++)
                ret.add(getNextPosition());

        }
            return ret;
    }

    @Override
    public boolean isInBase() {
        return index<4;
    }

    public Position getNextPosition() {

        return getRoute().get(indexUp());
    }


    private int indexUp(){
        if(index<4)
            return index =4;
        else if(index<47)
            return ++index;
        return index;

    }

    public Position getCurrentPosition(){
        return getRoute().get(index);

    }

}
