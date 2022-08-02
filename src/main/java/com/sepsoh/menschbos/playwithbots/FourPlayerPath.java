package com.sepsoh.menschbos.playwithbots;


import java.util.ArrayList;

public class FourPlayerPath implements Path{

    private final ArrayList<Position> path = new ArrayList<>();
    private final ArrayList<Position> target = new ArrayList<>();
    private final ArrayList<Position> base = new ArrayList<>();


    private int index = 0;
    private String name;
    private int indexOfName;
    public FourPlayerPath(String name, int indexOfName){
        this.name = name;
        this.indexOfName = indexOfName;
        //blue
        path.add(new Position(5,11));
        path.add(new Position(5,10));
        path.add(new Position(5,9));
        path.add(new Position(5,8));
        path.add(new Position(5,7));
        path.add(new Position(4,7));
        path.add(new Position(3,7));
        path.add(new Position(2,7));
        path.add(new Position(1,7));
        path.add(new Position(1,6));
        //yellow
        path.add(new Position(1,5));
        path.add(new Position(2,5));
        path.add(new Position(3,5));
        path.add(new Position(4,5));
        path.add(new Position(5,5));
        path.add(new Position(5,4));
        path.add(new Position(5,3));
        path.add(new Position(5,2));
        path.add(new Position(5,1));
        path.add(new Position(6,1));
        //green
        path.add(new Position(7,1));
        path.add(new Position(7,2));
        path.add(new Position(7,3));
        path.add(new Position(7,4));
        path.add(new Position(7,5));
        path.add(new Position(8,5));
        path.add(new Position(9,5));
        path.add(new Position(10,5));
        path.add(new Position(11,5));
        path.add(new Position(11,6));
        //red
        path.add(new Position(11,7));
        path.add(new Position(10,7));
        path.add(new Position(9,7));
        path.add(new Position(8,7));
        path.add(new Position(7,7));
        path.add(new Position(7,8));
        path.add(new Position(7,9));
        path.add(new Position(7,10));
        path.add(new Position(7,11));
        path.add(new Position(6,11));
        //blue
        base.add(new Position(2,11));
        base.add(new Position(1,11));
        base.add(new Position(1,10));
        base.add(new Position(2,10));
        //yellow
        base.add(new Position(2,2));
        base.add(new Position(1,2));
        base.add(new Position(1,1));
        base.add(new Position(2,1));
        //green
        base.add(new Position(11,2));
        base.add(new Position(10,2));
        base.add(new Position(10,1));
        base.add(new Position(11,1));
        //red
        base.add(new Position(11,11));
        base.add(new Position(10,11));
        base.add(new Position(10,10));
        base.add(new Position(11,10));
        //blue
        target.add(new Position(6,10));
        target.add(new Position(6,9));
        target.add(new Position(6,8));
        target.add(new Position(6,7));
        //yellow
        target.add(new Position(2,6));
        target.add(new Position(3,6));
        target.add(new Position(4,6));
        target.add(new Position(5,6));
        //green
        target.add(new Position(6,2));
        target.add(new Position(6,3));
        target.add(new Position(6,4));
        target.add(new Position(6,5));
        //red
        target.add(new Position(10,6));
        target.add(new Position(9,6));
        target.add(new Position(8,6));
        target.add(new Position(7,6));




    }

    public Position getNextPosition() {
            if (name.startsWith("blue")){
                if(index<40)
                    return path.get(index++);
                else
                    return null;
            }
            return getCurrentPosition();
    }
    public Position getCurrentPosition(){
        return base.get(indexOfName);

    }

}
