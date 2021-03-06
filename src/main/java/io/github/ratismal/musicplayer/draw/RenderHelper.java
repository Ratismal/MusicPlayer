package io.github.ratismal.musicplayer.draw;

import io.github.ratismal.musicplayer.MusicPlayer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;

/**
 * Created by Ratismal on 2015-09-15.
 */

public class RenderHelper {

    private static Texture texture;
    private static TrueTypeFont font;

    public void initTexture(String resource) {

        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/main/resources/" + resource + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initFont(String resource, int style, int size) {
        Font awtFont = new Font(resource, style, size);
        font = new TrueTypeFont(awtFont, false);
    }

    public void renderBackground(Texture background) {
        org.newdawn.slick.Color.white.bind();
        background.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex2f(0, 0);
        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex2f(Display.getWidth(), 0);
        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex2f(Display.getWidth(), Display.getHeight());
        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex2f(0, Display.getHeight());
        GL11.glEnd();
    }

    public void renderQuad(String resource, int x, int y) {
        org.newdawn.slick.Color.white.bind();
        initTexture(resource);
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + texture.getTextureWidth(), y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + texture.getTextureHeight());
        GL11.glEnd();

    }

    public void renderQuad(String resource, int x, int y, String text, String fontResource, int style, int size, org.newdawn.slick.Color colour) {
        org.newdawn.slick.Color.white.bind();
        initTexture(resource);
        initFont(fontResource, style, size);
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + texture.getTextureWidth(), y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + texture.getTextureHeight());
        GL11.glEnd();

        renderString(text, x + texture.getTextureWidth() / 2 - font.getWidth(text) / 2, y + texture.getTextureHeight() / 2 - font.getHeight(text) / 2, colour);

    }

    public void renderQuad(String resource, int x, int y, String text, org.newdawn.slick.Color colour) {
        org.newdawn.slick.Color.white.bind();
        initTexture(resource);
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + texture.getTextureWidth(), y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + texture.getTextureHeight());
        GL11.glEnd();
        //System.out.println(texture.getTextureWidth());
        renderString(text, x + texture.getTextureWidth() / 2 - font.getWidth(text) / 2, y + texture.getTextureHeight() / 2 - font.getHeight(text) / 2, colour);

    }

    public void renderQuad(Texture texture, Rectangle rect, String text, Color colour) {
        org.newdawn.slick.Color.white.bind();
        texture.bind();

        int x1 = rect.getX();
        int x2 = x1 + rect.getWidth();
        int y1 = rect.getY();
        int y2 = y1 + rect.getHeight();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x1, y1);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x2, y1);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x2, y2);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x1, y2);
        GL11.glEnd();
        //System.out.println(texture.getTextureWidth());
        renderString(text, x1 + rect.getWidth() / 2 - font.getWidth(text) / 2, y1 + rect.getHeight() / 2 - font.getHeight(text) / 2, colour);
    }

    public static void renderQuad(Texture texture, Rectangle rect) {
        org.newdawn.slick.Color.white.bind();
        texture.bind();

        int x1 = rect.getX();
        int x2 = x1 + rect.getWidth();
        int y1 = rect.getY();
        int y2 = y1 + rect.getHeight();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x1, y1);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x2, y1);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x2, y2);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x1, y2);
        GL11.glEnd();
    }


    public static void renderAlbum(Texture texture) {
        org.newdawn.slick.Color.white.bind();
        texture.bind();

        float xTex = ((float) texture.getImageWidth()) / ((float) texture.getTextureWidth());
        float yTex = ((float) texture.getImageHeight()) / ((float) texture.getTextureHeight());

        int x1 = 0;
        int x2 = 400;
        int y1 = 0;
        int y2 = 400;
        try {
            if (texture.getImageWidth() > texture.getImageHeight()) {
                int newHeight = texture.getImageHeight() / (texture.getImageWidth() / 400);
                //System.out.println(newHeight);
                y1 = (400 - newHeight) / 2;
                y2 = 400 - (400 - newHeight) / 2;

            } else if (texture.getImageWidth() < texture.getImageHeight()) {
                int newWidth = texture.getImageWidth() / (texture.getImageHeight() / 400);
                x1 = (400 - newWidth) / 2;
                x2 = 400 - (400 - newWidth) / 2;
            }
        } catch (Exception e) {
            //uh oh!

            //...

            //AND WE JUST WALK AWAY!
        }


        //System.out.println("Dimensions: " + texture.getImageWidth() + " " + texture.getImageHeight() + " " + texture.getTextureWidth() + " " + texture.getTextureHeight());
        //System.out.println("Ratios: " + xTex + " " + yTex);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x1, y1);
        GL11.glTexCoord2f(xTex, 0);
        GL11.glVertex2f(x2, y1);
        GL11.glTexCoord2f(xTex, yTex);
        GL11.glVertex2f(x2, y2);
        GL11.glTexCoord2f(0, yTex);
        GL11.glVertex2f(x1, y2);
        GL11.glEnd();
    }

    public static void renderQuad(Color color, Rectangle rect) {
        color.bind();

        int x1 = rect.getX();
        int x2 = x1 + rect.getWidth();
        int y1 = rect.getY();
        int y2 = y1 + rect.getHeight();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x1, y1);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x2, y1);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x2, y2);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x1, y2);
        GL11.glEnd();
    }


    public static void renderString(String text, int x, int y, org.newdawn.slick.Color colour) {
        font.drawString(x, y, text, colour);
    }

    public static void renderString(String text, Rectangle rect, org.newdawn.slick.Color colour) {
        font.drawString(rect.getX() + rect.getWidth() / 2 - font.getWidth(text) / 2, rect.getY() + rect.getHeight() / 2 - font.getHeight(text) / 2, text, colour);
    }
    public static void renderTitle(String text, Rectangle rect, org.newdawn.slick.Color colour) {
        if (font.getWidth(text) > 390) {
            int split = text.indexOf(text.split(" ")[text.split(" ").length/2]);
            font.drawString(rect.getX() + rect.getWidth() / 2 - font.getWidth(text.substring(split)) / 2, rect.getY() + rect.getHeight() / 2 - font.getHeight(text.substring(split)) / 2, text.substring(split), colour);
            Rectangle rect2 = new Rectangle(0, 380, 400, 24);
            font.drawString(rect2.getX() + rect2.getWidth() / 2 - font.getWidth(text.substring(0, split)) / 2, rect2.getY() + rect2.getHeight() / 2 - font.getHeight(text.substring(0, split)) / 2, text.substring(0, split), colour);

        } else {
            font.drawString(rect.getX() + rect.getWidth() / 2 - font.getWidth(text) / 2, rect.getY() + rect.getHeight() / 2 - font.getHeight(text) / 2, text, colour);
        }
    }

    public static void renderString(String text, Rectangle rect, boolean left) {
        if (!left)
            font.drawString(rect.getX() + rect.getWidth() / 2 - font.getWidth(text) / 2, rect.getY() + rect.getHeight() / 2 - font.getHeight(text) / 2, text, Color.black);
        else
            font.drawString(8 + rect.getX(), rect.getY() + rect.getHeight() / 2 - font.getHeight(text) / 2, text, Color.black);
    }

    public static Texture getTexture(String resource) {

        try {
            if (MusicPlayer.class.getResource("MusicPlayer.class").toString().startsWith("jar")) {
                return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(resource + ".png"));
            } else {
                return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/main/resources/" + resource + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
