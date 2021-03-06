package io.github.ratismal.musicplayer.windows;


import io.github.ratismal.musicplayer.handler.MouseHandler;

/**
 * Created by Ratismal on 2015-09-15.
 */

public interface IWindowInstance {

    void render();

    void mouseInput(MouseHandler mouse);

    void doMouse(int id);

    void drawButtons();

}
