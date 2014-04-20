import java.io.IOException;
import java.io.*;

/**
 * Created by jufey on 19.04.14.
 */
public class Editor {

     public static void main (String args[]) {
         int mapsize = 30;
         String s = "";
         try {
             PrintWriter p = new PrintWriter (new FileWriter("/home/jufey/IdeaProjects/Bombing/src/levels/level1.txt"));
             for (int i = 0; i <=mapsize ; i++) {
                for(int j = 0; j<=mapsize;j++){

                    if(((j*50)<200)||((j*50)>1300)){
                        s= i*(50)+"/"+j*(50)+"/50/50/0";
                        p.println(s);
                    }
                    else if (((i*50)<200)||((i*50)>1300))
                    {
                        s= i*(50)+"/"+j*(50)+"/50/50/0";
                        p.println(s);
                    }else{
                        s= i*(50)+"/"+j*(50)+"/50/50/"+myRandomWithHigh(3,4);
                        p.println(s);
                    }

                }
             }
             p.close();
         }
         catch (IOException e) {
             System.out.println("Fehler: "+e.toString());
         }
     }
    public static int myRandomWithHigh(int low, int high) {
        high++;
        return (int) (Math.random() * (high - low) + low);
    }
    }