package org.Package;

public class Position {

    public int x;
    public int y;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void echange(Position Position1) {
        this.x = Position1.x;
        this.y = Position1.y;
    }


    public boolean verifPos(Object test) {
        if (this == test) return true;
        if (test == null || getClass() != test.getClass()) return false;
        Position position = (Position) test;
        return x == position.x && y == position.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}