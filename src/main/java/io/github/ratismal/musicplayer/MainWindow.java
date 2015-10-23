package io.github.ratismal.musicplayer;


import io.github.ratismal.musicplayer.draw.RenderHelper;
import io.github.ratismal.musicplayer.handler.MouseHandler;
import io.github.ratismal.musicplayer.lib.LibDisplay;
import io.github.ratismal.musicplayer.lib.LibTexture;
import io.github.ratismal.musicplayer.windows.InstanceSwitcher;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.awt.Font;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class MainWindow {

    private RenderHelper rh;
    private InstanceSwitcher instanceSwitcher;
    private MouseHandler mouse;

    public MainWindow(RenderHelper renderHelper, InstanceSwitcher instanceSwitcher) {

        this.rh = renderHelper;
        this.instanceSwitcher = instanceSwitcher;

    }

    public void start() {
        mouse = new MouseHandler();
        LibDisplay.init();
        initGL(LibDisplay.main);
        LibTexture.init();
        rh.initFont("Courier New", Font.BOLD, 16);
        while (true) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            instanceSwitcher.renderInstance();
            pollInput();
            Display.update();
            Display.sync(100);

            if (Display.wasResized()) {
                initOtherGL(Display.getWidth(), Display.getHeight());
            }

            if (Display.isCloseRequested()) {
                Display.destroy();
                System.exit(0);
            }
        }
    }

    public void initGL(DisplayMode mode) {
        /*
        try {
            Display.setDisplayMode(new DisplayMode(x, y));
            Display.setResizable(true);
            Display.setTitle("Japanese Quiz");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Display wasn't initialized correctly.");
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
*/

        try {
            Display.setDisplayMode(mode);
            Display.create();
            Display.setVSyncEnabled(true);
            Display.setResizable(true);
            Display.setTitle("Music Player");
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        initOtherGL(mode.getWidth(), mode.getHeight());
    }

    public void initOtherGL(int width, int height) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0, 0, width, height);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void pollInput() {

        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    if (Mouse.isButtonDown(0)) {
                        mouse.setX(Mouse.getX());
                        mouse.setY(Display.getHeight() - Mouse.getY());

                        //System.out.println("MOUSE DOWN @ X: " + Mouse.getX() + " Y: " + Mouse.getY());
                        instanceSwitcher.mouseInput(mouse);
                    }
                }
            }
        }
/*
        if (Mouse.isButtonDown(0)) {
            mouse.setX(Mouse.getX());
            mouse.setY(Display.getHeight() - Mouse.getY());

            //System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
            instanceSwitcher.mouseInput(mouse);
        }
*/
    }


}
