package io.github.ratismal.musicplayer.button;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class ButtonFile extends ButtonText {

    public ButtonFile(int id, Texture texture, int x, int y, String text) {
        super(id, texture, new Rectangle(x, y, texture.getTextureWidth(), texture.getTextureHeight()), text);
        left = true;
    }

}
