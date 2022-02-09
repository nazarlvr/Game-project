package render.entity;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RenderEntity
{
    public static final String textures_path = "textures/entities/";
    protected final Entity entity;
    protected BufferedImage texture;
    protected double width;
    protected double height;

    public RenderEntity(Entity e)
    {
        entity = e;
    }

    public void render(Graphics g, int x, int y, int w, int h)
    {
        //g.drawString("" + this.entity.isAirborne, x, y);
        g.drawImage(this.texture, x, y, (int)(width * w), (int)(height * h), null);
    }

    public static BufferedImage loadTexture(String filepath)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(textures_path + filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public double getWidth()
    {
        return this.width;
    }

    public double getHeight()
    {
        return this.height;
    }
}
