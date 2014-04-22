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
    Bomb test2;
    Rectangle2D moveArea;


    int vspeed = 100;
    int hspeed = 100;


    public static void main(String[] args) {
        new Game(800, 600);
    }


    public Game(int w, int h) {
        super(w, h);
    }

    @Override
    protected void doInitializations() {
        super.doInitializations();

        lib = SpriteLib.getInstance();
        actors = new Vector<Sprite>();


        map = new MapDisplay("levels/level1.txt", "pics/tiles1.gif", "pics/tilesshadow1.gif", 3, 4, this);
        map.setVisibleRectangle(new Rectangle2D.Double(500, 500, getWidth(), getHeight()));

        player = new Player(lib.getSprite("pics/Bomber.gif", 4, 1), 375, 275, 100, this, map);

        test = new Bomb(lib.getSprite("pics/Bomber.gif", 4, 1), 700, 700, 100, this, map);
        test2 = new Bomb(lib.getSprite("pics/Bomber.gif", 4, 1), 400, 500, 100, this, map);
        test.setLoop(0,1);
        test2.setLoop(2,3);
        actors.add(test);
        actors.add((test2));
        moveArea = new Rectangle2D.Double(500, 500, 100, 100);

    }

    public void render(Graphics g) {
        if (getGame_status() == 1) {
            map.drawVisibleMap(g);


            for (Drawable dra : actors) {
                dra.drawObjects(g);
            }
            player.drawObjects(g);

        } else {

            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 600 );
           
            g.setColor(Color.white);
            g.drawString("Press Enter", 370, 300);

        }


    }


    @Override
    protected void doLogic() {
        for (Movable mov : actors) {
            mov.doLogic(delta);
        }

        player.doLogic(delta);


    }

    @Override
    protected void moveObjects() {

        map.moveVisibleRectangle(delta);
        player.move(delta);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    protected void checkKeys() {
        if (left) {
            if (player.isMidHorizontal()) {
                map.setHorizontalSpeed(-hspeed);
            }
            if (map.isFrameEndLeft()) {
                player.setHorizontalSpeed(-hspeed);
            }
            if (map.isFrameEndRight()) {
                player.setHorizontalSpeed(-hspeed);
                if (map.moveArea(player)) {
                    player.setHorizontalSpeed(0);
                }
            }
        }
        //Funktioniert !!!!
        if (right) {
            if (player.isMidHorizontal()) {
                map.setHorizontalSpeed(hspeed);
            }
            if (map.isFrameEndRight()) {
                player.setHorizontalSpeed(hspeed);
            }
            if (map.isFrameEndLeft()) {
                player.setHorizontalSpeed(hspeed);
                if (map.moveArea(player)) {
                    player.setHorizontalSpeed(0);
                }
            }
        }

        if (!left && !right) {
            map.setHorizontalSpeed(0);
            player.setHorizontalSpeed(0);
        }

        if (up) {
            if (player.isMidVertical()) {
                map.setVerticalSpeed(-vspeed);

            }
            if (map.isFrameEndUp()) {
                player.setVerticalSpeed(-vspeed);
            }
            if (map.isFrameEndDown()) {
                player.setVerticalSpeed(-vspeed);
                if (map.moveArea(player)) {
                    player.setVerticalSpeed(0);
                }
            }

        }
        if (down) {
            if (player.isMidVertical()) {
                map.setVerticalSpeed(vspeed);
            }
            if (map.isFrameEndDown()) {
                player.setVerticalSpeed(vspeed);
            }
            if (map.isFrameEndUp()) {
                player.setVerticalSpeed(vspeed);
                if (map.moveArea(player)) {
                    player.setVerticalSpeed(0);
                }
            }

        }
        if (!up && !down) {

            map.setVerticalSpeed(0);
            player.setVerticalSpeed(0);

        }
        map.setFrameEndFalse();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(map.moveArea(player)){
                hspeed=200;
                vspeed=200;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (getGame_status() == 0) {
                doInitializations();
                setGame_status(1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (getGame_status() == 1) {
                stopGame();
            } else {
                exitGame();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            player.printPos();
            System.out.println(map.moveArea(player));
            System.out.println(player.isMidVertical());

        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            if(map.moveArea(player)){
                player.setX(375);
                player.setY(275);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(map.moveArea(player)){
                hspeed=100;
                vspeed=100;
            }
        }
    }

    public MapDisplay getMap() {
        return map;
    }
}
