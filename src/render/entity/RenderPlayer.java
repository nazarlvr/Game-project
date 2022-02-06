package render.entity;

import entity.Entity;

public class RenderPlayer extends RenderEntity
{
    public RenderPlayer(Entity e)
    {
        super(e);
        this.width = e.getWidth();
        this.height = e.getHeight();
        this.texture = RenderEntity.loadTexture("player/player.png");
    }
}
