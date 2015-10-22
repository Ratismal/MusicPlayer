package musicplayer.windows.instances;

import musicplayer.button.Button;
import musicplayer.button.ButtonLong;
import musicplayer.draw.RenderHelper;
import musicplayer.handler.MouseHandler;
import musicplayer.windows.IWindowInstance;
import musicplayer.windows.InstanceSwitcher;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceOptionMenu extends Instance {

    private RenderHelper rh;

    Texture texLogo;
    Texture texButLong;


    public InstanceOptionMenu(RenderHelper rh) {
        this.rh = rh;
    }

    public void render() {
        texLogo = RenderHelper.getTexture("gui/logo/options");
        texButLong = RenderHelper.getTexture("gui/button/button_long");

        Button butLogo = new Button(0, texLogo,
                new Rectangle(Display.getWidth()/2 - 256, Display.getHeight()/3 - 128,
                        texLogo.getTextureWidth(), texLogo.getTextureHeight()));
        Button button1 = new ButtonLong(1,
                new Rectangle(Display.getWidth()/2 - 256, Display.getHeight()/6*5 - 64,
                        texButLong.getTextureWidth(), texButLong.getTextureHeight()), "Back");

        buttonList.add(butLogo);
        buttonList.add(button1);

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
                InstanceSwitcher.setInstance(0);
                break;
        }
    }
}
