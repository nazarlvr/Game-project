package entity;

import block.Block;
import util.AABB;
import game.Game;
import util.Vec2;
import world.World;

import java.util.concurrent.TimeUnit;

public class Entity
{
    public World world;
    public int entityId;
    private int entityData;
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    public double velX;
    public double velY;
    protected AABB aabb;
    protected double distX;
    protected double distY;
    public boolean isAirborne;

    public Entity() { }

    public Entity(double x, double y)
    {
        this();
        this.posX = x;
        this.posY = y;
    }

    public void launchX(double x)
    {
        this.velX += x;
    }

    public void launchY(double y)
    {
        this.velY += y;
    }

    public void init()
    {
        this.aabb = new AABB();
        this.velX = 0;
        this.velY = 0;
        this.distX = 0;
        this.distY = 0;
    }

    public void checkCollisions()
    {
        boolean collided = true;

        while (collided)
        {
            this.updateAABB();
            this.isAirborne = true;
            collided = false;
            AABB vbb = this.getAABB();
            vbb.expand(this.velX, this.velY);
            Vec2 d = new Vec2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            AABB cbb = new AABB();

            System.out.println("Entity pos: " + this.posX + " " + this.posY + " vel: " + this.velX + " " + this.velY);
            System.out.println("Entity AABB: " + this.aabb);
            System.out.println("Velocity AABB: " + vbb);

            for (int x = 0; x < this.world.width; ++x)
                for (int y = 0; y < this.world.height; ++y)
                {
                    Block b = this.world.getBlock(x, y);

                    if (b != null && b.isCollideable())
                    {
                        if (b.getAABB().intersects(vbb) && !b.getAABB().intersects(this.aabb))
                        {
                            AABB block_bb = b.getAABB();
                            Vec2 bd = b.getAABB().vectorDistance(this.aabb, this.getVelVec2());
                            //System.out.println("Block " + b.getAABB() + " intersects VAABB " + vbb + " distance = " + bd);
                            //b.getAABB().printIntersectionPoints(e.aabb, e.getVelVec2());

                            /*System.out.println("p00: " + this.aabb.point00() + " " + this.aabb.point00().add(this.getVelVec2()) + ":");
                            Vec2 i00 = Vec2.intersection_lines(block_bb.point01(), block_bb.point11(), this.aabb.point00(), this.aabb.point00().add(this.getVelVec2()));
                            if (i00 != null)
                                System.out.println(i00.coordX + " " + i00.coordY);
                            else
                                System.out.println("No intersection");
                            Vec2 i10 = Vec2.intersection_lines(block_bb.point01(), block_bb.point11(), this.aabb.point10(), this.aabb.point10().add(this.getVelVec2()));
                            Vec2 i01 = Vec2.intersection_lines(block_bb.point01(), block_bb.point11(), this.aabb.point01(), this.aabb.point01().add(this.getVelVec2()));
                            Vec2 i11 = Vec2.intersection_lines(block_bb.point01(), block_bb.point11(), this.aabb.point11(), this.aabb.point11().add(this.getVelVec2()));

                            System.out.println("Intersections with " + block_bb.point01() + " " + block_bb.point11() + ":");
                            if (i00 != null)
                                System.out.println("i00: " + i00);
                            if (i10 != null)
                                System.out.println("i10: " + i10);
                            if (i01 != null)
                                System.out.println("i01: " + i01);
                            if (i11 != null)
                                System.out.println("i11: " + i11);

                            System.out.println("Enity AABB x Block AABB: " + this.aabb.intersects(b.getAABB()));
                            System.out.println("Velocity AABB x Block AABB: " + vbb.intersects(b.getAABB()));
                            System.out.println("Distance vec2: " + bd.coordX + " " + bd.coordY);
                            System.out.println("VD: " + bd.coordX + " " + bd.coordY);*/

                            if (bd.lensqr() < d.lensqr())
                            {
                                collided = true;
                                cbb = b.getAABB();
                                d = bd;
                            }
                        }

                        if (this.getAABB().isStandingOnTop(b.getAABB()))
                        {
                            //System.out.println(this.aabb + " standing on top of " + b.getAABB());
                            this.isAirborne = false;
                        }
                    }
                }

            if (collided)
            {
                        /*System.out.println("Detected collision:");
                        System.out.println("Block AABB: " + cbb.posX + " " + cbb.posY + " " + cbb.width + " " + cbb.height);
                        System.out.println("VD: " + vd.coordX + " " + vd.coordY);*/
                this.processCollision(cbb, d);

                /*try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }*/

                        /*if (vd.coordX < vd.coordY)
                            e.processHorizontalCollision(cbb);
                        else
                            e.processVerticalCollision(cbb);*/
            }

            System.out.println("" + this.isAirborne);
        }
    }

    public void tick()
    {
        this.distX = this.velX;
        this.distY = this.velY;

        this.checkCollisions();

        posX += distX;
        posY += distY;
        this.updateAABB();


        /*if (!world.isAirborne(this) && velY < 0)
        {
            velY = 0;
        }

        if (velY <= 0.01 && velY >= -0.01)
        {
            velY = 0;
        }
        else
        {
            double dY = velY / Game.tick_frequency;
            velY -= dY;
            posY += dY;
        }

        if (world.isAirborne(this) && velY <= 0)
        {
            velY = -1;
        }*/

        if (posX < 0)
            posX = 0;

        if (posY < 0)
            posY = 0;

        if (posX > world.width)
            posX = world.width;

        if (posY > world.height)
            posY = world.height;

        /*if (posY - ((int)posY) <= Game.collisionPrecision && velY <= 0)
            posY = (int)posY;*/

        if (this.isAirborne)
        {
            this.velY -= 0.08;
            this.velY *= 0.98;
            //if (velY > -velMax)
            //{
            //velY -= 9.8 / Game.tick_frequency;
            //}
        }
        else
        {
            if (this.velY < 0)
                this.velY = 0;

            if (this.velX <= 0.01 && this.velX >= -0.01)
                this.velX = 0;
        }

        this.velX *= 0.85;

        /*if (velX >= -0.001 && velX <= 0.001)
            velX = 0;

        if (velY >= -0.001 && velY <= 0.001)
            velY = 0;*/

        /*if (velX > Game.velMax)
            velX = Game.velMax;

        if (velX < -Game.velMax)
            velX = -Game.velMax;

        if (velY > Game.velMax)
            velY = Game.velMax;

        if (velY < -Game.velMax)
            velY = -Game.velMax;*/
    }

    public void processCollision(AABB bb, Vec2 v)
    {
        double dx = v.coordX;
        double dy = v.coordY;

        System.out.println("Processing collision:");
        System.out.println("Entity AABB: " + this.aabb);
        System.out.println("Block AABB: " + bb);
        //System.out.println("Distance: " + d);
        //System.out.println("a: " + Math.toDegrees(a));
        //System.out.println("dx, dy: " + dx + " " + dy);

        this.distX -= dx;
        this.distY -= dy;
        this.posX += dx;
        this.posY += dy;
        this.updateAABB();

        if (this.aabb.posX + this.aabb.width <= bb.posX || bb.posX + bb.width <= this.aabb.posX)
        {
            this.velX = 0;
            this.distX = 0;
        }

        if (this.aabb.posY + this.aabb.height <= bb.posY || bb.posY + bb.height <= this.aabb.posY)
        {
            this.velY = 0;
            this.distY = 0;
        }
    }

    /*public void processHorizontalCollision(AABB bb)
    {
        double px = this.posX;

        if (this.velX < 0)
            px = bb.posX + bb.width + this.width / 2;

        if (this.velX > 0)
            px = bb.posX - this.width / 2;

        double dy = this.velY * (px - this.posX) / this.velX;
        this.posX = px;
        this.distX = 0;
        this.distY -= dy;
        this.velX = 0;

        System.out.println("Horizontal collision: " + this.aabb + " " + bb);
    }

    public void processVerticalCollision(AABB bb)
    {
        double py = this.posY;

        if (this.velY < 0)
            py = bb.posY + bb.height;

        if (this.velY > 0)
            py = bb.posY - this.height;

        double dx = this.velX * (py - this.posY) / this.velY;
        this.posY = py;
        this.distX -= dx;
        this.distY = 0;
        this.velY = 0;

        System.out.println("Vertical collision: " + this.aabb + " " + bb);
    }*/

    public double getPosX()
    {
        return this.posX;
    }

    public double getPosY()
    {
        return this.posY;
    }

    public void setPosX(double posx)
    {
        this.posX = posx;
    }

    public void setPosY(double posy)
    {
        this.posY = posy;
    }

    public void updateAABB()
    {
        this.aabb.posX = this.posX - this.width / 2;
        this.aabb.posY = this.posY;
        this.aabb.width = this.width;
        this.aabb.height = this.height;
    }

    public double getWidth()
    {
        return this.width;
    }

    public double getHeight()
    {
        return this.height;
    }

    public double getVelX()
    {
        return this.velX;
    }

    public double getVelY()
    {
        return this.velY;
    }

    public Vec2 getVelVec2()
    {
        return new Vec2(this.velX, this.velY);
    }

    public AABB getAABB()
    {
        return this.aabb.copy();
    }
}
