package io.github.ratismal.musicplayer;

import io.github.ratismal.musicplayer.draw.RenderHelper;
import io.github.ratismal.musicplayer.windows.InstanceSwitcher;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import sun.tools.jar.Main;

import java.awt.*;
import java.io.*;

/**
 * Created by Ratismal on 2015-10-21.
 */

public class MusicPlayer {

    public static void main(String[] argv) {
        if (MusicPlayer.class.getResource("MusicPlayer.class").toString().startsWith("jar")) {

            System.out.println("Loading dlls");
            try {
                if (System.getProperty("os.arch").equals("x86")) {
                    loadLib("jinput-dx8.dll");
                    loadLib("lwjgl.dll");
                    loadLib("jinput-raw.dll");
                    loadLib("OpenAL32.dll");
                } else {
                    loadLib("jinput-dx8_64.dll");
                    loadLib("lwjgl64.dll");
                    loadLib("jinput-raw_64.dll");
                    loadLib("OpenAL64.dll");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());
        RenderHelper rh = new RenderHelper();
        InstanceSwitcher is = new InstanceSwitcher(rh);

        MainWindow mainWindow = new MainWindow(rh, is);
        mainWindow.start();
        System.out.println("Finished.");

    }

    public static void loadJarDll(String name) throws IOException {
        InputStream in = MusicPlayer.class.getResourceAsStream(name);
        if (in == null) {
            System.out.println("Cannot get resource \"" + name + "\" from Jar file.");
            return;
        }
        byte[] buffer = new byte[1024];
        MusicPlayer.class.getResource(name);
        int read = -1;
        File temp = new File(new File(System.getProperty("java.io.tmpdir")), name);
        FileOutputStream fos = new FileOutputStream(temp);

        while ((read = in.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }
        fos.close();
        in.close();

        System.load(temp.getAbsolutePath());
        System.out.println("Loaded " + name);
    }

    private static void loadLib(String name) {
        try {
            File fileOut = new File(name);

            name = "/" + name;
            System.out.println("Loading" + name);
            InputStream in = MusicPlayer.class.getResourceAsStream(name);

            OutputStream out = FileUtils.openOutputStream(fileOut);
            IOUtils.copy(in, out);

            in.close();
            out.close();
            System.load(fileOut.getAbsolutePath());//loading goes here
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
