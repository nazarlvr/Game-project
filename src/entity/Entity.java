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

    public Entity() {}

    public Entity(double x, double y)
    {
        posX = x;
        posY = y;
    }

    public void tick()
    {
        if (posX < 0)
            posX = 0;

        if (posY < 0)
            posY = 0;

        if (posX > world.width)
            posX = world.width;

        if (posY > world.height)
            posY = world.height;
    }

    public double getPosX()
    {
        return this.posX;
    }

    public double getPosY()
    {
        return this.posY;
    }
}
