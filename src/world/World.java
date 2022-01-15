package world;

import block.Block;
import block.Blocks;
import entity.Entity;
import entity.EntitySlime;

import java.util.*;

public class World
{
    private String name;
    public static final int width = 20;
    public static final int height = 10;
    private Block[][] blocks;
    private long time;
    private ArrayList<Entity> entities;
    public long timeStart;

    public World(String n)
    {
        this.name = n;
        this.time = 0;
        this.blocks = new Block[this.width][this.height];
        this.entities = new ArrayList<Entity>();

        this.blocks[0][0] = Blocks.dirt;
        this.blocks[0][1] = Blocks.coal;
        this.blocks[1][0] = Blocks.stone;
        this.blocks[1][1] = Blocks.dirt;
        this.blocks[1][2] = Blocks.grass;
        this.blocks[2][1] = Blocks.dirt;
        this.blocks[3][1] = Blocks.grass;
        this.blocks[4][1] = Blocks.stone;
        this.blocks[5][1] = Blocks.stone;

        for (int i = 0; i < this.width; ++i)
        {
            this.blocks[i][0] = Blocks.stone;
        }
        this.spawnEntity(new EntitySlime(2.5, 7));
    }

    public void tick()
    {
        for (int x = 0; x < width; ++x)
            for (int y = 0; y < height; ++y)
            {
                Block b = blocks[x][y];

                if (b != null)
                    b.tick();
            }

        for (Entity e : entities)
        {
            if (e != null)
                e.tick();
        }

        ++time;
    }

    public void spawnEntity(Entity e)
    {
        if (e != null)
        {
            e.world = this;
            entities.add(e);
        }
    }

    public boolean isInt(double x)
    {
        int z = (int) x;

        return (x-z) <= 0.05;
    }

    public boolean isAirborne(Entity entity)
    {
        double x = entity.getPosX(), y = entity.getPosY();

        if ((int) y <= 0)
            return false;

        if (isInt(y))
            return blocks[(int) x][(int) y - 1] == null;
        else
            return true;

        //return blocks[(int)][(int)-1] == null ;
    }

    public ArrayList<Entity> getEntities()
    {
        return (ArrayList<Entity>) entities.clone();
    }

    public Block getBlock(int x, int y)
    {
        return blocks[x][y];
    }

    public long getTime()
    {
        return time;
    }
}
