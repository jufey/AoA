import java.awt.*;
/**
 * Created by jufey on 19.04.14.
 */
public class Tile extends Rectangle {
    private static final long serialVersionUID = 1L;
    int image_num;
    ImageControl control;

    public Tile(int x, int y, int width, int height, int num){
        super(x,y,width,height);
        image_num = num;
        control = ImageControl.getInstance();
    }
    public int getImageNumber(){
        return image_num;
    }
    /*public void drawTile(Graphics g){
        g.drawImage(control.getImageAt(image_num),x,y,null);
    }*/

}
