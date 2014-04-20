import com.sun.java.swing.plaf.windows.resources.windows_it;
import sun.awt.X11.awtImageData;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Map extends Rectangle {

    private static final long serialVersionUID = 1L;

    ImageControl control;
    Vector<Tile> tiles;
    Vector<Sprite> sprites;
    Game parent;
    Rectangle2D display;




    public Map(String level, String picpath,int col, int row, GamePanel p) {
        tiles = new Vector<Tile>();
        sprites = new Vector<Sprite>();
        parent = (Game) p;

        loadLevelData(level+".txt");


        control = ImageControl.getInstance();
        control.setSourceImage(picpath+".gif", col, row);
        control.setShadowImage(picpath+"shadow.gif", col, row);

    }

    private void loadLevelData(String level) {
        try {
            InputStreamReader isr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(level));
            BufferedReader bufread = new BufferedReader(isr);

            String line = null;

            do {
                line = bufread.readLine();

                if (line == null) {
                    continue;
                }

                String[] split = line.split("/");

                int posx = Integer.parseInt(split[0]);
                int posy = Integer.parseInt(split[1]);
                int width = Integer.parseInt(split[2]);
                int height = Integer.parseInt(split[3]);
                int num = Integer.parseInt(split[4]);

                if ((width + posx) > this.width) {
                    this.width = posx + width;
                }
                if ((height + posy) > this.height) {
                    this.height = posy + height;
                }

                Tile t = new Tile(posx, posy, width, height, num);
                tiles.add(t);



            } while (line != null);

            bufread.close();
            isr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Vector<Tile> giveTile(){
        if(tiles.size()!= 0){
            return tiles;
        }
        return null;
    }
}