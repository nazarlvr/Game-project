package entity;

import game.Game;
import world.World;

public class Entity
{
    public World world;
    public int entityId;
    private int entityData;
    protected double posX;
    protected double posY;
    public double velX;
    public double velY;

    public Entity() {}

    public Entity(double x, double y)
    {
        posX = x;
        posY = y;
    }

    public void launchX(double v)
    {
        velX += v * Game.tick_frequency;
    }

    public void launchY(double v)
    {
        velY += v * Game.tick_frequency;
    }

    public void tick()
    {
        if (world.isAirborne(this))
        {
            launchY(-0.005);
            //if (velY > -velMax)
            //{
                //velY -= 9.8 / Game.tick_frequency;
            //}
        }
        else
        {
            if (velY < 0)
                velY = 0;

            if (velX < 0)
                launchX(0.01);
            if (velX > 0)
                launchX(-0.01);
        }

        if (velX >= -0.01 && velX <= 0.01)
            velX = 0;

        if (velY >= -0.01 && velY <= 0.01)
            velY = 0;

        if (velX > Game.velMax)
            velX = Game.velMax;

        if (velX < -Game.velMax)
            velX = -Game.velMax;

        if (velY > Game.velMax)
            velY = Game.velMax;

        if (velY < -Game.velMax)
            velY = -Game.velMax;

        posX += velX / Game.tick_frequency;
        posY += velY / Game.tick_frequency;


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
            posX += world.width;

        if (posY < 0)
            posY += world.height;

        if (posX > world.width)
            posX -= world.width;

        if (posY > world.height)
            posY -= world.height;

        if (posY - ((int)posY) <= Game.collisionPrecision && velY <= 0)
            posY = (int)posY;
    }

    public double getPosX(){return this.posX;}

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
}
