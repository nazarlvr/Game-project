package gui;

import block.Block;
import block.BlockChest;
import block.Blocks;
import entity.EntityPlayer;
import game.Game;
import item.ItemStack;
import render.RenderManager;
import render.RenderWorld;

import java.awt.*;

public class GuiPlayer extends Gui
{
    public EntityPlayer player;
    public int ActiveSlotNumber = 0;
    public GuiPlayer(Game g, EntityPlayer p, int bx, int by, int ex, int ey)
    {
        super(g, bx, by, ex, ey);
        this.player = p;
    }

    @Override
    public void render(Graphics g)
    {
        if (player == null)
            return;

        for (int i = 0; i < player.maxHP; ++i)
        {
            g.drawImage(RenderManager.texture_icon_heart_empty, this.minX + i * RenderWorld.guiSize * 9, this.minY, RenderWorld.guiSize * 9, RenderWorld.guiSize * 9, null);
        }

        for (int i = 0; i < player.currentHP; ++i)
        {
            g.drawImage(RenderManager.texture_icon_heart_full, this.minX + i * RenderWorld.guiSize * 9, this.minY, RenderWorld.guiSize * 9, RenderWorld.guiSize * 9, null);
        }
        int curX = (this.maxX - this.minX - 182 * RenderWorld.guiSize / 4 * 3) / 2;
        int curY = this.maxY - 22 * RenderWorld.guiSize / 4 * 3 - 10;
        g.drawImage(RenderManager.texture_gui_hotbar, curX, curY, 182 * RenderWorld.guiSize / 4 * 3, 22 * RenderWorld.guiSize / 4 * 3, null);
        g.drawImage(RenderManager.texture_active_slot, curX + RenderWorld.guiSize / 4 * 3 * (ActiveSlotNumber * 20 - 1), curY - RenderWorld.guiSize / 4 * 3, 24 * RenderWorld.guiSize / 4 * 3, 24 * RenderWorld.guiSize / 4 * 3, null);

        for (int i = 0; i < 9; i++) {
            if (!ItemStack.isEmpty(this.player.inventory.items[i])) {
                GuiItem gi = new GuiItem(this.player.inventory.items[i].item);
                int slotSize = 180 * RenderWorld.guiSize / 4 * 3 / 9;
                int itemSize = 16 * RenderWorld.guiSize / 4 * 3;
                gi.render(g, curX + RenderWorld.guiSize / 4 * 3  + slotSize * i + (slotSize - itemSize) / 2, curY + RenderWorld.guiSize / 4 * 3 + (slotSize - itemSize) / 2, itemSize, itemSize, this.player.inventory.items[i].stack_size);
            }
        }

        super.render(g);
    }

    @Override
    public void processKeyPresses()
    {
        if (this.game.ESC_clicked)
        {
            this.game.gameRunning = false;
            return;
        }

        if (this.player == null)
            this.player = this.game.world.findPlayer();

        if (this.player != null)
        {
            if (this.game.W_pressed)
            {
                if (!this.player.isAirborne)
                {
                    this.player.launchY(0.42);
                }
            }

            if (this.game.A_pressed)
            {
                this.player.launchX(-0.05);
            }

            if (this.game.D_pressed)
            {
                this.player.launchX(0.05);
            }

            if (this.game.mouseWheelRotaion != 0)
            {
                this.ActiveSlotNumber += this.game.mouseWheelRotaion;
                this.ActiveSlotNumber %= 9;

                if (this.ActiveSlotNumber < 0)
                    this.ActiveSlotNumber += 9;
            }

            if (this.game.E_clicked)
            {
                GuiInventoryPlayer guiInventoryPlayer = new GuiInventoryPlayer(this.game, this.player);
                guiInventoryPlayer.open();
                /*int guiMinY = this.minY + (this.maxY - this.minY) / 8, guiMaxY = this.maxY - (this.maxY - this.minY) / 8, guiHeight = guiMaxY - guiMinY;
                int guiWidth = guiHeight * 11 / 6, guiMinX = this.minX + (this.maxX - this.minX - guiWidth) / 2, guiMaxX = this.maxX - (this.maxX - this.minX - guiWidth) / 2;
                GuiInventory guiInventory = new GuiInventory(this.game, this.player.inventory, guiMinX, guiMinY, guiMaxX, guiMaxY);
                guiInventory.open();
                return;*/
            }

            if (this.game.mouse_left_clicked)
            {
                int x = (int) this.game.renderWorld.blockcoordinatesX(this.game.mouseX);
                int y = (int) this.game.renderWorld.blockcoordinatesY(this.game.mouseY);
                if ((this.player.getPosX() - x)*(this.player.getPosX() - x) + (this.player.getPosY() - y)*(this.player.getPosY() - y) < 5*5) {
                    this.player.world.hit(this.game.renderWorld.blockcoordinatesX(this.game.mouseX), this.game.renderWorld.blockcoordinatesY(this.game.mouseY), 1);
                    if (this.player.world.getBlock(x, y) != null)
                        this.player.world.breakBlock(x, y);
                }
            }

            if (this.game.mouse_right_clicked)
            {

                int x = (int)this.game.renderWorld.blockcoordinatesX(this.game.mouseX);
                int y = (int)this.game.renderWorld.blockcoordinatesY(this.game.mouseY);

                if ((this.player.getPosX() - x)*(this.player.getPosX() - x) + (this.player.getPosY() - y)*(this.player.getPosY() - y) < 5*5)
                {

                    if (this.player.world.getBlock(x, y) != null)
                        this.player.world.interactBlock(this.player, x, y);
                    else {
                        Block block;

                        if (this.player.inventory.items[this.ActiveSlotNumber].item != null && this.player.inventory.items[this.ActiveSlotNumber].item.itemId < 0 && this.player.inventory.items[this.ActiveSlotNumber].stack_size > 0) {
                            block = Blocks.getBlockFromItem(this.player.inventory.items[this.ActiveSlotNumber].item).copy();
                            block.initAABB(x, y);
                            if (this.player.world.isEmpty(block.aabb)) {
                                this.player.world.setBlock(x, y, block);
                                this.player.inventory.items[this.ActiveSlotNumber].stack_size--;
                            }
                        }
                    }
                }
            }

            if (this.player.isDead)
                this.player = null;
        }
        else
        {
            this.close();
            return;
        }




    }
}
