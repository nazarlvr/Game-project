package render.block;

import block.Block;
import block.Blocks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderBlock
{
    protected final Block block;
    protected BufferedImage texture;

    public RenderBlock(Block b)
    {
        this.block = b;
        this.texture = Blocks.textures_map.get(b.blockId);
    }

    public void render(Graphics g, int x, int y, int w, int h)
    {
        g.drawImage(this.texture, x, y, w, h, null);
    }
}
