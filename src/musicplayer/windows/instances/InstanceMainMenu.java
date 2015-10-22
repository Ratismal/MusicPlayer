package musicplayer.windows.instances;


import musicplayer.button.Button;
import musicplayer.button.ButtonHalf;
import musicplayer.button.ButtonLong;
import musicplayer.button.IButton;
import musicplayer.handler.MouseHandler;
import musicplayer.draw.RenderHelper;
import musicplayer.util.LoggerHelper;
import musicplayer.windows.IWindowInstance;
import musicplayer.windows.InstanceSwitcher;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import org.lwjgl.util.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceMainMenu extends Instance {

    private RenderHelper rh;
    Texture texLogo;
    Texture texButLong;
    Texture texButHalf;

    public InstanceMainMenu(RenderHelper rh) {
        this.rh = rh;
    }

    public void render() {
        texLogo = RenderHelper.getTexture("gui/logo/logo");
        texButLong = RenderHelper.getTexture("gui/button/button_long");
        texButHalf = RenderHelper.getTexture("gui/button/button_half");

        Button buttonLogo = new Button(0, texLogo,
                new Rectangle(Display.getWidth() / 2 - 256, Display.getHeight() / 3 - 128,
                        texLogo.getTextureWidth(), texLogo.getTextureHeight()));
        Button button1 = new ButtonLong(1,
                new Rectangle(Display.getWidth() / 2 - 256, Display.getHeight() / 3 * 2 - 64,
                        texButLong.getTextureWidth(), texButLong.getTextureHeight()), "Start");
        Button button2 = new ButtonHalf(2,
                new Rectangle(Display.getWidth() / 2 - 256, Display.getHeight() / 3 * 2 + 72,
                        texButHalf.getTextureWidth(), texButHalf.getTextureHeight()), "Options");
        Button button3 = new ButtonHalf(3,
                new Rectangle(Display.getWidth() / 2 + 3, Display.getHeight() / 3 * 2 + 72,
                        texButHalf.getTextureWidth(), texButHalf.getTextureHeight()), "Quit");

        buttonList.add(buttonLogo);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);

        super.render();
    }

    @Override
    public void mouseInput(MouseHandler mouse) {
        super.mouseInput(mouse);
    }

    @Override
    public void doMouse(int id) {
        switch (id) {
            case 1:
                //LoggerHelper.info("start");
                break;
            case 2:
                //LoggerHelper.info("option");
                InstanceSwitcher.setInstance(1);
                break;
            case 3:
                //LoggerHelper.info("quit");
                Display.destroy();
                System.exit(0);
                break;
        }
    }
}
