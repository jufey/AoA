import com.sun.java.swing.plaf.windows.resources.windows_it;
import sun.awt.X11.awtImageData;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class MapDisplay extends Rectangle2D.Double {

    private static final long serialVersionUID = 1L;


    Game parent;
    Rectangle2D display;
    double dx = 0;
    double dy = 0;

    Vector<Tile> tiles;
    Map bmap;
    ImageControl control;


    public MapDisplay(GamePanel p, Map bMap) {
        this.bmap = bMap;
        tiles = bmap.giveTile();
        parent = (Game) p;

        control = bmap.control;


        display = new Rectangle2D.Double(0, 0, parent.getWidth(), parent.getHeight());
    }


    public void drawVisibleMap(Graphics g) {
        for (Tile t : tiles) {
            if (t.intersects(display)) {
                double dx = t.x - display.getX();
                double dy = t.y - display.getY();

                g.drawImage((Image) (control.getImageAt(t.getImageNumber())), (int) dx, (int) dy, null);
            }
        }
    }


    public void setVisibleRectangle(Rectangle2D rec) {
        display = rec;
    }

    /*
    public void moveVisibleRectangle(Sprite player) {
        // player.Pos(this);
        x = (int) (player.xpos - display.getWidth());
        y = (int) (player.ypos - display.getHeight());

        //display.setRect(player.getX(),player.getY(),display.getWidth(),display.getHeight());



        if (dx != 0) {
            x = x + (dx * delta / 1e9);
        }
        if (dy != 0) {
            y = y + (dy * delta / 1e9);
        }


        if ((x + display.getWidth()) > width) {
            x = (int) (width - display.getWidth());
        }
        if (x < 0) {
            x = 0;
        }
        if ((y + display.getHeight()) > height) {
            y = (int) (height - display.getHeight());
        }
        if (y < 0) {
            y = 0;
        }
       /* if(atEdge(player)== true){
            player.Pos(this);
            x= (int)player.xpos;
            y= (int)player.ypos;
        }
        frameEndDown = false;
        frameEndLeft = false;
        frameEndRight = false;
        frameEndUp = false;



        //Überprüfung ob Kamera am Kartenrand ist
        if (x < 10) {
            frameEndLeft = true;
            // System.out.println("Links");
        }
        if (y < 10) {
            frameEndUp = true;
            // System.out.println("Oben");
        }
        if (((x + display.getWidth()) > width - 10)) {
            frameEndRight = true;
            //System.out.println("Rechts");
        }
        if ((y + display.getHeight()) > height - 10) {
            frameEndDown = true;
            //System.out.println("Unten");
        }
        if (start) {
            display.setRect(500, 500, display.getWidth(), display.getHeight());
        } else {
            display.setRect(x, y, display.getWidth(), display.getHeight());

        }


    }*/



    public Color getColorForPoint(Point p) {
        for (Tile t : tiles) {

            double dx = t.x - display.getX();
            double dy = t.y - display.getY();

            Rectangle temp2 = new Rectangle((int) dx, (int) dy, (int) t.getWidth(), (int) t.getHeight());
            if (temp2.contains(p)) {
                int px = (int) (p.x - dx);
                int py = (int) (p.y - dy);

                Color c = new Color(ImageControl.getInstance().getShadowImageAt(t.getImageNumber()).getRGB(px, py));
                return c;
            }

        }
        return null;
    }



    public void setVerticalSpeed(double d) {
        dy = d;
    }

    public void setHorizontalSpeed(double d) {
        dx = d;
    }

    public double getVerticalSpeed() {
        return dy;
    }

    public double getHorizontalSpeed() {
        return dx;
    }


}