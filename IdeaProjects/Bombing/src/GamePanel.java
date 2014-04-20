import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.net.URL;


import javax.imageio.ImageIO;
import javax.swing.*;

public abstract class GamePanel extends Canvas implements Runnable,KeyListener, ActionListener {

    private static final long serialVersionUID = 1L;
    protected boolean game_running = true;
    //0 !started, 1 started
    protected int game_status =0;


    protected boolean once = false;

    protected long delta=0;
    protected long last=0;
    protected long fps =0;
    protected long gameover =0;

    protected boolean up = false;
    protected boolean down = false;
    protected boolean left = false;
    protected boolean right = false;

    VolatileImage backbuffer;
    GraphicsEnvironment ge;
    GraphicsConfiguration gc;



    BufferStrategy strategy;

    Timer timer;



    public GamePanel(int w, int h) {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

        this.setPreferredSize(new Dimension(w, h));

        Frame frame = new JFrame("Window");
        frame.setLocation(300, 100);

        frame.addKeyListener(this);
        frame.add(this);
        frame.pack();
        frame.setIgnoreRepaint(true);
        frame.setVisible(true);

        createBufferStrategy(2);
        strategy = getBufferStrategy();
        doInitializations();
    }

    protected void doInitializations() {

        createBackpuffer();

        timer = new Timer(3000,this);
        timer.start();

        gameover = 0;
        last= System.nanoTime();

        if (!once){
            once = true;
            Thread t = new Thread(this);
            t.start();
        }
    }

    private void doPainting(){
        checkBuffer();

        Graphics g = backbuffer.getGraphics();
        render(g);
        g.dispose();

        Graphics g2 = strategy.getDrawGraphics();
        g2.drawImage(backbuffer,0,0,this);
        g2.dispose();

        strategy.show();
    }

    private void checkBuffer(){
        if(backbuffer == null){
            createBackpuffer();
        }
        if(backbuffer.validate(gc)== VolatileImage.IMAGE_INCOMPATIBLE){
            createBackpuffer();
        }
    }


    private void createBackpuffer(){
        if(backbuffer!=null){
            backbuffer.flush();
            backbuffer = null;
        }
        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
        backbuffer = gc.createCompatibleVolatileImage(getWidth(),getHeight());
    }

    public void run() {
        while (game_running) {

            computeDelta();

            if(getGame_status()==1){
                checkKeys();
                doLogic();
                moveObjects();
            }


            doPainting();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }

        }
    }

    protected abstract void checkKeys();
    protected abstract void doLogic();
    protected abstract void moveObjects();
    protected abstract void render(Graphics g);

    protected void computeDelta(){
        delta = System.nanoTime()-last;
        last = System.nanoTime();

        fps =((long) 1e9)/delta;
    }

    protected BufferedImage[] loadPics(String path, int pics){
        BufferedImage[] anim = new BufferedImage[pics];
        BufferedImage source = null;

        URL pic_url =  getClass().getClassLoader().getResource(path);

        try{
            source= ImageIO.read(pic_url);
        }
        catch(IOException e){}

        for(int x=0;x<pics;x++){
            anim[x]= source.getSubimage(x*source.getWidth()/pics,0,source.getWidth()/pics,source.getHeight());

        }
        return anim;
    }

    public int getGame_status(){
        return game_status;
    }

    public void setGame_status(int i){
        game_status= i;
    }

    protected void stopGame(){
        setGame_status(0);
    }


}
