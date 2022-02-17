package entity;

import inventory.Inventory;
import item.Item;
import item.ItemStack;

public class EntityPlayer extends Entity
{
    public Inventory inv;

    public EntityPlayer(double x, double y)
    {
        super(x,y);
        this.width = 0.8;
        this.height = 1.6;
        maxHP = 20;
    }

    @Override
    public void init() {
        super.init();
        inv = new Inventory(36);
    }

    @Override
    public void tick() {
        //this.posX += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //this.posY += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //System.out.println(posX + " " + posY + " " + world.isAirborne(this) + " " + velX + " " + velY);
        System.out.println("{");
        for (ItemStack s : this.inv.items)
        {
            System.out.println(s);
        }
        System.out.println("}");

        super.tick();
    }

    @Override
    public boolean canPickItems() {
        return true;
    }

    @Override
    public void pickUp(EntityItem e)
    {
        if (e.itemStack != null)
        {
            e.itemStack = this.inv.add(e.itemStack);
        }
    }
}
