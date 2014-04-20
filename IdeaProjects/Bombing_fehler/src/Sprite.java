import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by jufey on 18.04.14.
 */
public abstract class Sprite extends Rectangle2D.Double implements Drawable, Movable {
    long delay;

    //GamePanel parent;
    BufferedImage[] pics;
    int currentpic =0;
    long animation = 0;
    protected double dx;
    protected double dy;
    double xpos;
    double ypos;
    Map map;

    int loop_from;
    int loop_to;
    boolean remove = false;

    public Sprite(BufferedImage[] i, double x, double y, long delay,GamePanel p, Map map){
        pics = i;
        this.map = map;
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.width = pics[0].getWidth();
        this.height = pics[0].getHeight();

        loop_from=0;
        loop_to=pics.length-1;

    }

    @Override
    public void drawObjects(Graphics g) {

        g.drawImage(pics[currentpic],(int) x, (int) y, null);
    }

    @Override
    public void doLogic(long delta) {

        animation += (delta/1000000);
        if(animation> delay){
            animation= 0;
            computeAnimation();
        }
    }

    private void computeAnimation(){
        currentpic++;
        if(currentpic>loop_to){
            currentpic = loop_from;
        }
    }
    public void setLoop(int from, int to){
        loop_from= from;
        loop_to= to;
        currentpic=from;
    }



    public abstract boolean collidedWith(Sprite s);

    public void setVerticalSpeed(double d){
        dy =d;
    }
    public void setHorizontalSpeed(double d){
        dx =d;
    }
    public double getVerticalSpeed(){
        return dy;
    }
    public double getHorizontalSpeed(){
        return dx;
    }

    public void Pos(){
            xpos = map.display.getX()+this.getX();
            ypos= map.display.getY()+this.getY();
    }


    public void printPos(){
        System.out.println(xpos+" "+ypos);
        System.out.println(this.getX()+ " "+this.getY());
    }



}