package simplex.anim;

import simplex.math.XMath;

/**
 * The {@code Interpolator} is a class, made to create animations by taking a {@link Interpolator#start}, an {@link Interpolator#end},
 * a {@link Interpolator#delta}, and a {@link Interpolator#curve}, to compute the value {@link Interpolator#progress()}.
 *
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public class Interpolator {

    /**
     * Value between {@code 0} and {@code 1}.
     **/
    private float progress;

    /**
     * Value at which the interpolator should start.
     **/
    private final double start;

    /**
     * Value at which the interpolator should end.
     **/
    private final double end;

    /**
     * The increment at which the interpolation should occur. Effectively it sets the speed of the animation.
     * Value between {@code 0} and {@code 1}. (Usually {@code 0.01f}).
     **/
    private final double delta;

    /**
     * Indicates how the animation should behave.
     **/
    private final AnimationCurve curve;


    /**
     * @param start Value at which the interpolator should start.
     * @param end Value at which the interpolator should end.
     * @param delta The increment at which the interpolation should occur. Effectively it sets the speed of the animation.
     *              Value between {@code 0} and {@code 1}. (Usually {@code 0.01f}).
     * @param curve Indicates how the animation should behave.
     *
     * @see AnimationCurve
     **/
    public Interpolator(double start, double end, double delta, AnimationCurve curve)
    {
        this.progress = 0;
        this.start = start;
        this.end = end;
        this.delta = delta;
        this.curve = curve;
    }


    /**
     * Increase the progress of the {@code Interpolator}, by {@link Interpolator#delta}.
     **/
    public void update()
    {
        if (progress < 1)
            progress += delta;
    }

    /**
     * The {@link Interpolator#progress} is then clamped from {@code [0, 1]} to <code>[{@link Interpolator#start}, {@link Interpolator#end}]</code> and returned as corresponded value.
     **/
    public double progress()
    {
        return XMath.Clamp.xFabTcd((float) curve.f(progress), 0, 1, start, end);
    }
}