package io.github.ratismal.musicplayer.windows;


import io.github.ratismal.musicplayer.draw.RenderHelper;
import io.github.ratismal.musicplayer.handler.MouseHandler;

import io.github.ratismal.musicplayer.lib.LibTexture;
import io.github.ratismal.musicplayer.windows.instances.InstanceMainMenu;
import io.github.ratismal.musicplayer.windows.instances.InstanceOptionMenu;
import org.lwjgl.opengl.GL11;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceSwitcher {

    private RenderHelper rh;
    private InstanceMainMenu mainMenu;
    private InstanceOptionMenu optionMenu;
    private static int instance = 0;

    public InstanceSwitcher(RenderHelper rh) {
        this.rh = rh;
        this.mainMenu = new InstanceMainMenu();
        this.optionMenu = new InstanceOptionMenu();
    }

    public void renderInstance() {
        //glViewport(0, 0, Display.getWidth(), Display.getHeight());
        //glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        rh.renderBackground(LibTexture.background);
        switch(instance) {
            case 0:
                mainMenu.render();
                break;
            case 1:
                optionMenu.render();
                break;
            default:
                mainMenu.render();
                break;
        }
        GL11.glPopMatrix();
        GL11.glLoadIdentity();
    }

    public void mouseInput(MouseHandler mouse) {
        switch(instance) {
            case 0:
                mainMenu.mouseInput(mouse);
                break;
            case 1:
                optionMenu.mouseInput(mouse);
                break;
            default:
                mainMenu.mouseInput(mouse);
                break;
        }
    }

    public int getInstance() { return instance; }

    public static void setInstance(int newInstance) { instance = newInstance; }

}
