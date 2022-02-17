package render.block;

import block.Block;
import block.Blocks;

import java.awt.*;

public class RenderSlab extends RenderBlock
{
    public RenderSlab(Block b)
    {
        super(b);
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h)
    {
        g.clipRect(x, y + h / 2, w, (h + 1) / 2);
        g.drawImage(this.texture, x, y, w, h, null);
        g.setClip(null);
    }
}
