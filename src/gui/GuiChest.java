package gui;

import block.BlockChest;
import entity.EntityPlayer;
import game.Game;
import inventory.Inventory;
import render.RenderManager;
import render.RenderWorld;

import java.awt.*;

public class GuiChest extends GuiInventory
{
    Inventory inventoryPlayer;
    Inventory inventoryChest;

    public GuiChest(Game g, EntityPlayer p, BlockChest c, int bx, int by, int ex, int ey)
    {
        super(g, bx, by, ex, ey);
        this.texture = RenderManager.texture_gui_chest;
        this.inventoryPlayer = p.inventory;
        this.inventoryChest = c.inventory;
    }

    public GuiChest(Game g, EntityPlayer p, BlockChest c)
    {
        this(g, p, c, RenderWorld.minX + (RenderWorld.maxX - RenderWorld.minX - 198 * RenderWorld.guiSize) / 2, RenderWorld.minY + (RenderWorld.maxY - RenderWorld.minY - 180 * RenderWorld.guiSize) / 2, RenderWorld.maxX - (RenderWorld.maxX - RenderWorld.minX - 198 * RenderWorld.guiSize) / 2, RenderWorld.maxY - (RenderWorld.maxY - RenderWorld.minY - 180 * RenderWorld.guiSize) / 2);
    }

    @Override
    public void open()
    {
        super.open();

        int guiWidth = this.maxX - this.minX, guiHeight = this.maxY - this.minY;
        int slotMinX = this.minX + guiWidth / 11, slotMinY = this.minY + guiHeight / 10, slotMaxX = this.minX + guiWidth * 10 / 11, slotMaxY = this.minY + guiHeight * 9 / 10;
        int slotWidth = (slotMaxX - slotMinX)/9, slotHeight = (slotMaxY - slotMinY)/8;

        slots = new GuiItemSlot[this.inventoryChest.size + this.inventoryPlayer.size];

        for (int x = 0; x < 9; ++x)
        {
            for (int y = 0; y < 3; ++y)
            {
                slots[y * 9 + x] = new GuiItemSlot(this.inventoryChest, y * 9 + x, slotMinX + x * slotWidth, slotMinY + y * slotHeight, slotMinX + x * slotWidth + slotWidth, slotMinY + y * slotHeight + slotHeight);
            }
        }

        for (int x = 0; x < 9; ++x)
        {
            for (int y = 0; y < 4; ++y)
            {
                slots[(y + 3) * 9 + x] = new GuiItemSlot(this.inventoryPlayer, y * 9 + x, slotMinX + x * slotWidth, slotMinY + (y + 4) * slotHeight, slotMinX + x * slotWidth + slotWidth, slotMinY + (y + 4) * slotHeight + slotHeight);
            }
        }
    }
}
