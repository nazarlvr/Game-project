package entity;

import game.Game;
import util.MathHelper;

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
        if (!this.isAirborne && this.world.getTime() % 50 == 0)
            this.launchY(0.42);

        if (this.isAirborne && MathHelper.round(this.velX) == 0)
            this.launchX(Math.random() - 0.5);

        super.tick();
    }
}
