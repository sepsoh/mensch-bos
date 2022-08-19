package com.sepsoh.menschbos.mensch;

import java.util.ArrayList;

public interface Path {
    Position getCurrentPosition();

    Position getNextPosition();

    ArrayList<Position> getPosition(int length);
    boolean isInBase();
    boolean isInTarget();


}
