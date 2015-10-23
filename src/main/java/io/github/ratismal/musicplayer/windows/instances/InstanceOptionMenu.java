package io.github.ratismal.musicplayer.windows.instances;

import io.github.ratismal.musicplayer.button.Button;
import io.github.ratismal.musicplayer.button.ButtonLong;
import io.github.ratismal.musicplayer.draw.RenderHelper;
import io.github.ratismal.musicplayer.handler.MouseHandler;
import io.github.ratismal.musicplayer.lib.LibDisplay;
import io.github.ratismal.musicplayer.lib.LibTexture;
import io.github.ratismal.musicplayer.windows.InstanceSwitcher;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceOptionMenu extends Instance {

    boolean refresh = true;

    Button back;
    Button small;

    public List<File> songs = new ArrayList<File>();

    public InstanceOptionMenu() {

    }

    public void render() {

        if (back == null) {
            back = new ButtonLong(0, LibTexture.buttonLong, 16, 450, "Back");
            small = new ButtonLong(1, LibTexture.buttonLong, 152, 450, "Smaller");
        }
        if (songs.size() == 0) {
            songs.addAll(InstanceMainMenu.files);
        }

        RenderHelper.renderQuad(Color.black, new Rectangle(0, 0, Display.getWidth(), Display.getWidth()));
        update();
        super.drawButtons();

    }

    public void update() {
        if (refresh) {
            getButtons();
            refresh = false;
        }
    }

    public void getButtons() {
        buttonList.add(back);
        buttonList.add(small);
    }

    @Override
    public void mouseInput(MouseHandler mouse) {
        try {
            if (Display.getDisplayMode() == LibDisplay.small) {
                Display.setDisplayMode(LibDisplay.main);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.mouseInput(mouse);
    }

    @Override
    public void doMouse(int id) {

        switch (id) {
            case 0:
                InstanceSwitcher.setInstance(0);
                break;
            case 1:
                try {
                    if (Display.getDisplayMode() == LibDisplay.main) {
                        Display.setDisplayMode(LibDisplay.small);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
