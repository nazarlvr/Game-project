package render;

import block.Block;
import block.BlockGrass;
import block.BlockSlab;
import block.Blocks;
import entity.Entity;
import entity.EntityPlayer;
import entity.EntitySlime;
import render.block.RenderBlock;
import render.block.RenderGrass;
import render.block.RenderSlab;
import render.entity.RenderEntity;
import render.entity.RenderPlayer;
import render.entity.RenderSlime;

public class RenderManager
{
    public static RenderBlock getRender(Block b)
    {
        if (b == null)
            return null;

        if (b instanceof BlockGrass)
            return new RenderGrass(b);
        else if (b instanceof BlockSlab)
            return new RenderSlab(b);
        else
            return new RenderBlock(b);
    }

    public static RenderEntity getRender(Entity e)
    {
        if (e == null)
            return null;

        if (e instanceof EntitySlime)
            return new RenderSlime(e);
        else if (e instanceof EntityPlayer)
            return new RenderPlayer(e);
        else
            return new RenderEntity(e);
    }
}
