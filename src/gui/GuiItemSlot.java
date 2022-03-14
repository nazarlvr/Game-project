package gui;

import inventory.Inventory;
import item.Item;
import item.ItemStack;
import item.Items;
import render.RenderManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GuiItemSlot
{
    //protected final GuiItem guiItem;
    protected final Inventory inventory;
    protected final int slot;
    protected final int minX, minY, maxX, maxY;

    public GuiItemSlot(Inventory i, int s, int bx, int by, int ex, int ey)
    {
        this.inventory = i;
        this.slot = s;
        this.minX = bx;
        this.minY = by;
        this.maxX = ex;
        this.maxY = ey;

        /*if (this.inventory.items[this.slot] != null && this.inventory.items[this.slot].item != null && this.inventory.items[this.slot].stack_size != 0)
            this.guiItem = new GuiItem(this.inventory.items[this.slot].item);
        else
            this.guiItem = null;*/
    }

    /*public GuiItemSlot(Inventory i, int s)
    {
        this(i, s, 0, 0, 0, 0);
    }

    public GuiItemSlot()
    {
        this(null, 0);
    }*/

    public void render(Graphics g)
    {
        int slotWidth = this.maxX - this.minX, slotHeight = this.maxY - this.minY;
        g.drawImage(RenderManager.texture_item_slot, this.minX, this.minY, slotWidth, slotHeight,null);

        GuiItem guiItem;

        if (!ItemStack.isEmpty(this.inventory.items[this.slot]))
            guiItem = new GuiItem(this.inventory.items[this.slot].item);
        else
            guiItem = null;

        if (guiItem != null)
        {
            //g.setColor(Color.WHITE);
            //g.setFont(new Font("TimesRoman", Font.PLAIN, slotWidth * 4 / 18));
            guiItem.render(g, this.minX + slotWidth / 18, this.minY + slotHeight / 18, slotWidth * 16 / 18, slotHeight * 16 / 18, this.inventory.items[this.slot].stack_size);
            //g.drawString(String.valueOf(this.inventory.items[this.slot].stack_size), this.minX + slotWidth * 2 / 18, this.minY + slotHeight * 16 / 18);
        }
    }
}
