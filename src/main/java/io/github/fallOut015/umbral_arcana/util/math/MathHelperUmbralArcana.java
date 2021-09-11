package io.github.fallOut015.umbral_arcana.util.math;

public class MathHelperUmbralArcana {
    public static double quad(double x, double xint2, double max, int exp, boolean clamp) {
        double v = -max * Math.pow(((2 * x - xint2) / 2) / (xint2 / 2), exp) + max;
        if(clamp) {
            return MathHelper.clamp(v, 0 < max ? 0 : max, max > 0 ? max : 0);
        } else {
            return v;
        }
    }
}
