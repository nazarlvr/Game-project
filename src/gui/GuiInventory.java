package gui;

import game.Game;
import inventory.Inventory;
import item.Item;
import item.ItemStack;
import render.RenderWorld;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuiInventory extends Gui
{
    protected BufferedImage texture;
    public GuiItemSlot slots[];
    public ItemStack stack;

    public GuiInventory(Game g, int bx, int by, int ex, int ey)
    {
        super(g, bx, by, ex, ey);
    }

    public GuiInventory(Game g)
    {
        this(g, RenderWorld.minX, RenderWorld.minY, RenderWorld.maxX, RenderWorld.maxY);
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(this.texture, this.minX, this.minY, this.maxX - this.minX, this.maxY - this.minY, null);

        for (GuiItemSlot s : this.slots)
        {
            s.render(g);
        }

        if (!ItemStack.isEmpty(this.stack))
        {
            GuiItem guiItem = new GuiItem(this.stack.item);
            guiItem.render(g, this.game.mouseX - RenderWorld.guiSize * 8, this.game.mouseY - RenderWorld.guiSize * 8,  RenderWorld.guiSize * 16,  RenderWorld.guiSize * 16);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, RenderWorld.guiSize * 4));
            g.drawString(String.valueOf(this.stack.stack_size), this.game.mouseX - RenderWorld.guiSize * 7, this.game.mouseY + RenderWorld.guiSize * 7);
        }
    }

    @Override
    public void processKeyPresses()
    {
        if (this.game.ESC_clicked)
        {
            this.close();
            return;
        }

        if (this.game.mouse_left_clicked)
        {
            for (GuiItemSlot s : this.slots)
            {
                if (s.minX < this.game.mouseX && this.game.mouseX < s.maxX && s.minY < this.game.mouseY && this.game.mouseY < s.maxY)
                {
                    if (ItemStack.isEmpty(this.stack) && ItemStack.isEmpty(s.inventory.items[s.slot]))
                    {
                        this.stack = null;
                        s.inventory.items[s.slot] = null;
                    }
                    else if (ItemStack.isEmpty(this.stack))
                    {
                        if (s.output)
                        {
                            if (s.input)
                            {
                                if (this.game.SHIFT_pressed)
                                    this.stack = s.inventory.items[s.slot].split();
                                else
                                {
                                    this.stack = s.inventory.items[s.slot];
                                    s.inventory.items[s.slot] = null;
                                }
                            }
                            else
                            {
                                if (this.game.SHIFT_pressed)
                                {
                                    this.stack = s.inventory.items[s.slot];
                                    s.inventory.items[s.slot] = null;
                                }
                                else
                                {
                                    this.stack = new ItemStack(s.inventory.items[s.slot].item, 1);
                                    --s.inventory.items[s.slot].stack_size;
                                }
                            }
                        }
                    }
                    else if (ItemStack.isEmpty(s.inventory.items[s.slot]))
                    {
                        if (s.input)
                        {
                            if (s.output)
                            {
                                if (this.game.SHIFT_pressed)
                                    s.inventory.items[s.slot] = this.stack.split();
                                else
                                {
                                    s.inventory.items[s.slot] = this.stack;
                                    this.stack = null;
                                }
                            }
                            else
                            {
                                if (this.game.SHIFT_pressed)
                                {
                                    s.inventory.items[s.slot] = this.stack;
                                    this.stack = null;
                                }
                                else
                                {
                                    s.inventory.items[s.slot] = new ItemStack(this.stack.item, 1);
                                    --this.stack.stack_size;
                                }
                            }
                        }
                    }
                    else if (s.inventory.items[s.slot].item.equals(this.stack.item))
                    {
                        if (s.input)
                        {
                            if (s.output)
                                this.stack = s.inventory.items[s.slot].add(this.stack);
                            else
                            {
                                ++s.inventory.items[s.slot].stack_size;
                                --this.stack.stack_size;
                            }
                        }
                    }
                    else
                    {
                        if (s.input && s.output)
                        {
                            ItemStack st = s.inventory.items[s.slot];
                            s.inventory.items[s.slot] = stack;
                            stack = st;
                        }
                    }
                }
            }
        }
    }
}
