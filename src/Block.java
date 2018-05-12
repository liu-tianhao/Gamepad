
import java.awt.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class Block {

    private GamePanel gameP;    
    private Point allocation;
    
    private int check = 2; //碰撞消失次数
    
    public Point location[];
    public Point size;
    public int num;
    public boolean exist[];
    
    public int map[]; //存剩余生命的数组
    public Integer precious[];//存宝箱状态的数组
    
    public Block(GamePanel gp) {
        gameP = gp;
        num = 20;
        allocation = new Point(4, 5);
        size = new Point(50, 20);
        location = new Point[num];
        for (int i = 0; i < allocation.y; i++) {
            for (int j = 0; j < allocation.x; j++) {
                location[i * allocation.x + j] = new Point((gp.width / allocation.x - size.x) / 2 + j * (gp.width / allocation.x), (i * 2 + 1) * size.y);
                System.out.println(location[i * allocation.x + j]);
                
            }
        }
        exist = new boolean[num];     //
        map = new int[num];
        precious = new Integer[num];
        for (int i = 0; i < num; i++) {
        	map[i] = check;
        	precious[i] = (int) (Math.random()*6);
            exist[i] = true;
        }
    }

    public void update() {
    }

    public void draw(Graphics g) {
    	for(int i = 0 ;i<precious.length;i++){
    		
    		
    		switch (precious[i]) {
			case 0:g.setColor(Color.red);break;
			case 1:g.setColor(Color.orange);break;
			case 2:g.setColor(Color.yellow);break;
			case 3:g.setColor(Color.green);break;
			case 4:g.setColor(Color.blue);break;
			case 5:g.setColor(Color.pink);break;
			
			}
    	}
        
        for (int i = 0; i < num; i++) {
            if (exist[i] == true) {
                g.fillRect(location[i].x, location[i].y, size.x, size.y);
            }
        }
    }
}
