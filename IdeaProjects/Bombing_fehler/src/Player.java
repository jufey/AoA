/**
 * Created by jufey on 18.04.14.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
public class Player extends Sprite {

    Game parent;
    Map map;


    public Player(BufferedImage[] i, double x, double y, long delay,GamePanel p, Map map){
        super(i,x,y,delay,p,map);
        parent = (Game) p;





    }

    @Override
    public void drawObjects(Graphics g){

        if(remove){
            return;
        }
        super.drawObjects(g);


    }


    @Override
    public void doLogic(long delta){
        super.doLogic(delta);

        //HITBOX !!!!
        // Color col1 = parent.getMap().getColorForPoint(new Point((int)getX(), (int) getY()));
        //  Color col2 = parent.getMap().getColorForPoint(new Point((int)getX()+50, (int) getY()));
        //Color col3 = parent.getMap().getColorForPoint(new Point((int)getX(), (int) getY()+50));
        //Color col4 = parent.getMap().getColorForPoint(new Point((int)getX()+50, (int) getY()+50));

        //checkColor(col1);
        //checkColor(col2);
        //checkColor(col3);
        //checkColor(col4);

    }
    private void checkColor(Color col){
        if (col.equals(Color.blue)){
            //System.out.println("blue");
        }
        if (col.equals(Color.red)){
            //System.out.println("rot");
        }

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
    public void move(long delta) {

        if(dx!=0){
            x+= dx*(delta/1e9);
        }
        if(dy!=0){
            y+= dy*(delta/1e9);
        }

    }

    public void setX(double i){
        x=i;
    }
    public void setY(double i){
        y=i;
    }



}
