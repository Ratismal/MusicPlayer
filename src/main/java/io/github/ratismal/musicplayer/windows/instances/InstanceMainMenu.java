package io.github.ratismal.musicplayer.windows.instances;

import io.github.ratismal.musicplayer.button.Button;
import io.github.ratismal.musicplayer.button.ButtonFile;
import io.github.ratismal.musicplayer.button.ButtonHalf;
import io.github.ratismal.musicplayer.button.ButtonLong;
import io.github.ratismal.musicplayer.handler.MouseHandler;
import io.github.ratismal.musicplayer.lib.LibTexture;
import musicplayer.windows.InstanceSwitcher;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceMainMenu extends Instance {

    int entriesPP = 24;

    boolean refresh = true;
    boolean showHidden = false;

    public static int page = 0;
    public static File currentDir = null;
    public static List<File> files = new ArrayList<File>();
    public static List<File> contents = new ArrayList<File>();

    Button display;
    Button next;
    Button prev;
    Button back;
    Button show;
    Button exit;

    public InstanceMainMenu() {
    }

    public void render() {

        if (currentDir == null) {
            currentDir = new File("C:/");
        }
        if (display == null) {
            display = new ButtonLong(32, LibTexture.buttonLong, 16, 450, "Select");
            exit = new ButtonLong(37, LibTexture.buttonLong, 152, 450, "Quit");
            next = new ButtonHalf(33, LibTexture.buttonHalf, 280, 132, "-->");
            prev = new ButtonHalf(34, LibTexture.buttonHalf, 280, 172, "<--");
            back = new ButtonHalf(35, LibTexture.buttonHalf, 280, 92, "Up");
            show = new ButtonHalf(36, LibTexture.buttonHalf, 280, 212, "Show");
        }

        update();
        super.drawButtons();

    }

    public void update() {
        if (refresh) {
            System.out.println("Refreshing buttons");
            files.clear();
            buttonList.clear();
            checkButtons();
            populateIndex();
            getButtons();

            refresh = false;
        }
    }

    public void checkButtons() {
        if (!containsMp3()) {
            display.setVisible(false);
        } else {
            display.setVisible(true);
        }
    }

    public void populateIndex() {

        files.clear();
        for (final File fileEntry : currentDir.listFiles()) {
            if (fileEntry.isDirectory() && (!fileEntry.isHidden() || showHidden))
                files.add(fileEntry);
            else
                contents.add(fileEntry);
        }
        refresh = false;

    }

    public void getButtons() {

        buttonList.clear();

        buttonList.add(display);
        buttonList.add(next);
        buttonList.add(prev);
        buttonList.add(back);
        buttonList.add(show);
        buttonList.add(exit);
        if (files.size() > 0) {
            for (int i = 0; i + entriesPP * page < entriesPP + entriesPP * page && i + entriesPP * page <= files.size() - 1; i++) {
                buttonList.add(new ButtonFile(i, LibTexture.buttonFile, 8, (i + 1) * 17,
                        files.get(i + (entriesPP) * page).getName()));
            }
        }
    }

    @Override
    public void mouseInput(MouseHandler mouse) {
        super.mouseInput(mouse);
    }


    @Override
    public void doMouse(int id) {
        switch (id) {
            case 32:
                if (containsMp3())
                    InstanceSwitcher.setInstance(1);
                break;
            case 37:
                Display.destroy();
                System.exit(0);
                break;
            case 33:
                System.out.println(files.size() + " " + entriesPP);
                double test = files.size() / entriesPP;
                System.out.println((int) test + " " + page);
                if (page < files.size() / (entriesPP + 1)) {
                    page++;
                    refresh = true;
                }
                break;
            case 34:
                if (page > 0) {
                    page--;
                    refresh = true;
                }
                break;
            case 35:
                if (currentDir.getParentFile() != null) {
                    currentDir = currentDir.getParentFile();
                    refresh = true;
                }
                break;
            case 36:
                showHidden = !showHidden;
                refresh = true;
                break;
            default:
                File thing = files.get(id + entriesPP * page);
                if (thing.listFiles() == null) {
                    break;
                }
                if (thing.isDirectory()) {
                    currentDir = thing;
                }
                refresh = true;
                break;
        }
    }

    public boolean containsMp3() {
        boolean contains = false;
        check:
        for (File f : contents) {
            if (f.getName().endsWith(".mp3")) {
                contains = true;
                break check;
            }
        }
        return contains;
    }
}
