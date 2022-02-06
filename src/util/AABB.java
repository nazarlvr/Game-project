package util;

import game.Game;

import java.util.ArrayList;
import java.util.Iterator;

public class AABB
{
    public double posX, posY, width, height;

    public AABB() { }

    public AABB(double x, double y, double w ,double h)
    {
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;
    }

    public Vec2 point00()
    {
        return new Vec2(this.posX, this.posY);
    }

    public Vec2 point10()
    {
        return new Vec2(this.posX + this.width, this.posY);
    }

    public Vec2 point01()
    {
        return new Vec2(this.posX, this.posY + this.height);
    }

    public Vec2 point11()
    {
        return new Vec2(this.posX + this.width, this.posY + this.height);
    }

    public AABB copy()
    {
        return new AABB(this.posX, this.posY, this.width, this.height);
    }

    public void expand(double dx, double dy)
    {
        if (dx < 0)
        {
            this.posX += dx;
            this.width -= dx;
        }
        else
            this.width += dx;

        if (dy < 0)
        {
            this.posY += dy;
            this.height -= dy;
        }
        else
            this.height += dy;
    }

    /*public void fit(AABB bb)
    {
        if (this.posX < bb.posX && this.posX + this.width > bb.posX)
            this.width = bb.posX - this.posX;

        if (bb.posX < this.posX && bb.posX + bb.width > this.posX)
            this.posX = bb.posX + bb.width;

        if (this.posY < bb.posY && this.posY + this.height > bb.posY)
            this.height = bb.posY - this.posY;

        if (bb.posY < this.posY && bb.posY + bb.height > this.posY)
            this.posY = bb.posY + bb.height;
    }

    public double intersectX(AABB bb)
    {
        return this.width + bb.width + Math.min(this.posX, bb.posX) - Math.max(this.posX + this.width, bb.posX + bb.width);
    }

    public double intersectY(AABB bb)
    {
        return this.height + bb.height + Math.min(this.posY, bb.posY) - Math.max(this.posY + this.height, bb.posY + bb.height);
    }*/

    public boolean intersects(AABB bb)
    {
        if (MathHelper.round(this.posX + this.width) <= MathHelper.round(bb.posX))
            return false;
        if (MathHelper.round(bb.posX + bb.width) <= MathHelper.round(this.posX))
            return false;
        if (MathHelper.round(this.posY + this.height) <= MathHelper.round(bb.posY))
            return false;
        if (MathHelper.round(bb.posY + bb.height) <= MathHelper.round(this.posY))
            return false;

        return true;
    }

    /*public boolean isAdjacentX(AABB bb)
    {
        if (this.posY + this.height <= bb.posY)
            return false;
        if (bb.posY + bb.height <= this.posY)
            return false;

        return this.posX == bb.posX + bb.width || this.posX + this.width == bb.posX;
    }

    public boolean isAdjacentY(AABB bb)
    {
        if (this.posX + this.width <= bb.posX)
            return false;
        if (bb.posX + bb.width <= this.posX)
            return false;

        return this.posY == bb.posY + bb.height || this.posY + this.height == bb.posY;
    }*/

    public boolean isStandingOnTop(AABB bb)
    {
        if (MathHelper.round(this.posX + this.width) <= MathHelper.round(bb.posX))
            return false;
        if (MathHelper.round(bb.posX + bb.width) <= MathHelper.round(this.posX))
            return false;

        return MathHelper.round(this.posY) == MathHelper.round(bb.posY + bb.height);
    }

    public boolean isInside(double x, double y)
    {
        return x > this.posX && x < this.posX + this.width && y > this.posY && y < this.posY + this.height;
    }

    /*public double distance(Vec2 p, Vec2 v)
    {
        Vec2 p0x = Vec2.intersection_segments(p, p.add(v), this.point00(), this.point10());
        Vec2 pyx = Vec2.intersection_segments(p, p.add(v), this.point01(), this.point11());
        Vec2 p0y = Vec2.intersection_segments(p, p.add(v), this.point00(), this.point01());
        Vec2 pxy = Vec2.intersection_segments(p, p.add(v), this.point10(), this.point11());

        double d = Double.POSITIVE_INFINITY;

        if (p0x != null)
            d = Math.min(d, p.sub(p0x).lensqr());

        if (pyx != null)
            d = Math.min(d, p.sub(pyx).lensqr());

        if (p0y != null)
            d = Math.min(d, p.sub(p0y).lensqr());

        if (pxy != null)
            d = Math.min(d, p.sub(pxy).lensqr());

        return Math.sqrt(d);
    }

    public double distance(AABB bb, Vec2 v)
    {
        return Math.min(
                Math.min(
                        Math.min(this.distance(bb.point00(), v),
                                this.distance(bb.point10(), v)),
                        Math.min(this.distance(bb.point01(), v),
                                this.distance(bb.point11(), v))
                ),
                Math.min(
                        Math.min(bb.distance(this.point00(), v.neg()),
                                bb.distance(this.point10(), v.neg())),
                        Math.min(bb.distance(this.point01(), v.neg()),
                                bb.distance(this.point11(), v.neg()))
                )
        );
    }*/

    public Vec2 vectorDistance(Vec2 p, Vec2 v)
    {
        Vec2 c = new Vec2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Vec2 p0x = Vec2.intersection_segments(p, p.add(v), this.point00(), this.point10());
        Vec2 pyx = Vec2.intersection_segments(p, p.add(v), this.point01(), this.point11());
        Vec2 p0y = Vec2.intersection_segments(p, p.add(v), this.point00(), this.point01());
        Vec2 pxy = Vec2.intersection_segments(p, p.add(v), this.point10(), this.point11());

        if (p0x != null && p.sub(p0x).lensqr() < c.lensqr())
            c = p0x.sub(p);

        if (pyx != null && p.sub(pyx).lensqr() < c.lensqr())
            c = pyx.sub(p);

        if (p0y != null && p.sub(p0y).lensqr() < c.lensqr())
            c = p0y.sub(p);

        if (pxy != null && p.sub(pxy).lensqr() < c.lensqr())
            c = pxy.sub(p);

        c.coordX = MathHelper.round(c.coordX);
        c.coordY = MathHelper.round(c.coordY);
        return c;
    }

    public Vec2 vectorDistance(AABB bb, Vec2 v)
    {
        //Vec2 vmin = new Vec2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        Vec2 c = new Vec2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Vec2 v00 = this.vectorDistance(bb.point00(), v);
        Vec2 v10 = this.vectorDistance(bb.point10(), v);
        Vec2 v01 = this.vectorDistance(bb.point01(), v);
        Vec2 v11 = this.vectorDistance(bb.point11(), v);

        Vec2 b00 = bb.vectorDistance(this.point00(), v.neg());
        Vec2 b10 = bb.vectorDistance(this.point10(), v.neg());
        Vec2 b01 = bb.vectorDistance(this.point01(), v.neg());
        Vec2 b11 = bb.vectorDistance(this.point11(), v.neg());

        if (v00.lensqr() < c.lensqr())
            c = v00;

        if (v10.lensqr() < c.lensqr())
            c = v10;

        if (v01.lensqr() < c.lensqr())
            c = v01;

        if (v11.lensqr() < c.lensqr())
            c = v11;

        if (b00.lensqr() < c.lensqr())
            c = b00;

        if (b10.lensqr() < c.lensqr())
            c = b10;

        if (b01.lensqr() < c.lensqr())
            c = b01;

        if (b11.lensqr() < c.lensqr())
            c = b11;

        return c;

        /*ArrayList<Vec2> vlist = new ArrayList<Vec2>();
        vlist.add(v00);
        vlist.add(v10);
        vlist.add(v01);
        vlist.add(v11);

        vlist.add(b00);
        vlist.add(b10);
        vlist.add(b01);
        vlist.add(b11);

        for (Vec2 vi : vlist)
        {
            if (vmin.coordX == vmin.coordY)
                if (vi.coordX <= vmin.coordX || vi.coordY <= vmin.coordY)
                    vmin = vi;
            else if (vi.coordX < vmin.coordX || vi.coordY < vmin.coordY)
                vmin = vi;
        }

        return vmin;*/
        /*return new Vec2(Math.min(Math.min(Math.min(v00.coordX, v10.coordX), Math.min(v01.coordX, v11.coordX)), Math.min(Math.min(b00.coordX, b10.coordX), Math.min(b01.coordX, b11.coordX))),
                Math.min(Math.min(Math.min(v00.coordY, v10.coordY), Math.min(v01.coordY, v11.coordY)), Math.min(Math.min(b00.coordY, b10.coordY), Math.min(b01.coordY, b11.coordY))));*/
    }

    /*public void printIntersectionPoint(Vec2 p1, Vec2 p2, Vec2 p3, Vec2 p4, Vec2 p, boolean v)
    {
        Vec2 i = Vec2.intersection(p1, p2, p3, p4);
        Vec2.intersection_print(p1, p2, p3, p4);
        if (i != null)
            System.out.println(i + " " + p.sub(i).len() + (v ? " vertical" : " horizontal"));
        else
            System.out.println(null + " " + null + (v ? " vertical" : " horizontal"));
    }

    public void printIntersectionPoints(Vec2 p, Vec2 v)
    {
        this.printIntersectionPoint(p, p.add(v), this.point00(), this.point10(), p, true);
        this.printIntersectionPoint(p, p.add(v), this.point01(), this.point11(), p, true);
        this.printIntersectionPoint(p, p.add(v), this.point00(), this.point01(), p, false);
        this.printIntersectionPoint(p, p.add(v), this.point10(), this.point11(), p, false);
    }

    public void printIntersectionPoints(AABB bb, Vec2 v)
    {
        this.printIntersectionPoints(bb.point00(), v);
        this.printIntersectionPoints(bb.point10(), v);
        this.printIntersectionPoints(bb.point01(), v);
        this.printIntersectionPoints(bb.point11(), v);

        bb.printIntersectionPoints(this.point00(), v.neg());
        bb.printIntersectionPoints(this.point10(), v.neg());
        bb.printIntersectionPoints(this.point01(), v.neg());
        bb.printIntersectionPoints(this.point11(), v.neg());
    }*/

    @Override
    public String toString()
    {
        return "(" + this.posX + ", " + this.posY + ") (" + (this.posX + this.width) + ", " + (this.posY + this.height) + ")";
    }
}
