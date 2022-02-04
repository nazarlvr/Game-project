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


        this.spawnEntity(new EntitySlime(4.5, 2));
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
            this.setBlock(i, 0, Blocks.bedrock);
            this.setBlock(i, this.height - 1, Blocks.bedrock);
        }

        for (int i = 0; i < this.height; ++i)
        {
            this.setBlock(0, i, Blocks.bedrock);
            this.setBlock(this.width - 1, i, Blocks.bedrock);
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
        {
            if (e != null)
            {
                boolean collided = true;

                while (collided)
                {
                    e.aabb.posX = e.getPosX() - e.getWidth() / 2;
                    e.aabb.posY = e.getPosY();
                    e.aabb.width = e.getWidth();
                    e.aabb.height = e.getHeight();
                    e.isAirborne = true;
                    collided = false;
                    AABB vbb = e.aabb.copy();
                    vbb.expand(e.getVelX(), e.getVelY());
                    Vec2 vd = new Vec2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                    AABB cbb = new AABB();

                    System.out.println("Entity pos: " + e.getPosX() + " " + e.getPosY() + " vel: " + e.getVelX() + " " + e.getVelY());
                    System.out.println("Entity AABB: " + e.aabb);
                    System.out.println("Velocity AABB: " + vbb);

                    for (int x = 0; x < width; ++x)
                        for (int y = 0; y < height; ++y)
                        {
                            Block b = blocks[x][y];

                            if (b != null && b.isCollideable())
                            {
                                if (b.getAABB().intersects(vbb))
                                {
                                    AABB block_bb = b.getAABB();
                                    Vec2 vb = b.getAABB().vectorDistance(e.aabb, e.getVelVec2());
                                    System.out.println("Velocity AABB " + " intersects " + b.getAABB());
                                    System.out.println(vb);
                                    //b.getAABB().printIntersectionPoints(e.aabb, e.getVelVec2());

                                    /*System.out.println("p00: (" + e.aabb.point00().coordX + " " + e.aabb.point00().coordY + ") (" + e.aabb.point00().add(e.getVelVec2()).coordX + " " + e.aabb.point00().add(e.getVelVec2()).coordY + "):");
                                    Vec2 i00 = Vec2.intersection_lines(block_bb.point01(), block_bb.point11(), e.aabb.point00(), e.aabb.point00().add(e.getVelVec2()));
                                    if (i00 != null)
                                        System.out.println(i00.coordX + " " + i00.coordY);
                                    else
                                        System.out.println("No intersection");
                                    Vec2 i10 = Vec2.intersection(block_bb.point01(), block_bb.point11(), e.aabb.point10(), e.aabb.point10().add(e.getVelVec2()));
                                    Vec2 i01 = Vec2.intersection(block_bb.point01(), block_bb.point11(), e.aabb.point01(), e.aabb.point01().add(e.getVelVec2()));
                                    Vec2 i11 = Vec2.intersection(block_bb.point01(), block_bb.point11(), e.aabb.point11(), e.aabb.point11().add(e.getVelVec2()));

                                    System.out.println("Intersections with (" + block_bb.point01().coordX + " " + block_bb.point01().coordY + ") (" + block_bb.point11().coordX + " " + block_bb.point11().coordY + "):");
                                    if (i00 != null)
                                        System.out.println("i00: " + i00.coordX + " " + i00.coordY);
                                    if (i10 != null)
                                        System.out.println("i10: " + i10.coordX + " " + i10.coordY);
                                    if (i01 != null)
                                        System.out.println("i01: " + i01.coordX + " " + i01.coordY);
                                    if (i11 != null)
                                        System.out.println("i11: " + i11.coordX + " " + i11.coordY);

                                    System.out.println("Enity AABB x Block AABB: " + e.aabb.intersects(b.getAABB()));
                                    System.out.println("Velocity AABB x Block AABB: " + vbb.intersects(b.getAABB()));
                                    System.out.println("Block AABB: " + b.getAABB().posX + " " + b.getAABB().posY + " " + b.getAABB().width + " " + b.getAABB().height);
                                    System.out.println("Distance vec2: " + vb.coordX + " " + vb.coordY);
                                    System.out.println("VD: " + vd.coordX + " " + vd.coordY);*/

                                    if (vb.coordX < vd.coordX || vb.coordY < vd.coordY)
                                    {
                                        collided = true;
                                        cbb = b.getAABB();
                                        vd = vb;
                                    }
                                }

                                if (e.aabb.isStandingOnTop(b.getAABB()))
                                {
                                    System.out.println(e.aabb + " standing on top of " + b.getAABB());
                                    e.isAirborne = false;
                                }
                            }
                        }

                    /*try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }*/

                    if (collided)
                    {
                        //System.out.println("Detected collision:");
                        //System.out.println("Block AABB: " + cbb.posX + " " + cbb.posY + " " + cbb.width + " " + cbb.height);
                        //System.out.println("VD: " + vd.coordX + " " + vd.coordY);

                        if (vd.coordX < vd.coordY)
                            e.processHorizontalCollision(cbb);
                        else
                            e.processVerticalCollision(cbb);
                    }

                    System.out.println("" + e.isAirborne);
                }

                e.tick();
            }
        }


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
