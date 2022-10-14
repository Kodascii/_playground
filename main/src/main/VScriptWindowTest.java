package main;


import simplex.math.Vector;
import simplex.math.XMath;
import vscript.stage.Window;


public class VScriptWindowTest extends Window {

    public VScriptWindowTest() { super(Quality.HD); }

    @Override
    public void onSetup()
    {
        Vector vector1 = new Vector(1, 2);
        Vector vector2 = new Vector(1, 5, 7);
        Vector vector3 = new Vector(-2, 1);

        Vector sum = Vector.add(vector1, vector2, vector3);
        System.out.println(sum);
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


    public static void main(String[] args)
    {
        (new VScriptWindowTest()).launch();
    }
}
