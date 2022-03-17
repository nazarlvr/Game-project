package entity;

import game.Game;
import inventory.Inventory;

public class EntityPlayer extends Entity
{
    public Inventory inventory;
    public Game game;

    public EntityPlayer(double x, double y, Game g)
    {
        super(x,y);
        this.width = 0.8;
        this.height = 1.6;
        this.game = g;
        this.maxHP = 20;
    }

    @Override
    public void init() {
        super.init();
        inventory = new Inventory(36);
    }

    @Override
    public void tick() {
        //this.posX += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //this.posY += 3 * (Math.random() - 0.5) / Game.tick_frequency;
        //System.out.println(posX + " " + posY + " " + world.isAirborne(this) + " " + velX + " " + velY);
        /*System.out.println("{");
        for (ItemStack s : this.inv.items)
        {
            System.out.println(s);
        }
        System.out.println("}");*/

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
            e.itemStack = this.inventory.add(e.itemStack);
        }
    }

    @Override
    public void onDeath() {
        super.onDeath();
        this.inventory.dropItems(this.world, posX ,posY + 1);
    }
}
