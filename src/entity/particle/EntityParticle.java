package entity.particle;

import entity.Entity;

public abstract class EntityParticle extends Entity
{
    public int age;
    public int max_age;
    public String name;

    public EntityParticle(double x, double y)
    {
        super(x, y);
    }

    @Override
    public void init() {
        super.init();
        this.age = this.max_age;
    }

    @Override
    public void tick()
    {
        --age;

        if (age <= 0)
            this.isDead = true;
    }

    @Override
    public boolean isEntityCollidable() {
        return false;
    }

    @Override
    public boolean isBlockCollidable() {
        return false;
    }

    @Override
    public boolean isGravityAffected() {
        return false;
    }
}
