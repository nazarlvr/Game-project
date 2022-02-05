package world;

import block.Block;
import block.Blocks;
import entity.Entity;
import entity.EntitySlime;
import util.AABB;
import util.Vec2;

import java.util.*;
import java.util.concurrent.TimeUnit;

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


        this.spawnEntity(new EntitySlime(18.5, 2));
        this.generateTerrain();
    }

    public void generateTerrain()
    {
        this.setBlock(0, 0, Blocks.dirt);
        this.setBlock(0, 1, Blocks.coal);
        this.setBlock(1, 0, Blocks.stone);
        this.setBlock(1, 1, Blocks.dirt);
        this.setBlock(1, 2, Blocks.grass);
        this.setBlock(2, 1, Blocks.dirt);
        this.setBlock(3, 1, Blocks.grass);
        this.setBlock(3, 5, Blocks.dirt);
        this.setBlock(4, 1, Blocks.stone);
        this.setBlock(5, 1, Blocks.stone);
        this.setBlock(5, 5, Blocks.dirt);
        this.setBlock(6, 4, Blocks.dirt);
        this.setBlock(7, 2, Blocks.dirt);
        this.setBlock(8, 3, Blocks.dirt);
        this.setBlock(9, 3, Blocks.dirt);
        this.setBlock(10, 3, Blocks.dirt);
        this.setBlock(9, 5, Blocks.dirt);
        this.setBlock(18, 1, Blocks.bedrock);

        for (int i = 0; i < this.width; ++i)
        {
            this.setBlock(i, 0, i % 2 == 0 ? Blocks.bedrock : Blocks.coal);
            this.setBlock(i, this.height - 1, i % 2 == this.height  % 2 ? Blocks.bedrock : Blocks.coal);
        }

        for (int i = 0; i < this.height; ++i)
        {
            this.setBlock(0, i, i % 2 == 0 ? Blocks.bedrock : Blocks.coal);
            this.setBlock(this.width - 1, i, i % 2 == this.width  % 2 ? Blocks.bedrock : Blocks.coal);
        }

        System.out.println("BD: " + this.getBlockData(3,1));
        this.setBlockData(3, 1, 1000);
        System.out.println("BD: " + this.getBlockData(3,1));
    }

    public void tick()
    {
        for (int x = 0; x < width; ++x)
            for (int y = 0; y < height; ++y)
            {
                Block b = blocks[x][y];

                if (b != null)
                {
                    b.tick();
                }

            }

        for (Entity e : entities)
            if (e != null)
                e.tick();


        ++time;
    }

    public void spawnEntity(Entity e)
    {
        if (e != null)
        {
            e.world = this;
            e.init();
            entities.add(e);
        }
    }

    public boolean isInt(double x)
    {
        int z = (int) x;

        return (x-z) <= 0.05;
    }

    //Entity.isAirborne
    /*public boolean isAirborne(Entity entity)
    {
        double x = entity.getPosX(), y = entity.getPosY();

        if ((int) y <= 0)
            return false;

        if (isInt(y))
            return blocks[(int) x][(int) y - 1] == null;
        else
            return true;

        //return blocks[(int)][(int)-1] == null ;
    }*/

    public ArrayList<Entity> getEntities()
    {
        return (ArrayList<Entity>) entities.clone();
    }

    public Block getBlock(int x, int y)
    {
        return blocks[x][y];
    }

    public void setBlock(int x, int y, Block b)
    {
        blocks[x][y] = b.copy();
        blocks[x][y].initAABB(x, y);
    }

    public int getBlockData(int x, int y)
    {
        return blocks[x][y].blockData;
    }

    public void setBlockData(int x, int y, int d)
    {
        blocks[x][y].blockData = d;
    }

    public long getTime()
    {
        return time;
    }
}