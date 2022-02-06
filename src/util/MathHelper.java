package util;

public class MathHelper
{
    public static double round(double x)
    {
        if (Double.isInfinite(x) || Double.isNaN(x))
            return x;
        else
            return Math.round(x * 1e6) / 1e6;
    }
}
