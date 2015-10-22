package musicplayer;

import musicplayer.draw.RenderHelper;
import musicplayer.windows.InstanceSwitcher;

/**
 * Created by Ratismal on 2015-10-21.
 */

public class MusicPlayer {

    public static void main(String[] argv) {
        RenderHelper rh = new RenderHelper();
        InstanceSwitcher is = new InstanceSwitcher(rh);

        MainWindow mainWindow = new MainWindow(rh, is);
        mainWindow.start();
    }

}