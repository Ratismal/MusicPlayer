package musicplayer.button;

import musicplayer.draw.RenderHelper;
import org.lwjgl.util.Rectangle;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class ButtonHalf extends ButtonText {

    String texturePath = "gui/button/button_half";

    public ButtonHalf(int id, Rectangle rect) {
        super(id, null, rect);
        texture = RenderHelper.getTexture(texturePath);
    }

    public ButtonHalf(int id, Rectangle rect, String text) {
        super(id, null, rect, text);
        texture = RenderHelper.getTexture(texturePath);
    }



}
