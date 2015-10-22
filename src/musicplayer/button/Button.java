package musicplayer.button;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import org.lwjgl.util.Rectangle;

/**
 * Created by Ratismal on 2015-10-22.
 */

public class Button implements IButton{

    protected final int id;
    protected final Rectangle rect;
    protected Texture texture;

    public Button(int id, Texture texture, Rectangle rect) {
        this.id = id;
        this.rect = rect;
        this.texture = texture;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void draw() {
        org.newdawn.slick.Color.white.bind();
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(getX1(), getY1());
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(getX2(), getY1());
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(getX2(), getY2());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(getX1(), getY2());
        GL11.glEnd();
    }

    @Override
    public int getX1() {
        return rect.getX();
    }

    @Override
    public int getX2() {
        return rect.getX() + rect.getWidth();
    }

    @Override
    public int getY1() {
        return rect.getY();
    }

    @Override
    public int getY2() {
        return rect.getY() + rect.getHeight();
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public boolean inBounds(int x, int y) {
        return (x >= getX1() && x <= getX2()) && ((y >= getY1() && y <= getY2()));
    }

}
