package io.github.ratismal.musicplayer.handler;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class MouseHandler {

    private int x;
    private int y;

    public MouseHandler() {

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    /**
     * Checks if last stored mouse location is within a given range
     * @param x1 Most left X value
     * @param x2 Most right X value
     * @param y1 Top Y value
     * @param y2 Bottom Y value
     * @return True if in range
     */
    public boolean isInRange(int x1, int x2, int y1, int y2) {
        System.out.println("X=" + x + " Y=" + y);
        System.out.println("Checking if X is between " + x1 + " and " + x2 + ", " +
                "Checking if Y is between " + y1 + " and " + y2);
        if ((x >= x1) && (x <= x2) && (y >= y1) && (y <= y2)) {
            System.out.println("meow");
            return true;
        }
        return false;
    }

}
