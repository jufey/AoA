import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

/**
 * Created by jufey on 18.04.14.
 */
public class Game extends GamePanel {

    private static final long serialVersion = 1L;

    SpriteLib lib;
    Player player;
    MapDisplay map;
    Vector<Sprite> actors;
    Bomb test;


    int vspeed=100;
    int hspeed =100;


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


        map = new MapDisplay("levels/level1.txt","pics/tiles.gif",3,4,this);
        map.setVisibleRectangle(new Rectangle2D.Double(500, 500,getWidth(), getHeight()));

        player = new Player(lib.getSprite("pics/Bomber.gif",4,1),375,275,100,this,map);
        test = new Bomb(lib.getSprite("pics/heli.gif", 4, 1), 700, 700, 100, this,map);
        //actors.add(player);
    }

    public void render(Graphics g){
        if(getGame_status()==1){
            map.drawVisibleMap(g);


            player.drawObjects(g);
            test.drawObjects(g);



        }else{
            g.setColor(Color.red);
            g.drawString("Press Enter",50,50);

        }



    }



    @Override
    protected void doLogic() {
        player.doLogic(delta);
        test.doLogic(delta);


    }

    @Override
    protected void moveObjects() {

        map.moveVisibleRectangle(delta);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    protected void checkKeys() {
        if(left){
            map.setHorizontalSpeed(-hspeed);
        }
        if(right){
            map.setHorizontalSpeed(hspeed);
        }
        if(!left && !right){
            map.setHorizontalSpeed(0);
        }

        if(up){
            map.setVerticalSpeed(-vspeed);
        }
        if(down){
            map.setVerticalSpeed(vspeed);
        }
        if(!up && !down){
            map.setVerticalSpeed(0);
        }

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
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(getGame_status()==1){
                stopGame();
            }else{
                stopGame();
                System.exit(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_P){

        }
    }
}
