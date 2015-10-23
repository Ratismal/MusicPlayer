package io.github.ratismal.musicplayer.windows.instances;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import io.github.ratismal.musicplayer.button.Button;
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
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class InstanceOptionMenu extends Instance {

    boolean refresh = true;

    Button back;
    Button small;

    Mp3File song;
    BufferedImage img;
    Texture texture = null;
    MediaPlayer mp;

    public List<File> songs = new ArrayList<File>();

    public InstanceOptionMenu() {
        JFXPanel fxPanel = new JFXPanel();
    }

    public void render() {
        RenderHelper.renderQuad(Color.black, new Rectangle(0, 0, Display.getWidth(), Display.getWidth() + 24));
        if (back == null) {
            back = new ButtonLong(0, LibTexture.buttonLong, 16, 450, "Back");
            small = new ButtonLong(1, LibTexture.buttonLong, 152, 450, "Smaller");
        }
        if (songs.size() == 0) {
            songs.addAll(InstanceMainMenu.contents);
            int rand = ThreadLocalRandom.current().nextInt(0, songs.size());

            try {
                song = new Mp3File(songs.get(rand).getPath());
                if (song.hasId3v2Tag()) {
                    ID3v2 id3v2tag = song.getId3v2Tag();
                    byte[] imageData = id3v2tag.getAlbumImage();
                    //converting the bytes to an image
                    img = ImageIO.read(new ByteArrayInputStream(imageData));
                    texture = BufferedImageUtil.getTexture("", img);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            play(songs.get(rand), rand);




        }


        RenderHelper.renderQuad(texture, new Rectangle(0, 0, Display.getWidth(), Display.getWidth()));
        RenderHelper.renderString(song.getId3v2Tag().getTitle(), new Rectangle(0, 400, 400, 24), Color.white);


        update();
        super.drawButtons();

    }

    public void play(File media, int rand) {
        try {
            song = new Mp3File(media.getPath());

            String bip = media.toURI().toString();
            Media hit = new Media(bip.replace(" ", "%20"));
            mp = new MediaPlayer(hit);
            mp.play();
            songs.remove(rand);
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mp.stop();
                    int rand = ThreadLocalRandom.current().nextInt(0, songs.size());
                    File song = songs.get(rand);
                    play(song, rand);
                    return;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        }

    }
}
