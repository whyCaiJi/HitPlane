/**
 * Main PlaneGame Frame
 * Author: Wu Heyang
 * Date: 28-Jul-2016
 * OS: Windows 10
 * Version: 1.0
 */

package heyang.plane;

import heyang.util.Constant;
import heyang.util.GameUtil;
import heyang.util.MyFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

public class PlaneGameFrame extends MyFrame{
	// Loading Pictures
	Image bg = GameUtil.getImage("image/background.png");
	Image losingPic = GameUtil.getImage("image/endPic.jpg");
	Image winningPic = GameUtil.getImage("image/winningPic.jpg");
	//Image fuLiPic = GameUtil.getImage("image/FuLi.jpg");
	Image explosion[] = new Image[Constant.EXPLOSION_NO];
	
	// Declare static variables and objects
	static Plane p;
	static ArrayList bulletList;
	static Date startTime;
	static boolean gameEnd;
	static Explode bao;
	
	Date endTime, currentTime;
	long timeElapsed;
	
	// Override the paint method
	public void paint(Graphics g){
		g.drawImage(bg,0,0,null);
		p.draw(g);
		currentTime = new Date();	// Updating time counter
		
		// for loop to draw all the bullets
		for(int i = 0;i < bulletList.size();i++){
			Bullet b = (Bullet)bulletList.get(i);
			b.draw(g);
			
			// Check for collision
			boolean collision = b.getRect().intersects(p.getRect());
			if(collision){
				// Mark the plane as dead after collision
				p.setLive(false);
				
				// If statement to update end game
				if(!gameEnd){
					endTime = new Date();
					
					// If statement to initialize an explosion
					if(bao == null){
						bao = new Explode(p.x,p.y);
					}// end inner if
				}// end outside if
				gameEnd = true;
			}// end outside most if
		}// end for
		
		if(bao != null){
			bao.draw(g, explosion);
		}
		
		// Calculating the survival time
		timeElapsed = (currentTime.getTime() - startTime.getTime())/1000;
		
		// If statement to print time and other info
		if(p.isLive())
		{
			// The in game timer is only available when the plane is alive
			printInfo(g,"Time elapsed: "+timeElapsed+"s",Color.white,15,350,50);
		}else if(!p.isLive() && Explode.count == Constant.EXPLOSION_NO){	// end if and start else
			// Game over screen after collision
			printInfo(g,"GAME OVER",Color.white,25,Constant.GAME_WIDTH/2-80,Constant.GAME_HEIGHT/2-20);
			long period = (endTime.getTime() - startTime.getTime())/1000;
			
			// Not a man
			if(period < 30){
				printInfo(g,"Survive Time: "+period+"sec",Color.yellow,15,Constant.GAME_WIDTH/2-65,Constant.GAME_HEIGHT/2);
				g.drawImage(losingPic, Constant.GAME_WIDTH/2-50, Constant.GAME_HEIGHT/2+10, null);
				printInfo(g,"Press R to retry",Color.white,15,Constant.GAME_WIDTH/2-53,Constant.GAME_HEIGHT/2+130);
			}else {
				// Is a man
				printInfo(g,"Survive Time: "+period+"sec",Color.yellow,15,Constant.GAME_WIDTH/2-65,Constant.GAME_HEIGHT/2);
				printInfo(g,"You are a true bloody YeMen!",Color.white,20,Constant.GAME_WIDTH/2-120,Constant.GAME_HEIGHT/2+170);
				g.drawImage(winningPic, Constant.GAME_WIDTH/2-50, Constant.GAME_HEIGHT/2+10, null);
				printInfo(g,"Press R to retry",Color.white,15,Constant.GAME_WIDTH/2-53,Constant.GAME_HEIGHT/2+193);
			}
		}// end else
	}// end paint()
	
	// Method to print info on the screen
	public void printInfo(Graphics g, String str, Color color, int size, int x, int y){
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("Times New Roman",Font.BOLD,size);
		g.setFont(f);
		g.drawString(str,x,y);
		g.setColor(c);
	}// end printInfo()

	// Main method to launch the frame
	public static void main(String[] args){
		new PlaneGameFrame().launchFrame();
	}// end main()
	
	// Override launchFrame()
	public void launchFrame(){
		// Loading explosion image array
		for(int i = 0;i < Constant.EXPLOSION_NO;i++){
			explosion[i] = GameUtil.getImage("image/explode/e"+(i+1)+".png");
			explosion[i].getWidth(null);
		}
		super.launchFrame();
		addKeyListener(new KeyMonitor());
		initialize();
	}// end launchFrame()
	
	// Define internal class
	class KeyMonitor extends KeyAdapter {
		// Override keyPressed
		public void keyPressed(KeyEvent e) {
			p.changeDirection(e);
			if(gameEnd){
				p.reset(e);
			}// end if
		}// end keyPressed()

		// Override keyReleased
		public void keyReleased(KeyEvent e) {
			p.stopDirection(e);
		}// end keyReleased()
	}// end KeyMonitor
	
	// Initialize method to start and restart
	public static void initialize(){
		// Declare start time and bulletList
		startTime = new Date();
		bulletList = new ArrayList();
		
		// generate bullets
		for(int i = 0;i < Constant.BULLET_NO;i++){
			Bullet b = new Bullet();
			bulletList.add(b);
		}// end for
		
		// Generate new plane and initialize game status
		p = new Plane("image/Earth.jpg",Constant.GAME_WIDTH/2-16,Constant.GAME_HEIGHT/2-16,5);
		gameEnd = false;
		bao = null;
		Explode.count = 0;
	}// end initialize()
}// end PlaneGameFrame Class