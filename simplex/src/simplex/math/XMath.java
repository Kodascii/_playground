package simplex.math;

import java.util.Arrays;

public class XMath {


    public static float lerp(float x, float y, float t) { return x + (y - x) * t; }

    public static class Clamp
    {
        public static double xF0bT0d(double x, double b, float d)
        {
            return x * (d / b) ;
        }

        public static double xF0bTcd(double x, double b, double c, double d) { return x * (d - c) / b + c; }

        public static double xFabT0d(double x, double a, double b, double d) { return (x - a) / (b - a) * d; }

        public static double xFabTcd(double x, double a, double b, double c, double d) { return (x - a) / (b - a) * (d - c) + c; }
    }

    public static int convertToBase(int value, int radix)
    {
        int Q = value, R = 0;
        int result = 0;
        int p = 0;

        while(Q != 0)
        {
            R = Q % radix;
            result += (int) R * Math.pow(10, p);
            p++;

            Q = Q/radix;
        }

        return result;
    }

    public static int[] _$(int[] alphabet, int level, int seed)
    {
        int[] result = new int[level];
        int Q = seed, R = 0;
        int p = 0;

        while(Q != 0)
        {
            R = Q % alphabet.length;
            result[level-p-1] = alphabet[R];
            p++;

            Q = Q / alphabet.length;
        }

        return result;
    }

    public static float[] _$(float[] alphabet, int level, int seed)
    {
        float[] result = new float[level];
        int Q = seed, R = 0;
        int p = 0;

        while(Q != 0)
        {
            R = Q % alphabet.length;
            result[level-p-1] = alphabet[R];
            p++;

            Q = Q / alphabet.length;
        }

        return result;
    }

    public static char[] _$(String alphabet, int level, int seed)
    {
        char[] result = new char[level];
        for (int i = 0; i < level; i++)
            result[i] = alphabet.charAt(0);

        int Q = seed, R = 0;
        int p = 0;

        while (Q != 0)
        {
            R = Q % alphabet.length();
            result[level-p-1] = alphabet.charAt(R);
            p++;

            Q = Q / alphabet.length();
        }

        return result;
    }
}
