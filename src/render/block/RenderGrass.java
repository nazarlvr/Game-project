package render.block;

import block.Block;
import block.Blocks;
import render.block.RenderBlock;

import java.awt.*;

public class RenderGrass extends RenderBlock
{
    public RenderGrass(Block b)
    {
        super(b);

        if (b.blockData == 0)
            this.texture = Blocks.loadTexture("grass_1.png");
        else
            this.texture = Blocks.loadTexture("grass_0.png");
    }
}
