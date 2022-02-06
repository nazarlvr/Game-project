package util;

import block.Block;

public class Vec2
{
    public double coordX;
    public double coordY;

    public Vec2()
    {
        this.coordX = 0;
        this.coordY = 0;
    }

    public Vec2(double x, double y)
    {
        this.coordX = x;
        this.coordY = y;
    }

    public Vec2(double x1, double y1, double x2, double y2)
    {
        this(x2 - x1, y2 - y1);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Vec2)
            return ((Vec2) o).coordX == this.coordX && ((Vec2) o).coordY == this.coordY;
        else
            return false;
    }

    public Vec2 add(Vec2 v)
    {
        return new Vec2(this.coordX + v.coordX, this.coordY + v.coordY);
    }

    public Vec2 sub(Vec2 v)
    {
        return new Vec2(this.coordX - v.coordX, this.coordY - v.coordY);
    }

    public Vec2 mul(double k)
    {
        return new Vec2(this.coordX * k, this.coordY * k);
    }

    public double dot(Vec2 v)
    {
        return this.coordX * v.coordX + this.coordY * v.coordY;
    }

    public double lensqr()
    {
        return this.dot(this);
    }

    public double len()
    {
        return Math.sqrt(this.lensqr());
    }

    public Vec2 neg()
    {
        return new Vec2(-this.coordX, -this.coordY);
    }

    public Vec2 norm()
    {
        return this.mul(1 / this.len());
    }

    public static Vec2 intersection_segments(Vec2 p1, Vec2 p2, Vec2 p3, Vec2 p4)
    {
        double d = (p1.coordX - p2.coordX) * (p3.coordY - p4.coordY);
        double d0 = (p1.coordY - p2.coordY) * (p3.coordX - p4.coordX);

        if (d == d0)
            return null;
        else
        {
            d -= d0;

            double t = (p1.coordX - p3.coordX) * (p3.coordY - p4.coordY) - (p1.coordY - p3.coordY) * (p3.coordX - p4.coordX);
            double u = (p1.coordX - p3.coordX) * (p1.coordY - p2.coordY) - (p1.coordY - p3.coordY) * (p1.coordX - p2.coordX);

            if (d < 0 && (t < d || u < d || t > 0 || u > 0))
                return null;

            if (d > 0 && (t > d || u > d || t < 0 || u < 0))
                return null;

            /*double a = p1.coordX * p2.coordY - p1.coordY * p2.coordX;
            double b = p3.coordX * p4.coordY - p3.coordY * p4.coordX;
            Vec2 v = new Vec2((a * (p3.coordX - p4.coordX) - (p1.coordX - p2.coordX) * b) / d, (a * (p3.coordY - p4.coordY) - (p1.coordY - p2.coordY) * b) / d);*/
            Vec2 v = new Vec2();

            if (p1.coordX == p2.coordX)
                v.coordX = p1.coordX;
            else if (p3.coordX == p4.coordX)
                v.coordX = p3.coordX;
            else
                v.coordX = p1.coordX + t * (p2.coordX - p1.coordX) / d;

            if (p1.coordY == p2.coordY)
                v.coordY = p1.coordY;
            else if (p3.coordY == p4.coordY)
                v.coordY = p3.coordY;
            else
                v.coordY = p1.coordY + t * (p2.coordY - p1.coordY) / d;

            return v;
        }
    }

    /*public boolean between(Vec2 p1, Vec2 p2)
    {
        return Math.min(p1.coordX, p2.coordX) <= this.coordX && this.coordX <= Math.max(p1.coordX, p2.coordX) && Math.min(p1.coordY, p2.coordY) <= this.coordY && this.coordY <= Math.max(p1.coordY, p2.coordY);
    }

    public static Vec2 intersection(Vec2 p1, Vec2 p2, Vec2 p3, Vec2 p4)
    {
        Vec2 v = Vec2.intersection_lines(p1, p2, p3, p4);

        if (v != null && v.between(p1, p2) && v.between(p3, p4))
            return v;
        else
            return null;
    }*/

    /*public static void intersection_print(Vec2 p1, Vec2 p2, Vec2 p3, Vec2 p4)
    {
        Vec2 v = Vec2.intersection_lines(p1, p2, p3, p4);

        if (v != null)
        {
            System.out.println(v + " " + p1 + " " + p2 + " " + p3 + " " + p4);
        }
        else
            System.out.println("No intersection");
    }*/

    @Override
    public String toString()
    {
        return "(" + this.coordX + ", " + this.coordY + ")";
    }
}
