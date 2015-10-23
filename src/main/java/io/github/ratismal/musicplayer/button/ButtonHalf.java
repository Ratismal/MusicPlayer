package io.github.ratismal.musicplayer.button;

import io.github.ratismal.musicplayer.draw.RenderHelper;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class ButtonHalf extends ButtonText {

    String texturePath = "src/resources/gui/button/button_half";

    public ButtonHalf(int id, Rectangle rect) {
        super(id, null, rect);
        texture = RenderHelper.getTexture(texturePath);
    }

    public ButtonHalf(int id, Texture texture, int x, int y, String text) {
        super(id, texture, new Rectangle(x, y, texture.getTextureWidth(), texture.getTextureHeight()), text);
    }



}
