package vscript.stage;

import processing.core.PApplet;
import vscript.anim.Timeline;

import java.util.HashMap;

/**
 * <p>Creates a new window holder.</p>
 * <strong>Exemple : </strong>
 * <pre>{@code
 *       public class Main extends Window
 *       {
 *          public VScriptWindow() { super(Quality.HD); }

 *          @Override
 *          public void onSetup()
 *          {
 *              addToTimeline("main", new Scene(5000)
 *              {
 *                  public <T extends Window> void displayOn(T win, long time)
 *                  {
 *                      Backgrounds.BACKGROUND_1.displayOn(win);
 *                  }
 *              });
 *          }

 *          @Override
 *          public void onDraw() {  }
 *       }
 *}</pre>

 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public abstract class Window extends PApplet {

    /**
     * @param quality : Example : {@code super(Quality.HD);} will create a window of HD resolution (1280, 720).
     **/
    public Window(Quality quality)
    {
        this.quality = quality;
        timelines = new HashMap<>();
    }



    /**
     * List of available qualities.
     **/
    public enum Quality
    {
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> SD (Standard Definition)</li>
         *     <li><strong>Common Name :</strong> 480p</li>
         *     <li><strong>Aspect Ratio :</strong> 4:3</li>
         *     <li><strong>Pixel Size :</strong> 640 x 480</li>
         * </ul>
         **/
        SD(640, 480),
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> HD (High Definition)</li>
         *     <li><strong>Common Name :</strong> 720p</li>
         *     <li><strong>Aspect Ratio :</strong> 16:9</li>
         *     <li><strong>Pixel Size :</strong> 1280 x 720</li>
         * </ul>
         **/
        HD(1280, 720),
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> FHD (Full HD)</li>
         *     <li><strong>Common Name :</strong> 1080p</li>
         *     <li><strong>Aspect Ratio :</strong> 16:9</li>
         *     <li><strong>Pixel Size :</strong> 1920 x 1080</li>
         * </ul>
         **/
        FHD(1920, 1080),
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> QHD (Quad HD)</li>
         *     <li><strong>Common Name :</strong> 1440p</li>
         *     <li><strong>Aspect Ratio :</strong> 16:9</li>
         *     <li><strong>Pixel Size :</strong> 2560 x 1440</li>
         * </ul>
         **/
        QHD(2560, 1440),
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> 2K</li>
         *     <li><strong>Common Name :</strong> 1080p</li>
         *     <li><strong>Aspect Ratio :</strong> 1:1.77</li>
         *     <li><strong>Pixel Size :</strong> 2048 x 1080</li>
         * </ul>
         **/
        K2(2048, 1080),
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> 4K</li>
         *     <li><strong>Common Name :</strong> 4K or 2160p</li>
         *     <li><strong>Aspect Ratio :</strong> 1:1.9</li>
         *     <li><strong>Pixel Size :</strong> 3840 x 2160</li>
         * </ul>
         **/
        K4(3840, 2160),
        /**
         * <ul>
         *     <li><strong>Resolution Type :</strong> 8K</li>
         *     <li><strong>Common Name :</strong> 8K or 4320p</li>
         *     <li><strong>Aspect Ratio :</strong> 16∶9</li>
         *     <li><strong>Pixel Size :</strong> 7680 x 4320</li>
         * </ul>
         **/
        K8(7680, 4320);

        public final int width, height;

        Quality(int width, int height)
        {
            this.width = width;
            this.height = height;
        }
    }



    /**
     * Stores the time at which the window has been displayed :: instantiation in {@link Window#setup()}.
     * */
    private long timeEntry;


    /**
     * Stores the time at which {@link Window#draw()} has been re-invoked.
     **/
    private long clock;


    /**
     * Window quality.
     * @see Window.Quality
     **/
    private Quality quality;

    /**
     * Returns the current quality.
     **/
    public Quality getQuality()
    {
        return this.quality;
    }

    /**
     * Sets the quality.
     * @see Window.Quality
     **/
    public void setQuality(Quality quality)
    {
        this.quality = quality;
    }


    /**
     * Collection of timelines as a {@code HashMap<String, Timeline>}. You can attribute, for a given timeline, a name.
     **/
    private HashMap<String, Timeline> timelines;

    /**
     * @return a {@link Timeline} from {@link Window#timelines} with a given key.
     **/
    public Timeline getTimeline(String timelineName)
    {
        return timelines.get(timelineName);
    }

    /**
     *
     * Creates a new {@link Timeline}, adds it to {@link Window#timelines} with a reference as {@code timelineName}, and returns it.
     * */
    public Timeline addTimeline(String timelineName)
    {
        Timeline timeline = new Timeline();
        timelines.put(timelineName, timeline);
        return timeline;
    }

    /**
     * If a {@link Timeline} with the reference {@code timelineName} does not exists, it creates a new one with {@link Window#addTimeline(String)},
     * and attack the Scene to it. (Same scene can be part of different timelines).
     * */
    public void addToTimeline(String timelineName, Scene... scenes)
    {
        Timeline timeline = timelines.containsKey(timelineName) ? timelines.get(timelineName) : addTimeline(timelineName);
        timeline.addScenes(scenes);
    }



    /**
     * Set up your {@link Timeline}'s and your {@link Scene}'s in here.
     * */
    public abstract void onSetup();

    /**
     * Always visible scene.
     **/
    public abstract void onDraw();



    /**
     * Do not {@code @Override} this method !
     **/
    public void settings()
    {
        setSize(quality.width, quality.height);
    }

    /**
     * Do not {@code @Override} this method !
     **/
    public void setup()
    {
        timeEntry = System.currentTimeMillis();
        clock = System.currentTimeMillis();
        onSetup();
    }

    /**
     * Do not {@code @Override} this method !
     **/
    public void draw()
    {
        clock = System.currentTimeMillis();
        timelines.values().forEach(timeline -> {
            timeline.onUpdate(timeEntry);
            Scene displayedScene = timeline.getDisplayedScene();
            if (displayedScene != null)
                displayedScene.onDisplay(this, timeEntry);
        });
        onDraw();
    }

    /**
     * <p>Launch the window.</p>
     * <strong>Example :</strong>
     * <pre>{@code
     *      public static void main(String[] args)
     *      {
     *          (new Main()).launch();
     *      }
     * }</pre>
     **/
    public void launch()
    {
        PApplet.main(getClass().getName());
    }
}
