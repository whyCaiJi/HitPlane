/**
 * Plane Class File
 * Author: Wu Heyang
 * Date: 28-Jul-2016
 * OS: Windows 10
 * Version: 1.0
 */

package heyang.plane;

import heyang.util.GameUtil;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Plane {
	// Instance variables
	int speed;
	Image img;
	double x,y;
	private boolean left,up,right,down;
	private boolean live = true;
	int width,height;
	
	// Method to get a rectangle for collision check
	public Rectangle getRect(){
		return new Rectangle((int)(x+4),(int)(y+4),width-8,height-8);
	}// end getRect()
	
	// Accessor method for live
	public boolean isLive() {
		return live;
	}// end isLive();

	// Mutator method for live
	public void setLive(boolean live) {
		this.live = live;
	}// end setLive()

	// Draw method for the plane
	public void draw(Graphics g){
		if(live){
			g.drawImage(img,(int)x,(int)y,null);
			move();
		}// end if
	}// end draw()
	
	// Move method to update the coordinate of the plane
	public void move(){
		// If statement to check whether the plane is out of the frame window
		// If the plane is at the boundary, plane cannot move further
		if(left && x >= 0){
			x -= speed;
		}// end if
		if(right && x <= 468){
			x += speed;
		}// end if
		if(up && y >= 20){
			y -= speed;
		}// end if
		if(down && y <= 468){
			y += speed;
		}// end if
	}// end move()
	
	// Keyboard control to start moving the plane when keys are pressed
	public void changeDirection(KeyEvent e){
		switch(e.getKeyCode()){
		case 37:
		case 65:
			left = true;
			break;
		case 38:
		case 87:
			up = true;
			break;
		case 39:
		case 68:
			right = true;
			break;
		case 40:
		case 83:
			down = true;
			break;
		default:
			break;
		}// end switch
	}// end changeDirection()
	
	// Keyboard control to stop moving the plane when keys are released
	public void stopDirection(KeyEvent e){
		switch(e.getKeyCode()){
		case 37:
		case 65:
			left = false;
			break;
		case 38:
		case 87:
			up = false;
			break;
		case 39:
		case 68:
			right = false;
			break;
		case 40:
		case 83:
			down = false;
			break;
		default:
			break;
		}// end switch
	}// end stopDirection()
	
	// Reset the game
	public void reset(KeyEvent e) {
		switch(e.getKeyCode()){
		case 82:
			PlaneGameFrame.initialize();
		}// end switch
	}// end reset
	
	// Constructor methods
	public Plane(String imgpath, double x,double y,int speed){
		super();
		this.img = GameUtil.getImage(imgpath);
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.height = 32;
		this.width = 32;
	}// end Plane()
	
	public Plane(){}// end Plane()
	
}// end Plane class