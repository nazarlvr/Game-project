package render.entity;

import entity.EntityItem;
import item.Items;
import render.entity.RenderEntity;

import java.awt.*;

public class RenderItem extends RenderEntity
{
    public RenderItem(EntityItem e)
    {
        super(e);
        this.width = e.getWidth();
        this.height = e.getHeight();
        this.texture = Items.textures_map.get(e.itemStack.item.itemId);
    }
}
