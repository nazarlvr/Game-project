package gui;

import entity.EntityPlayer;
import game.Game;
import render.RenderManager;
import render.RenderWorld;

public class GuiInventoryPlayer extends GuiInventory
{
    EntityPlayer player;

    public GuiInventoryPlayer(Game g, EntityPlayer p, int bx, int by, int ex, int ey)
    {
        super(g, bx, by, ex, ey);
        this.player = p;
        this.texture = RenderManager.texture_gui_chest;
    }

    public GuiInventoryPlayer(Game g, EntityPlayer p)
    {
        this(g, p, RenderWorld.minX + (RenderWorld.maxX - RenderWorld.minX - 198 * RenderWorld.guiSize) / 2, RenderWorld.minY + (RenderWorld.maxY - RenderWorld.minY - 180 * RenderWorld.guiSize) / 2, RenderWorld.maxX - (RenderWorld.maxX - RenderWorld.minX - 198 * RenderWorld.guiSize) / 2, RenderWorld.maxY - (RenderWorld.maxY - RenderWorld.minY - 180 * RenderWorld.guiSize) / 2);
    }

    @Override
    public void open()
    {
        super.open();

        int guiWidth = this.maxX - this.minX, guiHeight = this.maxY - this.minY;
        int slotMinX = this.minX + guiWidth / 11, slotMinY = this.minY + guiHeight / 10, slotMaxX = this.minX + guiWidth * 10 / 11, slotMaxY = this.minY + guiHeight * 9 / 10;
        int slotWidth = (slotMaxX - slotMinX)/9, slotHeight = (slotMaxY - slotMinY)/8;

        slots = new GuiItemSlot[this.player.inventory.size + this.player.inventoryCrafting.size];

        for (int x = 0; x < this.player.inventoryCrafting.size; ++x)
        {
            slots[x] = new GuiItemSlot(this.player.inventoryCrafting, x, slotMinX + x * slotWidth, slotMinY, slotMinX + x * slotWidth + slotWidth, slotMinY + slotHeight, false, true);
        }

        for (int x = 0; x < 9; ++x)
        {
            for (int y = 0; y < 4; ++y)
            {
                slots[this.player.inventoryCrafting.size + y * 9 + x] = new GuiItemSlot(this.player.inventory, y * 9 + x, slotMinX + x * slotWidth, slotMinY + (y + 4) * slotHeight, slotMinX + x * slotWidth + slotWidth, slotMinY + (y + 4) * slotHeight + slotHeight);
            }
        }
    }

    @Override
    public void processKeyPresses() {
        if (this.game.E_clicked)
        {
            this.close();
            return;
        }

        super.processKeyPresses();
    }
}
