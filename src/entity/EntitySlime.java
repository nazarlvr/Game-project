package entity;

import game.Game;
import item.ItemStack;
import item.Items;
import util.MathHelper;

public class EntitySlime extends Entity
{
    public EntitySlime(double x, double y)
    {
        super(x,y);
        this.width = 0.5;
        this.height = 0.5;
        maxHP = 2;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isAirborne && this.world.getTime() % 50 == 0)
            this.launchY(0.42);

        if (this.isAirborne && MathHelper.round(this.velX) == 0)
            this.launchX(Math.random() - 0.5);
    }

    public ItemStack getDrop()
    {
        return new ItemStack(Items.slime);
    }
}
