package render.entity;

import entity.Entity;
import entity.particle.EntityParticle;
import render.RenderManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderParticle extends RenderEntity
{
    public RenderParticle(Entity e)
    {
        super(e);
        this.width = e.getWidth();
        this.height = e.getHeight();
    }

    @Override
    public void render(Graphics g, int x, int y, int w, int h) {
        EntityParticle particle = (EntityParticle) this.entity;

        if (particle.age > 0)
        {
            int interval = particle.max_age / RenderManager.texture_particle.get(particle.name).length;
            this.texture = RenderManager.texture_particle.get(particle.name)[(particle.age - 1) / interval];
            super.render(g, x, y, w, h);
        }
    }
}
