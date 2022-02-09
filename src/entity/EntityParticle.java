package entity;

public class EntityParticle extends Entity
{
    public int age;

    public EntityParticle(double x, double y)
    {
        super(x, y);
    }

    @Override
    public void init() {
        super.init();

        age = 9;
    }

    @Override
    public void tick()
    {
        --age;

        if (age <= 0)
            this.isDead = true;
    }
}
