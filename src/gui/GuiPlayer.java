package gui;

import block.BlockChest;
import entity.EntityPlayer;
import game.Game;
import render.RenderManager;
import render.RenderWorld;

import java.awt.*;

public class GuiPlayer extends Gui
{
    public EntityPlayer player;

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
                this.player.world.hit(this.game.renderWorld.blockcoordinatesX(this.game.mouseX), this.game.renderWorld.blockcoordinatesY(this.game.mouseY), 1);

                int x = (int)this.game.renderWorld.blockcoordinatesX(this.game.mouseX);
                int y = (int)this.game.renderWorld.blockcoordinatesY(this.game.mouseY);

                if (this.player.world.getBlock(x, y) != null)
                    this.player.world.breakBlock(x, y);
            }

            if (this.game.mouse_right_clicked)
            {
                int x = (int)this.game.renderWorld.blockcoordinatesX(this.game.mouseX);
                int y = (int)this.game.renderWorld.blockcoordinatesY(this.game.mouseY);

                if (this.player.world.getBlock(x, y) != null)
                    this.player.world.interactBlock(this.player, x, y);
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
