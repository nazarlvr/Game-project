package render;

import block.Block;
import block.Blocks;
import entity.Entity;
import main.Main;
import world.World;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

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
        setForeground(Color.RED);

        g.drawImage(RenderEntity.loadTexture("player/Player.jpg"), 0, 0, 50, 50, null);
        final int xmin = 200, xmax = 1400, ymin = 200, ymax = 700, dx = (xmax - xmin) / world.width, dy = (ymax - ymin) / world.height;

        for (int x = 0; x < world.width; ++x)
        {
            for (int y = 0; y < world.height; ++y)
            {
                Block block = world.getBlock(x, y);

                if (block != null)
                {
                    RenderBlock renderBlock = RenderManager.getRender(block);
                    renderBlock.render(g, xmin + x * dx, ymax - (y + 1) * dy, dx, dy);
                }
            }
        }

        ArrayList<Entity> entities = world.getEntities();

        for (Entity e : entities)
        {
            if (e != null)
            {
                RenderEntity renderEntity = RenderManager.getRender(e);
                renderEntity.render(g, (int) (xmin + e.getPosX() * dx), (int) (ymax - (e.getPosY() + 1) * dy), dx, dy);
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
