package gui;

import entity.Entity;
import item.Item;
import item.Items;
import render.RenderManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuiItem
{
    protected final Item item;
    protected BufferedImage texture;

    public GuiItem(Item i)
    {
        item = i;
        this.texture = Items.textures_map.get(i.itemId);
    }

    public void render(Graphics g, int x, int y, int w, int h)
    {
        g.drawImage(this.texture, x, y, w, h, null);
    }
}
