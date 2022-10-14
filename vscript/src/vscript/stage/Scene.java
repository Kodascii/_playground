package vscript.stage;

import vscript.util.VFunc;

/**
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 * */
public abstract class Scene {

    /**
     * @param duration duration on the scene in millis.
     **/
    public Scene(long duration)
    {
        this.duration = duration;
    }



    public enum Backgrounds implements VFunc
    {
        BACKGROUND_1 {
            @Override
            public void displayOn(Window win) {
                win.background(200);
                float step = 15.0f; float paddingX = (win.width % step) / 2; float paddingY = (win.height % step) / 2;
                for (float x = paddingX; x <= win.width - paddingX; x+= step)
                    for (float y = paddingY; y <= win.height - paddingY; y+= step)
                    {
                        win.noStroke();
                        win.fill(230);
                        win.circle(x, y, 5);
                    }
            }
        }
    }



    /**
     * Duration of the scene in millis.
     **/
    private long duration;

    /**
     * Gets the scene {@link Scene#duration}.
     **/
    public long getDuration()
    {
        return duration;
    }

    /**
     * Sets the duration of the {@link Scene#duration} in millis, and returns the Scene.
     **/
    public Scene setDuration(long duration)
    {
        this.duration = duration;
        return this;
    }



    /**
     * A scene is {@code abstract} by default, the method {@code onDisplay(Window window, long time)} allows to redefined this method, and draw on {@link Window}.
     * @param window the window you want the scene to be displayed on.
     * @param time refers to {@link Window#timeEntry}.
     **/
    public abstract void onDisplay(Window window, long time);
}
