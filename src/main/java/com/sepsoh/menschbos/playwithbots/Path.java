package com.sepsoh.menschbos.playwithbots;

import java.util.ArrayList;

public interface Path {
    Position getCurrentPosition();

    Position getNextPosition();

    int getIndex();

    ArrayList<Position> getPosition(int length);


}
