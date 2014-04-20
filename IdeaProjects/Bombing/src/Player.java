/**
 * Created by jufey on 18.04.14.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
public class Player extends Sprite {

    Game parent;
    public Player(BufferedImage[] i, double x, double y, long delay,GamePanel p,MapDisplay map){
        super(i,x,y,delay,p,map);
        parent = (Game) p;

    }
    @Override
    public void doLogic(long delta){
        super.doLogic(delta);
    }

    @Override
    public boolean collidedWith(Sprite s) {
        if(this.intersects(s)){
            System.out.println("collidedWith Player");
            return true;
        }
        return false;
    }
    @Override
    public void drawObjects(Graphics g) {

        g.drawImage(pics[currentpic],(int) x, (int) y, null);


    }

    public void setX(double i){
        x=i;
    }
    public void setY(double i){
        y=i;
    }

}
