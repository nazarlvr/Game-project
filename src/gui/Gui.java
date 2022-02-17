package gui;

import block.Blocks;
import inventory.Inventory;
import item.ItemStack;
import render.entity.RenderItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui
{
    public static final String textures_path = "textures/gui/";
    protected BufferedImage texture;
    Inventory inventory;

    public Gui( Inventory inventory) {
        this.texture = loadTexture(textures_path + "inventory.png" );
        this.inventory = inventory;
    }


    public void render(Graphics g, int x, int y, int w, int h)
    {
        g.drawImage(this.texture, x, y, w, h, null);
        for (int i = 0; i < inventory.size; i++) {
            if (inventory.items[i] != null) {
                GuiItem guiItem = new GuiItem(inventory.items[i].item);
                guiItem.render(g, x + 60 + (i % 9)  * 122 , y + 55 + i / 9 * 122, w / 10, h / 5);
            }
        }
    }

    public static BufferedImage loadTexture(String filepath)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
