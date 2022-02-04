package entity;

import game.Game;

public class EntitySlime extends Entity
{
    public EntitySlime(double x, double y)
    {
        super(x,y);
        this.width = 0.5;
        this.height = 0.5;
    }

    @Override
    public void tick() {
        //this.posX += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //this.posY += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //System.out.println(posX + " " + posY + " " + world.isAirborne(this) + " " + velX + " " + velY);
        super.tick();
    }
}
