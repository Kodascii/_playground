package simplex.anim;

import simplex.math.XMath;

/**
 * The {@code Interpolator} is a class, made to create animations by taking a {@link Interpolator#start}, an {@link Interpolator#end},
 * a {@link Interpolator#deltaOrDuration}, and a {@link Interpolator#curve}, to compute the value {@link Interpolator#progress()}.
 *
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public class Interpolator {

    /**
     * @param start Value at which the interpolator should start.
     * @param end Value at which the interpolator should end.
     * @param deltaOrDuration The increment at which the interpolation should occur.
     *      <ul>
     *          <li>If {@code |deltaOrDuration| < 1}, effectively it sets the speed of the animation. Value between {@code 0} and {@code 1}. (Usually {@code 0.01f}).</li>
     *          <li>Otherwise, the variable represent the duration of the animation (in millis).</li>
     *      </ul>
     * @param curve Indicates how the animation should behave.
     *
     * @see AnimationCurve
     **/
    public Interpolator(double start, double end, double deltaOrDuration, AnimationCurve curve)
    {
        this.progress = 0;
        this.start = start;
        this.end = end;
        this.deltaOrDuration = deltaOrDuration;
        this.curve = curve;
    }



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
     * <p>The increment at which the interpolation should occur.</p>
     * <ul>
     *     <li>If {@code |deltaOrDuration| < 1}, effectively it sets the speed of the animation. Value between {@code 0} and {@code 1}. (Usually {@code 0.01f}).</li>
     *     <li>Otherwise, the variable represent the duration of the animation (in millis).</li>
     * </ul>
     *
     *
     **/
    private final double deltaOrDuration;


    /**
     * Indicates how the animation should behave.
     **/
    private final AnimationCurve curve;


    /**
     * Stores the time at which the interpolator has been instantiated.
     **/
    private long interpolatorTimeEntry = System.currentTimeMillis();


    /**
     * Stores the number of times, interpolator already repeated.
     **/
    private int repeater = 1;

    /**
     * Stores the number of times, the interpolator should replay the animation.
     **/
    private int repeat = 1;

    /**
     * Sets the number of times, the interpolator should repeat.
     **/
    public void repeat(int repeat)
    {
        this.repeat = repeat;
    }



    /**
     * Increase the progress of the {@code Interpolator}, by {@link Interpolator#deltaOrDuration}.
     **/
    public void update()
    {
        if (progress < 1) // will halt once progress >= 1
            if (StrictMath.abs(deltaOrDuration) < 1) // if progress acts as a delta
            {
                progress += deltaOrDuration;
            }
            else // if progress acts as a duration
            {
                long timeProgress = (System.currentTimeMillis() - interpolatorTimeEntry);
                progress = (float) XMath.clamp(timeProgress, 0, deltaOrDuration, 0, 1.0f);
            }
        else if (repeater < repeat)
        {
            interpolatorTimeEntry = System.currentTimeMillis();
            progress = 0;
            repeater++;
        }
    }

    /**
     * The {@link Interpolator#progress} is then clamped from {@code [0, 1]} to <code>[{@link Interpolator#start}, {@link Interpolator#end}]</code> and returned as corresponded value.
     **/
    public float progress()
    {
        return (float) XMath.clamp((float) curve.f(progress), 0, 1, start, end);
    }
}