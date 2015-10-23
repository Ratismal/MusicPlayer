package io.github.ratismal.musicplayer.lib;

import io.github.ratismal.musicplayer.draw.RenderHelper;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class LibTexture {

    public static Texture buttonLong;
    public static Texture buttonHalf;
    public static Texture buttonFile;

    public static Texture background;
    public static Texture songBack;

    public static void init() {
        buttonLong = RenderHelper.getTexture("gui/button/button_long");
        buttonHalf = RenderHelper.getTexture("gui/button/button_half");
        buttonFile = RenderHelper.getTexture("gui/button/button_file");
        songBack = RenderHelper.getTexture("gui/song_background");
        background = RenderHelper.getTexture("gui/background");
    }

}
