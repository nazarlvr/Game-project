package render.entity;

import entity.Entity;
import entity.EntityParticle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderParticle extends RenderEntity
{
    protected BufferedImage texture1;
    protected BufferedImage texture2;
    protected BufferedImage texture3;

    public RenderParticle(Entity e)
    {
        super(e);
        this.width = 0.5;
        this.height = 0.5;
        this.texture1 = RenderEntity.loadTexture("particle/particle1.png");
        this.texture2 = RenderEntity.loadTexture("particle/particle2.png");
        this.texture3 = RenderEntity.loadTexture("particle/particle3.png");
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        EntityParticle particle = (EntityParticle) this.entity;
        if(particle.age > 6)
            this.texture = texture1;
        else if (particle.age > 3)
            this.texture = texture2;
        else
            this.texture = texture3;

        super.render(g, x, y, w, h);
    }
}
