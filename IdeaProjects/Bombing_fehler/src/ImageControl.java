import java.awt.image.BufferedImage;

public class ImageControl{
    BufferedImage[] tiles;
    BufferedImage[] shadow;

    private static ImageControl instance;

    public static ImageControl getInstance(){
        if(instance==null){
            instance = new ImageControl();
        }
        return instance;
    }
    private ImageControl(){
        tiles = null;
    }

    public void setSourceImage(String path,int col, int row){
        SpriteLib lib = SpriteLib.getInstance();
        tiles= lib.getSprite(path,col,row);
    }

    public void setShadowImage(String path,int col, int row){
        SpriteLib lib = SpriteLib.getInstance();
        shadow= lib.getSprite(path,col,row);
    }
    public BufferedImage getImageAt(int num){
        return tiles[num];
    }
    public BufferedImage getShadowImageAt(int num){
        return shadow[num];
    }

    public int getTilesWidth(int n){
        return tiles[n].getWidth();
    }
    public int getTilesHeight(int n){
        return tiles[n].getHeight();
    }

}