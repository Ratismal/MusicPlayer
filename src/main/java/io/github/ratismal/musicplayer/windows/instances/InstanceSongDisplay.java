package io.github.ratismal.musicplayer.windows.instances;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import io.github.ratismal.musicplayer.button.Button;
import io.github.ratismal.musicplayer.button.ButtonHalf;
import io.github.ratismal.musicplayer.button.ButtonLong;
import io.github.ratismal.musicplayer.draw.RenderHelper;
import io.github.ratismal.musicplayer.handler.MouseHandler;
import io.github.ratismal.musicplayer.lib.LibDisplay;
import io.github.ratismal.musicplayer.lib.LibTexture;
import io.github.ratismal.musicplayer.windows.InstanceSwitcher;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.BufferedImageUtil;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceSongDisplay extends Instance {

    String bip;
    Media hit;
    boolean refresh = true;
    public static boolean first = true;
    Button back;
    Button small;
    Button vol;
    byte[] imageData;
    Mp3File song;
    BufferedImage img;
    Texture texture = null;
    MediaPlayer mp;
    double length;
    int volume = 5;
    boolean volChanged = false;

    public List<File> songs = new ArrayList<File>();

    public InstanceSongDisplay() {
        JFXPanel fxPanel = new JFXPanel();
    }

    public void render() {
        RenderHelper.renderQuad(Color.black, new Rectangle(0, 0, Display.getWidth(), Display.getWidth() + 24));
        if (back == null) {
            back = new ButtonLong(0, LibTexture.buttonLong, 16, 450, "Back");
            small = new ButtonLong(1, LibTexture.buttonLong, 152, 450, "Smaller");
            vol = new ButtonHalf(2, LibTexture.buttonHalf, 300, 450, String.valueOf(volume));
        }
        if (volChanged) {
            System.out.println("Changing volume");
            vol.setText(String.valueOf(volume));
            mp.setVolume(((double) volume) / 10);
            volChanged = false;
        }
        if (first) {
            for (File file : InstanceMainMenu.contents) {
                if (file.getName().endsWith(".mp3")) {
                    songs.add(file);
                }
            }
            first = false;
            play();
        }

        //System.out.println(length + " " + mp.getCurrentTime().toSeconds());
        if (mp.getCurrentTime().toSeconds() > length) {
            if (songs.size() > 0) {
                mp.stop();
                play();
            } else {
                for (File file : InstanceMainMenu.contents) {
                    if (file.getName().endsWith(".mp3")) {
                        songs.add(file);
                    }
                }
                play();
            }
        }


        if (texture != null) {
            RenderHelper.renderAlbum(texture);
        }
        RenderHelper.renderTitle(song.getId3v2Tag().getTitle(), new Rectangle(0, 400, 400, 24), Color.white);

        update();
        super.drawButtons();

    }

    public void play() {
        play(ThreadLocalRandom.current().nextInt(0, songs.size()));
    }

    public void play(int rand) {
        try {
            song = new Mp3File(songs.get(rand).getPath());
            length = song.getLengthInMilliseconds() / 1000;
            if (song.hasId3v2Tag()) {
                ID3v2 id3v2tag = song.getId3v2Tag();
                new File("song-playing.txt").delete();
                PrintWriter writer = new PrintWriter("song-playing.txt", "UTF-8");
                writer.println(id3v2tag.getTitle());
                writer.close();

                if (id3v2tag.getAlbumImage() != null) {
                    imageData = id3v2tag.getAlbumImage();
                    //converting the bytes to an image
                    img = ImageIO.read(new ByteArrayInputStream(imageData));
                    texture = BufferedImageUtil.getTexture("", img);
                } else {
                    texture = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bip = songs.get(rand).toURI().toString();
        hit = new Media(bip.replace(" ", "%20"));
        mp = new MediaPlayer(hit);
        mp.setAutoPlay(false);
        mp.setVolume(((double) volume) / 10);
        mp.play();
        songs.remove(rand);

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
        buttonList.add(vol);
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
                mp.stop();
                songs.clear();
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
            case 2:
                if (volume > 1) {
                    volume--;
                } else {
                    volume = 10;
                }
                volChanged = true;
                break;
        }

    }
}
