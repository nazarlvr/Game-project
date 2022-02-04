package entity;

import util.AABB;
import game.Game;
import util.Vec2;
import world.World;

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
    public AABB aabb;
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

    public void tick()
    {
        posX += distX;
        posY += distY;


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

            this.velX *= 0.85;
        }

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

        this.distX = this.velX;
        this.distY = this.velY;
    }

    public void processHorizontalCollision(AABB bb)
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
    }

    public double getPosX()
    {
        return this.posX;
    }

    public void setPosX(double posx)
    {
        this.posX = posx;
    }

    public void setPosY(double posy)
    {
        this.posY = posy;
    }

    public double getPosY()
    {
        return this.posY;
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
}
