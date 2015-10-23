package io.github.ratismal.musicplayer.lib;

import org.lwjgl.opengl.DisplayMode;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class LibDisplay {

    public static DisplayMode main;
    public static DisplayMode small;

    public static void init() {
        main = new DisplayMode(400, 500);
        small = new DisplayMode(400, 400);
    }

}
