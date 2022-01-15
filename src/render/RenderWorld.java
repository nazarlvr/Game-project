package render;

import block.Block;
import block.Blocks;
import main.Main;
import world.World;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class RenderWorld extends Canvas
{
    public BufferStrategy strategy;
    private World world;

    public RenderWorld(World w)
    {
        super();
        world = w;
    }

    public void BS()
    {
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    public void render()
    {
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.clearRect(0,0, getWidth(), getHeight());

        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.drawString(Long.toString(world.getTime()),40,40);

        setBackground(Color.WHITE);
        g.fillRect(130, 30,100, 80);
        g.drawOval(30,130,50, 60);
        setForeground(Color.RED);
        g.fillOval(130,130,50, 60);
        g.drawArc(30, 200, 40,50,90,60);
        g.fillArc(30, 130, 40,50,180,40);

        final int xmin = 400, xmax = 800, ymin = 400, ymax = 600, dx = (xmax - xmin) / world.width, dy = (ymax - ymin) / world.height;

        for (int x = 0; x < world.width; ++x)
            for (int y = 0; y < world.height; ++y)
            {
                Block block = world.getBlock(x,y);

                if (block != null)
                {
                    RenderBlock renderBlock = Blocks.getRender(block);
                    renderBlock.render(g, xmin + x * dx, ymax - (y+1) * dy, dx, dy);
                }
            }

        g.drawLine(xmin, ymin, xmin, ymax);
        g.drawLine(xmin, ymin, xmax, ymin);
        g.drawLine(xmin, ymax, xmax, ymax);
        g.drawLine(xmax, ymin, xmax, ymax);

        g.dispose();
        strategy.show();
    }
}
