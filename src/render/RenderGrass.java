package render;

import block.Block;
import block.Blocks;

import java.awt.*;

public class RenderGrass extends RenderBlock
{
    public RenderGrass(Block b)
    {
        super(b);

        if (b.blockData == 0)
            this.texture = Blocks.loadTexture("textures/grass_1.png");
        else
            this.texture = Blocks.loadTexture("textures/grass_0.png");
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        super.render(g, x, y, w, h);
    }
}
