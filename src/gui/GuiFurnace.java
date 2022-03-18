package gui;

import block.BlockFurnace;
import entity.EntityPlayer;
import game.Game;
import inventory.Inventory;
import render.RenderManager;
import render.RenderWorld;

import java.awt.*;

public class GuiFurnace extends GuiInventory
{
    Inventory inventoryPlayer;
    Inventory inventoryFurnace;
    BlockFurnace block;

    public GuiFurnace(Game g, EntityPlayer p, BlockFurnace f, int bx, int by, int ex, int ey)
    {
        super(g, bx, by, ex, ey);
        this.texture = RenderManager.texture_gui_chest;
        this.inventoryPlayer = p.inventory;
        this.inventoryFurnace = f.inventory;
        this.block = f;
    }

    public GuiFurnace(Game g, EntityPlayer p, BlockFurnace c)
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

        slots = new GuiItemSlot[this.inventoryFurnace.size + this.inventoryPlayer.size];

        slots[0] = new GuiItemSlot(this.inventoryFurnace, 0, slotMinX + 0 * slotWidth, slotMinY + 0 * slotHeight, slotMinX + 0 * slotWidth + slotWidth, slotMinY + 0 * slotHeight + slotHeight);
        slots[1] = new GuiItemSlot(this.inventoryFurnace, 1, slotMinX + 2 * slotWidth, slotMinY + 1 * slotHeight, slotMinX + 2 * slotWidth + slotWidth, slotMinY + 1 * slotHeight + slotHeight);
        slots[2] = new GuiItemSlot(this.inventoryFurnace, 2, slotMinX + 0 * slotWidth, slotMinY + 2 * slotHeight, slotMinX + 0 * slotWidth + slotWidth, slotMinY + 2 * slotHeight + slotHeight);



        /*for (int x = 0; x < 9; ++x)
        {
            for (int y = 0; y < 3; ++y)
            {
                slots[y * 9 + x] = new GuiItemSlot(this.inventoryFurnace, y * 9 + x, slotMinX + x * slotWidth, slotMinY + y * slotHeight, slotMinX + x * slotWidth + slotWidth, slotMinY + y * slotHeight + slotHeight);
            }
        }*/

        for (int x = 0; x < 9; ++x)
        {
            for (int y = 0; y < 4; ++y)
            {
                slots[3 + y * 9 + x] = new GuiItemSlot(this.inventoryPlayer, y * 9 + x, slotMinX + x * slotWidth, slotMinY + (y + 4) * slotHeight, slotMinX + x * slotWidth + slotWidth, slotMinY + (y + 4) * slotHeight + slotHeight);
            }
        }
    }

    @Override
    public void render(Graphics g)
    {
        super.render(g);
        g.drawString(String.valueOf(this.block.blockData % 1000), this.maxX - 300, this.minY + 100);
        g.drawString(String.valueOf(this.block.blockData / 1000), this.maxX - 300, this.minY + 300);
    }
}
