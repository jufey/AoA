import java.awt.image.BufferedImage;

/**
 * Created by jufey on 18.04.14.
 */
public class Bomb extends Sprite {

    public Bomb(BufferedImage[] i, double x, double y, long delay,GamePanel p,MapDisplay map){
        super(i,x,y,delay,p,map);

    }


    @Override
    public boolean collidedWith(Sprite s) {
        return false;
    }
}
