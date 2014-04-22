import com.sun.java.swing.plaf.windows.resources.windows_it;
import sun.awt.X11.awtImageData;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class MapDisplay extends Rectangle {

    private static final long serialVersionUID = 1L;

    ImageControl control;
    SpriteLib lib;
    Vector<Tile> tiles;

    Game parent;
    Rectangle2D display;
    double dx = 0;
    double dy = 0;

    boolean frameEndUp=false;
    boolean frameEndDown=false;
    boolean frameEndLeft=false;
    boolean frameEndRight=false;

    public MapDisplay(String level, String picpath,String shadowpath, int col, int row, GamePanel p){
        tiles = new Vector<Tile>();

        parent = (Game) p;

        loadLevelData(level);

        control = ImageControl.getInstance();
        control.setSourceImage(picpath,col,row);
        control.setShadowImage(shadowpath,col,row);
        display = new Rectangle2D.Double(0,0,parent.getWidth(),parent.getHeight());

    }

    private  void loadLevelData(String level){
        try{
            InputStreamReader isr =  new InputStreamReader(getClass().getClassLoader().getResourceAsStream(level));
            BufferedReader bufread = new BufferedReader(isr);

            String line = null;

            do{
                line = bufread.readLine();

                if(line ==null){
                    continue;
                }

                String[] split = line.split("/");

                int posx = Integer.parseInt(split[0]);
                int posy = Integer.parseInt(split[1]);
                int width = Integer.parseInt(split[2]);
                int height = Integer.parseInt(split[3]);
                int num = Integer.parseInt(split[4]);

                if((width + posx)> this.width){
                    this.width = posx+ width;
                }
                if((height + posy)> this.height){
                    this.height = posy+ height;
                }

                Tile t = new Tile(posx,posy,width, height,num);
                tiles.add(t);


            }while(line != null);

            bufread.close();
            isr.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void drawVisibleMap(Graphics g){
        for(Tile t:tiles){
            if(t.intersects(display)){
                double dx = t.x -display.getX();
                double dy = t.y -display.getY();

                g.drawImage((Image)(control.getImageAt(t.getImageNumber())),(int)dx,(int)dy,null);
            }
        }

    }

    public void setVisibleRectangle(Rectangle2D rec){

        display = rec;
    }

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

    public void moveVisibleRectangle(long delta){

        double x = display.getX();
        double y = display.getY();

        if(dx!=0){
            x= x+ (dx*delta/1e9);
        }
        if(dy!=0){
            y= y+(dy*delta/1e9);
        }
        if((x+display.getWidth())>width){
            x= width -display.getWidth();
        }
        if(x<0){
            x=0;

        }
        if((y+display.getHeight())>height){
            y= height -display.getHeight();
        }
        if(y<0){
            y=0;

        }
        //Ckeck FrameEnds

        if(x<5){
            frameEndLeft=true;
            //System.out.println("Links");
        }
        if(y<5){
            frameEndUp=true;
           // System.out.println("Oben");
        }
        if((x+display.getWidth())> width-5){
            frameEndRight=true;
            //System.out.println("Rechts");
        }
        if((y+display.getHeight())> height-5){
            frameEndDown=true;
            //System.out.println("Unten");
        }
        display.setRect(x,y,display.getWidth(),display.getHeight());

    }


    public Color getColorForPoint(Point p){
        for(Tile t:tiles){
            double dx = t.x - display.getX();
            double dy = t.y - display.getY();

            Rectangle temp2 = new Rectangle((int) dx,(int)dy,(int)t.getWidth(),(int)t.getHeight());

            if(temp2.contains(p)){
                int px = (int) (p.x -dx);
                int py = (int) (p.y -dy);

                Color c = new Color(ImageControl.getInstance().getShadowImageAt(t.getImageNumber()).getRGB(px,py));
                return c;
            }
        }
        return null;
    }
    public boolean isFrameEndUp(){
        return frameEndUp;
    }
    public boolean isFrameEndDown(){
        return frameEndDown;
    }
    public boolean isFrameEndLeft(){
        return frameEndLeft;
    }
    public boolean isFrameEndRight(){
        return frameEndRight;
    }
    public void setFrameEndFalse(){
        frameEndDown=false;
        frameEndUp=false;
        frameEndLeft= false;
        frameEndRight=false;

    }

    public boolean moveArea(Player player){
        double dx = (display.getWidth()/2) -display.getX()+26;
        double dy = (display.getHeight()/2) -display.getY()+26;

        Rectangle2D area = new Rectangle2D.Double((int)dx,(int)dy,(int)(getWidth()-display.getWidth()-52),(int)(getHeight()-display.getHeight()-52));

        if(area.intersects(player)){
            return true;
        }
        else{
            return false;
        }


    }


}