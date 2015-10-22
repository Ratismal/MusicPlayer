package musicplayer.button;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import musicplayer.draw.RenderHelper;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class ButtonLong extends ButtonText {

    String texturePath = "gui/button/button_long";

    public ButtonLong(int id, Rectangle rect) {
        super(id, null, rect);
        texture = RenderHelper.getTexture(texturePath);
    }

    public ButtonLong(int id, Rectangle rect, String text) {
        super(id, null, rect, text);
        texture = RenderHelper.getTexture(texturePath);
    }

}
