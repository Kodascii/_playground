package simplex.math;


/**
 *
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public class XMath {

    /**
     * Short form of linear interpolation.
     * @see simplex.anim.AnimationCurve.Easing
     **/
    public static double lerp(double x, double y, double t)
    {
        return x + (y - x) * t;
    }


    /**
     * Maps a value {@code x} from the interval {@code [a, b]}, to the interval {@code [c, d]}.
     **/
    public static double clamp(double x, double a, double b, double c, double d)
    {
        return (x - a) / (b - a) * (d - c) + c;
    }


    /**
     * <p>Converts a given {@code value} from base 10, to the base specified by {@code radix}.</p>
     * <strong>Example :</strong>
     * <pre>{@code
     *      int val = XMath.convertToBase(5, 2);
     *      System.out.println(val);
     * }</pre>
     * <p><strong>Output :</strong> {@code 101}.</p>
     **/
    public static int convertToBase(int value, int radix)
    {
        int quotient = value, rest = 0;
        int result = 0;
        int p = 0;

        while(quotient != 0)
        {
            rest = quotient % radix;
            result += (int) rest * Math.pow(10, p);
            p++;

            quotient = quotient/radix;
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
