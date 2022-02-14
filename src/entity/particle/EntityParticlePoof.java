package entity.particle;

public class EntityParticlePoof extends EntityParticle
{
    public EntityParticlePoof(double x, double y)
    {
        super(x, y);
        this.max_age = 16;
        this.width = 0.5;
        this.height = 0.5;
        this.name = "poof";
    }
}
