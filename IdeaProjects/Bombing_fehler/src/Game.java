import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by jufey on 18.04.14.
 */
public class Game extends GamePanel {

    private static final long serialVersion = 1L;

    SpriteLib lib;
    Map map;
    MapDisplay camera;




    public static void main(String[] args){
        new Game(800,600);
    }


    public Game(int w, int h) {
        super(w, h);
    }

    @Override
    protected void doInitializations(){
        super.doInitializations();

        lib = SpriteLib.getInstance();
        map = new Map("levels/level1","pics/tiles",4,3,this);
        camera = new MapDisplay(this, map);





    }

    public void render(Graphics g){
        if(getGame_status()==1){
            camera.drawVisibleMap(g);
           // player.drawObjects(g);

        }else{
            g.setColor(Color.red);

            g.drawString("Press Enter",50,50);

        }
    }


    @Override
    protected void checkKeys() {
        if(left){
           // map.setHorizontalSpeed(-speed);
           // if(map.isFrameEndLeft()== true){
           //     player.setHorizontalSpeed(-speed);
            //}

        }
        if(right){
           // map.setHorizontalSpeed(speed);
           // if(map.isFrameEndRight()== true){
              //  player.setHorizontalSpeed(speed);
           // }

        }
        if(!left && !right){
           // map.setHorizontalSpeed(0);
            // player.setHorizontalSpeed(0);

        }

        if(up){
           // map.setVerticalSpeed(-speed);
           // if(map.isFrameEndUp()== true){
            //   player.setVerticalSpeed(-speed);
           // }

        }
        if(down){
           // map.setVerticalSpeed(speed);
           // if(map.isFrameEndDown()== true){
            //   player.setVerticalSpeed(speed);
            //}

        }
        if(!up && !down){
           // map.setVerticalSpeed(0);
            // player.setVerticalSpeed(0);

        }

    }

    @Override
    protected void doLogic() {
        // player.doLogic(delta);
    }

    @Override
    protected void moveObjects() {
        //player.Pos(map);
        //player.move(delta);
        // bmap.isStart(false);
       // map.moveVisibleRectangle(player);



    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode()==KeyEvent.VK_UP){
            up= true;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            down= true;
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            right= true;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            left= true;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            //  speed = 200;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode()==KeyEvent.VK_UP){
            up= false;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            down= false;
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            right= false;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            left= false;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(getGame_status()==0){
                doInitializations();
                setGame_status(1);

            }
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            //  speed = 100;
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(getGame_status()==1){
                stopGame();
            }else{
                stopGame();
                System.exit(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_O){
            //map.moveVisibleRectangle(player);

            //System.out.println("Display stp: "+map.display.getX()+" "+map.display.getY() );
            //   player.printPos();
          //  map.moveRecTo(player);


        }
    }
    ////public MapDisplay getCam(){
        //  return cam;
    //}
}
