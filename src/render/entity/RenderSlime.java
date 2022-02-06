package render.entity;

import entity.Entity;
import render.entity.RenderEntity;

public class RenderSlime extends RenderEntity
{
    public RenderSlime(Entity e)
    {
        super(e);
        this.width = e.getWidth();
        this.height = e.getHeight();
        this.texture = RenderEntity.loadTexture("slime/slime.png");
    }
}
