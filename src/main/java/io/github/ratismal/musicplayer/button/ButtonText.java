package io.github.ratismal.musicplayer.button;

import io.github.ratismal.musicplayer.draw.RenderHelper;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class ButtonText extends Button {

    protected String text;
    protected boolean left = true;

    public ButtonText(int id, Texture texture, Rectangle rect) {
        super(id, texture, rect);
    }

    public ButtonText(int id, Texture texture, Rectangle rect, String text) {
        super(id, texture, rect);
        this.text = text;
    }

    @Override
    public void draw() {
        super.draw();
        if (text != null)
            RenderHelper.renderString(text, rect);
    }
}
