package entity;

import game.Game;

public class EntitySlime extends Entity
{
    public EntitySlime(double x, double y)
    {
        super(x,y);
    }

    @Override
    public void tick() {
        //this.posX += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //this.posY += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        System.out.println(posX + " " + posY + " " + world.isAirborne(this) + " " + velY);
        super.tick();
    }
}
