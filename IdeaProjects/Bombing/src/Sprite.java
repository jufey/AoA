import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by jufey on 18.04.14.
 */
public abstract class Sprite extends Rectangle2D.Double implements Drawable, Movable {
    long delay;

    GamePanel parent;
    BufferedImage[] pics;
    int currentpic =0;
    long animation = 0;
    protected double dx;
    protected double dy;

    double totalx;
    double tolaly;

    int loop_from;
    int loop_to;
    boolean remove = false;
    MapDisplay map;

    public Sprite(BufferedImage[] i, double x, double y, long delay, GamePanel p,MapDisplay map){
        pics = i;
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.width = pics[0].getWidth();
        this.height = pics[0].getHeight();
        parent = p;
        loop_from=0;
        loop_to=pics.length-1;
        this.map =map;
    }

    @Override
    public void drawObjects(Graphics g) {

        if(new Rectangle2D.Double(this.x,this.y,50,50).intersects(map.display)){
            double dx = this.x -map.display.getX();
            double dy = this.y -map.display.getY();
            g.drawImage(pics[currentpic], (int) dx, (int) dy, null);

        }




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

    @Override
    public void move(long delta) {

        if(dx!=0){
            x+= dx*(delta/1e9);
        }
        if(dy!=0){
            y+= dy*(delta/1e9);
        }

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

    //public void set
}
