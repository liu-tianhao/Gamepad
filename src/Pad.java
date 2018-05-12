
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class Pad {

    public Point location;
    public Point size;
    private GamePanel gameP;
    public Integer movesize = 10; //按键移动像素
    public Integer padweigth = 100; //木板长度
    public Pad(GamePanel gp) {
       gameP = gp;
       size = new Point(padweigth, 20);
       location = new Point((gp.width - size.x) / 2, gp.heigth - size.y);
    }
    public void initpad(){
        size = new Point(padweigth, 20); //更改木板长度
    }
    public void update() {
    	initpad();
        if (gameP.padMoveRight) {
            if (location.x + size.x < gameP.width) {
                location.x += movesize;
            }
        }
        if (gameP.padMoveLeft) {
            if (location.x > 0) {
                location.x -= movesize;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(location.x, location.y, size.x, size.y,10,10);
        
    }
}
