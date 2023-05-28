import java.awt.*;
import javax.swing.Timer;
 import javax.swing.Timer.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
//import java.util.Timer;

import javax.swing.JPanel;

public class gamePanel extends JPanel  implements ActionListener {
	static final int SCREEN_WIDTH=600;
	static final int SCREEN_HEIGHT=600;
	static final int UNIT_SIZE=25;
	static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY=50;
	static final int x[]=new int[GAME_UNITS];
	static final int y[]=new int[GAME_UNITS];
	int bodyParts=6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction='R';
	boolean running = false;
//	public Timer timer;
	Random random;
	private Timer t;
	
 gamePanel(){
	 random=new Random();
	 this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT ));
	  this.setBackground(Color.black);
	  this.setFocusable(true);
	  this.addKeyListener(new MyKeyAdapter());
	  startGame();
 }
 public void startGame() {
	 newApple();
	 running =true;
	 
	 t = new Timer(DELAY,this);
	 t.start();
 }
 public void paintComponent(Graphics g ) {
	 super.paintComponent(g);
	 draw(g);
 }
 public void newApple() {
	 appleX=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
	 appleY=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	 
 }
 public void draw(Graphics g ) {
	if (running) {
	 for(int i =0;i <SCREEN_HEIGHT/UNIT_SIZE; i ++) {
		 g.drawLine(i*UNIT_SIZE,0, i*UNIT_SIZE, SCREEN_HEIGHT);
		 g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
	 }
	 g.setColor(Color.red);
	 g.fillOval(appleX,appleY, UNIT_SIZE,UNIT_SIZE ); 
	 for(int i =0; i <bodyParts; i ++) {
		 if(i ==0) {
			 g.setColor(Color.green);
			 g.fillRect(x[i], y[i],UNIT_SIZE,UNIT_SIZE);
			 
		 }
		 else {
			 g.setColor( new Color(45,180,0));
			 g.fillRect(x[i], y[i],UNIT_SIZE,UNIT_SIZE);
		 }
		 
		 
	 }
	 g.setColor(Color.red);
	 g.setFont(new Font("INK FREE",Font.BOLD,75));
	 FontMetrics metrics= getFontMetrics(g.getFont());
	 g.drawString("score = "+applesEaten,SCREEN_WIDTH- metrics.stringWidth("score=" +applesEaten )/2, DELAY);
 }
//	else {
//		gameOver(g);
//	}
 }
 public void move() {
	 for(int i = bodyParts; i >0;i --) {
		 x[i]=x[i-1];
		 y[i]=y[i-1];
	 }
	 switch(direction) {
	 case'u':
		 y[0]=y[0]-UNIT_SIZE;
	 break;
	 case 'd':
		 y[0]=y[0]+UNIT_SIZE;
	 break;
	 case 'l':
		 x[0]=x[0]-UNIT_SIZE;
	 break;
	 case 'r':
		 x[0]=x[0]+UNIT_SIZE;
	 break;
	 }
	 }
	 
 
 public void checkApple() {
	 if((x[0]== appleX)&& (y[0]== appleY));
	 bodyParts++;
	 applesEaten++;
	 newApple();
	 
	 
 }
 public void checkCollision() {
	 //head collides with body
	 for(int i =bodyParts;i >0; i ++) {
		 if((x[0]==x[i])&&(y[0]==y[i])) {
			 running= false;
		 }
		 
	 }
	 //head checks the left border
	 if(x[0]<0) {
		 running=false;
		 
	 }
	//head checks the right border
		 if(x[0]<SCREEN_WIDTH) {
			 running=false;
			 
		 }
		//head checks the UP border
		 if(y[0]>SCREEN_HEIGHT) {
			 running=false;
			 
		 }
		 if(y[0]<0) {
			 running=false;
			 
		 }
		 if(!running) {
//		 javax.swing.Timer timer=new javax.swing.Timer(DELAY,this) ;
			 t.stop();
			 
			 
		 }
 }
 public void gameOver(Graphics g ) {
	 g.setColor(Color.red);
	 g.setFont(new Font("INK FREE",Font.BOLD,75));
	 FontMetrics metrics= getFontMetrics(g.getFont());
	 g.drawString("GAME OVER",(SCREEN_WIDTH- metrics.stringWidth("game over"))/2, DELAY);
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkApple();
			checkCollision();
			
		}
		repaint();
		
	}
	//INNER CLASS
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e ) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			if(direction!='r') {
				direction='l';
			}
			break;
			
			case KeyEvent.VK_UP:
				if(direction!='d'  ) {
					direction='u';
				}
				break;

			case KeyEvent.VK_DOWN:
				if(direction!='u'  ) {
					direction='d';
				}
				break;
				
			}
			}
			
		}
		
	}

	


