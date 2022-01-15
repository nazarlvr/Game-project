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

    public void tick()
    {
        if (!world.isAirborne(this) && velY < 0)
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
            velY = -9.8;
        }
        if (posX < 0)
            posX = 0;

        if (posY < 0)
            posY = 0;

        if (posX > world.width)
            posX = world.width;

        if (posY > world.height)
            posY = world.height;
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
