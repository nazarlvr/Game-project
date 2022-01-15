package render;

import entity.Entity;

public class RenderSlime extends RenderEntity
{
    public RenderSlime(Entity e)
    {
        super(e);
        this.width = 1;
        this.height = 1;
        this.texture = RenderEntity.loadTexture("slime/slime.png");
    }
}
