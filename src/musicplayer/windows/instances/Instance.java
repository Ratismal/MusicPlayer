package musicplayer.windows.instances;

import musicplayer.button.Button;
import musicplayer.button.IButton;
import musicplayer.handler.MouseHandler;
import musicplayer.util.LoggerHelper;
import musicplayer.windows.IWindowInstance;
import musicplayer.windows.InstanceSwitcher;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class Instance implements IWindowInstance {

    protected List<IButton> buttonList = new ArrayList<IButton>();

    @Override
    public void render() {
        drawButtons();
    }

    @Override
    public void mouseInput(MouseHandler mouse) {
        //LoggerHelper.info(mouse.getX() + " " + mouse.getY());
        for (IButton button : buttonList) {
            if (button.inBounds(mouse.getX(), mouse.getY())) {
                System.out.println(button.getId());
                doMouse(button.getId());
                break;
            }
        }
    }

    @Override
    public void doMouse(int id) {
        //NO-OP//
    }

    @Override
    public void drawButtons() {
        for (IButton button : buttonList) {
            button.draw();
        }
    }
}
