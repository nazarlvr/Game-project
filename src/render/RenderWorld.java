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
    //public int positionmX;
    //public int positionmY;
    public double playerX;
    public double playerY;
    public static final int screenX = 20, screenY = 10;
    public static final int minX = 0, maxX = Toolkit.getDefaultToolkit().getScreenSize().width, minY = 0, maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int width = maxX - minX, height = maxY - minY;
    public static final int dx = (maxX - minX) / screenX, dy = (maxY - minY) / screenY;
    public static final int guiSize = 4;


    double startX, startY, finalX, finalY;
    public RenderWorld(World w)
    {
        super();
        world = w;
    }
    public double blockcoordinatesX(double x)
    {
        return (x - minX) / dx + startX;
    }
    public double blockcoordinatesY(double y)
    {
        return (maxY - y) / dy + startY;
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
        setBackground(Color.CYAN);
        setForeground(Color.BLACK);

        startX = Math.min(Math.max(0, (playerX - screenX/2)), world.width - screenX);
        startY = Math.min(Math.max(0, playerY - screenY/2), world.height - screenY);
        finalX = startX + screenX;
        finalY = startY + screenY;

        g.clipRect(minX, minY, maxX, maxY);

        for (int x = (int) Math.floor(startX); x < Math.ceil(finalX) ; ++x)
        {
            for (int y = (int) Math.floor(startY); y < Math.ceil(finalY); ++y)
            {
                Block block = world.getBlock(x, y);

                if (block != null)
                {
                    RenderBlock renderBlock = RenderManager.getRender(block);
                    renderBlock.render(g, (int) (minX + (x - startX) * dx), (int) (maxY - ((y - startY) + 1) * dy), dx, dy);
                }
            }
        }

        ArrayList<Entity> entities = world.getEntities();

        for (Entity e : entities)
        {
            if (e != null && !(e instanceof EntityParticle) /*&& ((e.getPosX() > startX)&&(e.getPosX() < startX + screenX)&& (e.getPosY() > startY)&&(e.getPosY() < startY + screenY))*/)
            {
                RenderEntity renderEntity = RenderManager.getRender(e);
                renderEntity.render(g, minX + (int) (((e.getPosX() - startX) - renderEntity.getWidth() / 2) * dx), maxY - (int) (((e.getPosY() - startY) + renderEntity.getHeight()) * dy), dx, dy);
            }
        }

        for (Entity e : entities)
        {
            if (e != null && e instanceof EntityParticle)
            {
                RenderEntity renderEntity = RenderManager.getRender(e);
                renderEntity.render(g, minX + (int) (((e.getPosX() - startX) - renderEntity.getWidth() / 2) * dx), maxY - (int) (((e.getPosY() - startY) + renderEntity.getHeight()) * dy), dx, dy);
            }
        }

        g.setClip(null);

        if (this.game.currentGui != null)
        {
            this.game.currentGui.render(g);
        }

        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        g.drawString("Ticks: " + wt,40,40);
        g.drawString("Time: " + at, 60, 60);
        g.drawString("Ticks per second: " + tf,80,80);
        g.drawString("Shift: " + game.SHIFT_pressed,240,40);

        g.drawString("Mouse " + this.blockcoordinatesX(this.game.mouseX) + " " + this.blockcoordinatesY(this.game.mouseY),100,100);

        g.drawLine(minX, minY, minX, maxY);
        g.drawLine(minX, minY, maxX, minY);
        g.drawLine(minX, maxY, maxX, maxY);
        g.drawLine(maxX, minY, maxX, maxY);

        g.dispose();
        strategy.show();
    }
}
