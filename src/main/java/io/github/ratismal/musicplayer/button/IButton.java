package io.github.ratismal.musicplayer.button;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-10-22.
 */

public interface IButton {

    int getId();

    void draw();

    int getX1();

    int getX2();

    int getY1();

    int getY2();

    Texture getTexture();

    Rectangle getRect();

    boolean inBounds(int x, int y);


}
