package render;

import block.Block;
import block.Blocks;
import entity.Entity;
import entity.EntitySlime;

public class RenderManager
{
    public static RenderBlock getRender(Block b)
    {
        if (b == null)
            return null;

        if (b.blockId == Blocks.grass.blockId)
            return new RenderGrass(b);
        else
            return new RenderBlock(b);
    }

    public static RenderEntity getRender(Entity e)
    {
        if (e == null)
            return null;

        if (e instanceof EntitySlime)
            return new RenderSlime(e);
        else
            return new RenderEntity(e);
    }
}
