package gui;

import block.Blocks;
import entity.Entity;
import item.Item;
import item.Items;
import render.RenderManager;
import render.block.RenderBlock;

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

        if (i != null)
        {
            if (i.itemId > 0)
                this.texture = Items.textures_map.get(i.itemId);
            else
                this.texture = Blocks.textures_map.get(-i.itemId);
        }

    }

    public void render(Graphics g, int x, int y, int w, int h)
    {
        if (this.item.itemId > 0)
            g.drawImage(this.texture, x, y, w, h, null);
        else
        {
            RenderBlock rb = RenderManager.getRender(Blocks.getBlockFromItem(this.item));

            if (rb != null)
                rb.render(g, x, y, w, h);
        }
    }

    public void render(Graphics g, int x, int y, int w, int h, int n)
    {
        this.render(g, x, y, w, h);

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, h * 4 / 16));
        //guiItem.render(g, this.minX + slotWidth / 18, this.minY + slotHeight / 18, slotWidth * 16 / 18, slotHeight * 16 / 18);
        g.drawString(String.valueOf(n), x + w * 1 / 16, y + h * 15 / 16);
    }
}
