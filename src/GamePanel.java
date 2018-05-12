/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class GamePanel extends Panel implements Runnable, KeyListener {
    public int width;
    public int heigth;
    private Image im;
    private Graphics dbg;
    private Thread gamethread;
    private static final int FPS = 50;
    private Ball myball;
    private Pad mypad;
    private Block myblock;
    
    private int isdead = 3;
    
    public boolean padMoveRight = false;
    public boolean padMoveLeft = false;
    public boolean ballMove = false;
    public boolean gameOver = false;
    public boolean reStart = false;
    
    public JLabel jl4 = new JLabel("0");
	public JLabel jl5 = new JLabel("0");
	public JLabel jl6 = new JLabel("0");
	public JLabel jl9 = new JLabel("0");
	public JLabel jl10 = new JLabel("0");

    public GamePanel() {
        width = 400;
        heigth = 400;
        setBackground(Color.pink);
        setPreferredSize(new Dimension(width, heigth));

        mypad = new Pad(this);
        myblock = new Block(this);
        myball = new Ball(this, mypad, myblock);

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }

    public void stateJfram(){
    	Frame app = new Frame("Gamestate");
    	 app.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });
    	 app.setLocation(600, 100);
         app.setSize(300, 300);
       
         
         
         
         JLabel jl1 = new JLabel("----------当前小球速度  ：");
         JLabel jl2 = new JLabel(" ----------当前挡板长度  ：");
         JLabel jl3 = new JLabel("----------当前挡板速度  ：");
         JLabel jl7 = new JLabel("------------当前剩余生命  ：");
         JLabel jl8 = new JLabel("-------------当前积分  ：");
         JButton jb = new JButton();
         JButton jb2 = new JButton("点击确认即可开始");
         jb.setText("加3条命");
         jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myball.setIsdead(3);
			}
		});
         jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reSetGame();
			}
		});
          jl4 = new JLabel("0");
          jl5 = new JLabel("0");
          jl6 = new JLabel("0");
          jl9 = new JLabel("0");
          jl10 = new JLabel("0");
          
         app.add(jl1);
         app.add(jl4);
         app.add(jl2);
         app.add(jl5);
         app.add(jl3);
         app.add(jl6);
         app.add(jl7);
         app.add(jl9);
         app.add(jl8);
         app.add(jl10);
         app.add(jb);
         app.add(jb2);
         app.setLayout(new FlowLayout());
         app.setVisible(true);
    }
    public void changestate(){
    	//if(myball.getBallmovesize().toString() != null && pad.padweigth.toString() != null && pad.movesize.toString()!=null){
	    	jl4.setText(myball.getBallmovesize().toString());
	    	jl5.setText(mypad.padweigth.toString());
	    	jl6.setText(mypad.movesize.toString());
	    	jl9.setText(myball.getIsdead().toString());
	    	jl10.setText(myball.getInteg().toString());
    	//}
    }
    public void initGame(){
    	
        mypad = new Pad(this);
        myblock = new Block(this);
        myball = new Ball(this, mypad, myblock);

    }
    public void gameStart() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    public void gamePaint() {
        Graphics g;
        try {
            g = this.getGraphics();
            if (g != null && im != null) {
                g.drawImage(im, 0, 0, null);
            }
            g.dispose();
        } catch (Exception e) {
        }
    }

    public void gameRender() {
        if (im == null) {
            im = createImage(width, heigth);
            //im.getGraphics().drawString("ssds", width, heigth);
            if (im == null) {
                System.out.println("im is null");
            } else {
                dbg = im.getGraphics();
            }
        }
        
        dbg.setColor(Color.white);
        dbg.fillRect(0, 0, width, heigth);
        myblock.draw(dbg);
        myball.draw(dbg);
        mypad.draw(dbg);
    }

    public void gameUpdate() {
        isGameOver();
        if (reStart) {
            reSetGame();
            reStart = false;
            gameOver = false;
            isdead=isdead-1;
            ballMove = false;
           
        }
        if (!gameOver) {
               mypad.update();
               myblock.update();
               
               //System.out.println(isdead);
              
               myball.update();

        }

    }

    public void isGameOver() {
        int countBlock = 0;
        for (int i = 0; i < myblock.num; i++) {
            if (myblock.exist[i] == true) {
                countBlock++;
            }
        }
        if (countBlock == 0) {
            gameOver = true;
            
        }
        
    }
    

    public void run() {
        long t1,t2,dt,sleepTime;  
        long period=1000/FPS;  
        t1=System.nanoTime(); 

        while (true) {
        	changestate();
            gameUpdate();
            gameRender();
            gamePaint();
            
            t2= System.nanoTime() ; 
            dt=(t2-t1)/1000000L; 
            sleepTime = period - dt;
            if(sleepTime<=0)        
                  sleepTime=2;
            try {     
            Thread.sleep(sleepTime); 
           } catch (InterruptedException ex) { }
              t1 = System.nanoTime();  
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_RIGHT) {
            padMoveRight = true;
        }
        if (keycode == KeyEvent.VK_LEFT) {
            padMoveLeft = true;
        }
        if (keycode == KeyEvent.VK_SPACE) {
            ballMove = true;
        }
        if (keycode == KeyEvent.VK_R) {
            if (gameOver) {
            	/*System.out.println("关卡+1");
            	myball.setBallmovesize(20);
                myball.setDx(20);
                myball.setDy(20);
                mypad.padweigth -= 20;
                mypad.initpad();
                initGame();
                System.out.println(myball.getBallmovesize() +" "+mypad.padweigth);*/
                reStart = true;

            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_RIGHT) {
            padMoveRight = false;
        }
        if (keycode == KeyEvent.VK_LEFT) {
            padMoveLeft = false;
        }
    }

    private void reSetGame() {
        mypad = new Pad(this);
        myblock = new Block(this);
        myball = new Ball(this, mypad, myblock);

    }
}

