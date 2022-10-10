package vscript.anim;

import vscript.stage.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public class Timeline {

    /**
     * Stores the time at which the displayed scene occurred;
     **/
    private long displayedSceneTimeEntry = 0;

    /**
     * Collection of scenes as an {@link ArrayDeque}<{@link Scene}>.
     **/
    private Queue<Scene> scenes;
    /**
     * Gets the scene that is currently displayed on your screen.
     **/
    public Scene getDisplayedScene() { return scenes.peek(); }


    public Timeline()
    {
        scenes = new ArrayDeque<>();
    }


    /**
     * Compare the {@link Window#clock} and the duration of the displayed scene. If duration expired we {@link ArrayDeque#poll()} the old scene.
     **/
    public void onUpdate(long timeEntry)
    {
        Scene displayedScene = getDisplayedScene();
        if (displayedScene != null) // Do nothing, if the displayedScene is null.
        {
            long sceneDuration = displayedScene.getDuration();
            long now = System.currentTimeMillis();
            if ((now - timeEntry) > (sceneDuration + displayedSceneTimeEntry))
            {
                displayedSceneTimeEntry += sceneDuration;
                scenes.poll();
            }
        }
    }

    /**
     * Adds an array of {@link Scene} to {@code this.}{@link Timeline}.
     **/
    public void addScenes(Scene... scenes) { Arrays.stream(scenes).forEach(this.scenes::offer); }
}