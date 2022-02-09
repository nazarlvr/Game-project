package entity;

public class EntityPlayer extends Entity
{
    public EntityPlayer(double x, double y)
    {
        super(x,y);
        this.width = 0.8;
        this.height = 1.6;
        maxHP = 20;
    }

    @Override
    public void tick() {
        //this.posX += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //this.posY += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //System.out.println(posX + " " + posY + " " + world.isAirborne(this) + " " + velX + " " + velY);
        super.tick();
    }
}
