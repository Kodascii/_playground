package simplex.anim;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

 /**
 * <p>This {@code interface} holds an enumeration {@link Easing}, that contains pre-build list of animations. It also allows the programmer
 * to define its own {@code AnimationCurve}, in form of</p>
 * <pre>{@code
 *      AnimationCurve myAnimation = new AnimationCurve()
 *      {
 *          @Override
 *          public double f(float x)
 *          {
 *              return 2x - 1;
 *          }
 *      };
 * }</pre>

 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public interface AnimationCurve {

    /**
     * Nothing in nature moves linearly from one point to another. In reality, things tend to accelerate or decelerate as they move.
     * Our brains are wired to expect this kind of motion, so when animating, you should use this to your advantage.
     * Natural motion makes your users feel more comfortable with your apps, which in turn leads to a better overall experience.
     *
     * @ref : <a href="https://web.dev/the-basics-of-easing/">Paul Lewis</a>
     **/
    enum Easing implements AnimationCurve
    {
        LINEAR { @Override public double f(float x) { return x; }},

        EASE_IN_QUAD { @Override public double f(float x) { return x * x; }},
        EASE_OUT_QUAD { @Override public double f(float x) { return 1 - (1 - x) * (1 - x); }},
        EASE_IN_OUT_QUAD { @Override public double f(float x) { return x < 0.5 ? 2 * x * x : 1 - pow(-2 * x + 2, 2) / 2; }},

        EASE_IN_CUBIC { @Override public double f(float x) { return x * x * x; }},
        EASE_OUT_CUBIC { @Override public double f(float x) { return 1 - pow(1 - x, 3); }},
        EASE_IN_OUT_CUBIC { @Override public double f(float x) { return x < 0.5 ? 4 * x * x * x : 1 - pow(-2 * x + 2, 3) / 2; }},

        EASE_IN_QUART { @Override public double f(float x) { return x * x * x * x; }},
        EASE_OUT_QUART { @Override public double f(float x) { return 1 - pow(1 - x, 4); }},
        EASE_IN_OUT_QUART { @Override public double f(float x) { return x < 0.5 ? 8 * x * x * x * x : 1 - pow(-2 * x + 2, 4) / 2; }},

        EASE_IN_QUINT { @Override public double f(float x) { return x * x * x * x * x; }},
        EASE_OUT_QUINT { @Override public double f(float x) { return 1 - pow(1 - x, 5); }},
        EASE_IN_OUT_QUINT { @Override public double f(float x) { return x < 0.5 ? 16 * x * x * x * x * x : 1 - pow(-2 * x + 2, 5) / 2; }},

        EASE_IN_EXPO { @Override public double f(float x) { return x == 0 ? 0 : pow(2, 10 * x - 10); }},
        EASE_OUT_EXPO { @Override public double f(float x) { return x == 1 ? 1 : 1 - pow(2, -10 * x); }},
        EASE_IN_OUT_EXPO
        {
            @Override public double f(float x)
            {
                return x == 0 ? 0 : x == 1 ? 1
                        : x < 0.5 ? pow(2, 20 * x - 10) / 2
                        : (2 - pow(2, -20 * x + 10)) / 2;
            }
        },

        EASE_IN_BACK
        {
            @Override public double f(float x)
            {
                double c1 = 1.70158;
                double c3 = c1 + 1;
                return c3 * x * x * x - c1 * x * x;
            }
        },
        EASE_OUT_BACK
        {
            @Override public double f(float x)
            {
                double c1 = 1.70158;
                double c3 = c1 + 1;
                return 1 + c3 * pow(x - 1, 3) + c1 * pow(x - 1, 2);
            }
        },
        EASE_IN_OUT_BACK
        {
            @Override public double f(float x)
            {
                double c1 = 1.70158;
                double c2 = c1 * 1.525;

                return x < 0.5
                        ? (pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2
                        : (pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2;
            }
        },

        EASE_IN_SINE { @Override public double f(float x) { return 1f - (cos((x * PI) / 2)); }},
        EASE_OUT_SINE { @Override public double f(float x) { return (float) sin((x * PI) / 2); }},
        EASE_IN_OUT_SINE { @Override public double f(float x) { return (float) -(cos(PI * x) - 1) / 2;}},

        EASE_IN_CIRC { @Override public double f(float x) { return 1 - sqrt(1 - pow(x, 2)); }},
        EASE_OUT_CIRC { @Override public double f(float x) { return sqrt(1 - pow(x - 1, 2)); }},
        EASE_IN_OUT_CIRC
        {
            @Override public double f(float x)
            {
                return x < 0.5 ? (1 - sqrt(1 - pow(2 * x, 2))) / 2
                        : (sqrt(1 - pow(-2 * x + 2, 2)) + 1) / 2;
            }
        },

        EASE_IN_ELASTIC
        {
            @Override public double f(float x)
            {
                double c4 = (2 * Math.PI) / 3;
                return x == 0 ? 0 : x == 1 ? 1 : -pow(2, 10 * x - 10) * sin((x * 10 - 10.75) * c4);
            }
        },
        EASE_OUT_ELASTIC
        {
            @Override public double f(float x)
            {
                final double c4 = (2 * Math.PI) / 3;
                return x == 0 ? 0 : x == 1 ? 1 : pow(2, -10 * x) * sin((x * 10 - 0.75) * c4) + 1;
            }
        },
        EASE_IN_OUT_ELASTIC
        {
            @Override public double f(float x)
            {
                final double c5 = (2 * Math.PI) / 4.5;
                return x == 0 ? 0
                        : x == 1 ? 1
                        : x < 0.5 ? -(pow(2, 20 * x - 10) * sin((20 * x - 11.125) * c5)) / 2
                        : (pow(2, -20 * x + 10) * sin((20 * x - 11.125) * c5)) / 2 + 1;
            }
        },

        EASE_IN_BOUNCE {@Override public double f(float x) { return 1 - EASE_OUT_BOUNCE.f(1 - x); }},
        EASE_OUT_BOUNCE
        {
            @Override public double f(float x)
            {
                double n1 = 7.5625;
                double d1 = 2.75;

                if (x < 1 / d1)
                    return n1 * x * x;
                else if (x < 2 / d1)
                    return n1 * (x -= 1.5 / d1) * x + 0.75;
                else if (x < 2.5 / d1)
                    return n1 * (x -= 2.25 / d1) * x + 0.9375;
                else
                    return n1 * (x -= 2.625 / d1) * x + 0.984375;
            }
        },
        EASE_IN_OUT_BOUNCE
        {
            @Override public double f(float x)
            {
                return x < 0.5
                        ? (1 - EASE_OUT_BOUNCE.f(1 - 2 * x)) / 2
                        : (1 + EASE_OUT_BOUNCE.f(2 * x - 1)) / 2;
            }
        },
    }

    double f(float value);
}
