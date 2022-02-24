package render.entity;

import entity.EntityItem;
import gui.GuiItem;
import item.Items;
import render.entity.RenderEntity;

import java.awt.*;

public class RenderItem extends RenderEntity
{
    protected final GuiItem guiItem;

    public RenderItem(EntityItem e)
    {
        super(e);
        this.width = e.getWidth();
        this.height = e.getHeight();
        this.guiItem = new GuiItem(e.itemStack.item);
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h)
    {
        this.guiItem.render(g, x, y, (int) (w * this.width), (int) (h * this.height));
    }
}
