package com.even.model;

import java.util.Objects;

public class Pair {
    CoOrdinate topLeft;
    CoOrdinate botRight;
    int area;

    public Pair(CoOrdinate topLeft, CoOrdinate botRight) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        int length = topLeft.x - botRight.x;
        int breadth = topLeft.y - botRight.y;
        this.area = Math.abs( length * breadth );
    }

    public int getArea(){
        return area;
    }
    public CoOrdinate getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(CoOrdinate topLeft) {
        this.topLeft = topLeft;
    }

    public CoOrdinate getBotRight() {
        return botRight;
    }

    public void setBotRight(CoOrdinate botRight) {
        this.botRight = botRight;
    }

    @Override
    public String toString() {
        return topLeft + " " + botRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return getArea() == pair.getArea() &&
                getTopLeft().equals(pair.getTopLeft()) &&
                getBotRight().equals(pair.getBotRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopLeft(), getBotRight(), getArea());
    }
}
