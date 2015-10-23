package io.github.ratismal.musicplayer.button;

import io.github.ratismal.musicplayer.lib.LibTexture;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class ButtonLong extends ButtonText {



    public ButtonLong(int id, Rectangle rect) {
        super(id, null, rect);
        texture = LibTexture.buttonLong;
    }

    public ButtonLong(int id, Texture texture, int x, int y, String text) {
        super(id, texture, new Rectangle(x, y, texture.getTextureWidth(), texture.getTextureHeight()), text);
    }
}
