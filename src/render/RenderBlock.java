package render;

import block.Block;
import block.Blocks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderBlock {
    private final Block block;
    private final BufferedImage texture;

    public RenderBlock(Block b) {
        block = b;
        texture = Blocks.dirt_texture;
    }

    public void render(Graphics g, int x, int y, int w, int h)
    {
        g.drawImage(texture, x, y, w, h, null);
    }
}
