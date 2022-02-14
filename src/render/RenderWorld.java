package render;

import block.Block;
import entity.Entity;
import entity.particle.EntityParticle;
import entity.EntityPlayer;
import game.Game;
import render.block.RenderBlock;
import render.entity.RenderEntity;
import world.World;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class RenderWorld extends Canvas
{
    public BufferStrategy strategy;
    private World world;
    public Game game;
    public int positionmX;
    public int positionmY;
    public double playerX;
    public double playerY;
    final int xmin = 0, xmax = Toolkit.getDefaultToolkit().getScreenSize().width, ymin = 150, ymax = Toolkit.getDefaultToolkit().getScreenSize().height;
    final int screenX = 20, screenY = 10;
    final int dx = (xmax - xmin) / screenX, dy = (ymax - ymin) / screenY;
    double startX, startY, finalX, finalY;
    public RenderWorld(World w)
    {
        super();
        world = w;
    }
    public double blockcoordinatesX(double x)
    {
        return (x - xmin) / dx + startX;
    }
    public double blockcoordinatesY(double y)
    {
        return (ymax - y) / dy + startY;
    }
    public void BS()
    {
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    public void receivePlayerCoor()
    {
        for (Entity ent : this.world.getEntities()) {
            if(ent instanceof EntityPlayer)
            {
                playerX = ent.getPosX();
                playerY = ent.getPosY();
                break;
            }
        }
    }

    public void render()
    {
        receivePlayerCoor();
        long wt = world.getTime();
        long at = (System.currentTimeMillis() - world.timeStart) / 1000;
        double tf =  1.0 * wt / at;

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.clearRect(0,0, getWidth(), getHeight());


        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.drawString("Ticks: " + wt,40,40);
        g.drawString("Time: " + at, 60, 60);
        g.drawString("Ticks per second: " + tf,80,80);


        setBackground(Color.CYAN);
        setForeground(Color.RED);

        //g.drawImage(RenderEntity.loadTexture("player/Player.jpg"), 0, 0, 50, 50, null);

        g.drawString("Mouse " + this.blockcoordinatesX(positionmX) + " " + this.blockcoordinatesY(positionmY),100,100);
        startX = Math.min(Math.max(0, (playerX - screenX/2)), world.width - screenX);
        startY = Math.min(Math.max(0, playerY - screenY/2), world.height - screenY);
        finalX = startX + screenX;
        finalY = startY + screenY;

        g.clipRect(xmin, ymin, xmax, ymax);
        //g.clipRect(0, 0, world.width, world.height);
        for (int x = (int) Math.floor(startX); x < Math.ceil(finalX) ; ++x)
        {
            for (int y = (int) Math.floor(startY); y < Math.ceil(finalY); ++y)
            {
                Block block = world.getBlock(x, y);

                if (block != null)
                {
                    RenderBlock renderBlock = RenderManager.getRender(block);
                    renderBlock.render(g, (int) (xmin + (x - startX) * dx), (int) (ymax - ((y - startY) + 1) * dy), dx, dy);
                }
            }
        }

        ArrayList<Entity> entities = world.getEntities();

        for (Entity e : entities)
        {
            if (e != null && !(e instanceof EntityParticle) /*&& ((e.getPosX() > startX)&&(e.getPosX() < startX + screenX)&& (e.getPosY() > startY)&&(e.getPosY() < startY + screenY))*/)
            {
                RenderEntity renderEntity = RenderManager.getRender(e);
                renderEntity.render(g, xmin + (int) (((e.getPosX() - startX) - renderEntity.getWidth() / 2) * dx), ymax - (int) (((e.getPosY() - startY) + renderEntity.getHeight()) * dy), dx, dy);
            }
        }

        for (Entity e : entities)
        {
            if (e != null && e instanceof EntityParticle)
            {
                RenderEntity renderEntity = RenderManager.getRender(e);
                renderEntity.render(g, xmin + (int) (((e.getPosX() - startX) - renderEntity.getWidth() / 2) * dx), ymax - (int) (((e.getPosY() - startY) + renderEntity.getHeight()) * dy), dx, dy);
            }
        }

        g.setClip(null);


        g.drawLine(xmin, ymin, xmin, ymax);
        g.drawLine(xmin, ymin, xmax, ymin);
        g.drawLine(xmin, ymax, xmax, ymax);
        g.drawLine(xmax, ymin, xmax, ymax);

        g.dispose();
        strategy.show();
    }
}
