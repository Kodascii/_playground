package main;


import simplex.anim.AnimationCurve;
import simplex.anim.Interpolator;
import vscript.stage.Scene;
import vscript.stage.Window;


public class VScriptWindowTest extends Window {

    public VScriptWindowTest() { super(Quality.HD); }

    @Override
    public void onSetup()
    {
        addTimeline("main");
        Interpolator interpolator = new Interpolator(300, 600, deltaTime(5000), AnimationCurve.Easing.EASE_OUT_SINE);
        addToTimeline("main", new Scene(5000) {
            @Override
            public <T extends Window> void displayOn(T window, long time) {
                Backgrounds.BACKGROUND_1.displayOn(window);
                interpolator.update();
                drawBullet((float) (200 + interpolator.progress()), 400);
            }
        });
        addToTimeline("main", new Scene(5000) {
            @Override
            public <T extends Window> void displayOn(T window, long time) {
                background(0);
            }
        });

    }

    @Override
    public void onDraw()
    {

    }

    public void drawBullet(float x, float y)
    {
        noStroke();
        fill(30);
        circle(x, y, 20);
    }


    public static void main(String[] args) {
        (new VScriptWindowTest()).launch();
    }
}
