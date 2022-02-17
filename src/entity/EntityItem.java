package entity;

import item.ItemStack;

public class EntityItem extends Entity
{
    public int age;
    public ItemStack itemStack;

    public EntityItem(double x, double y)
    {
        super(x, y);
        itemStack = null;
        this.width = 0.5;
        this.height = 0.5;
        this.maxHP = 1000;
    }

    public EntityItem(double x, double y, ItemStack s)
    {
        this(x, y);
        itemStack = s;
    }

    @Override
    public void init() {
        super.init();

        age = 200;
    }

    @Override
    public void tick()
    {
        super.tick();

        --age;

        if (age <= 0 || this.itemStack == null || this.itemStack.stack_size == 0)
            this.isDead = true;
    }

    @Override
    public boolean isEntityCollidable() {
        return false;
    }
}
