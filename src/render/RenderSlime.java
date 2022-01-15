package render;

import entity.Entity;

public class RenderSlime extends RenderEntity
{
    public RenderSlime(Entity e)
    {
        super(e);
        this.width = 16;
        this.height = 16;
        this.texture = RenderEntity.loadTexture("slime/slime.png");
    }
}
