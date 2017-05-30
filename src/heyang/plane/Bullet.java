package heyang.plane;

/**
 * Bullet Class
 * @author wuheyang0617
 * @date 28-Jul-2016
 * @OS Windows 10
 * @version 1.0
 */
import heyang.util.Constant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	// Instance variables
	double x,y;
	double speed;
	double degree;
	int width = 5;
	int height = 5;
	
	// Constructor method to generate random bullets, radiating from 
	// the center of the screen
	public Bullet(){
		degree = Math.random()* Math.PI*2;
		speed = Constant.BULLET_SPEED + (Constant.BULLET_SPEED*Math.random());
		x = Constant.GAME_WIDTH/2 + 40*(1+Math.random())*Math.cos(degree);
		y = Constant.GAME_HEIGHT/2 + 40*(1+Math.random())*Math.sin(degree);
	}// end Bullet()
	
	// Method to get the rectangle area for collision check
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,width,height);
	}// end getRect()
	
	// Draw method to draw out all bullets
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, width, height);
		g.setColor(c);
		move();
	}// end draw()
	
	// Move method to update the coordinates of the bullet for the next frame 
	public void move(){
		// Polar coordinates
		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);

		// If statement for bouncing in vertical direction
		if(y > Constant.GAME_HEIGHT-height-5 || y < 30){
			degree = -degree;
		}// end if
		
		// If statement for bouncing in horizontal direction
		if(x<5 || x>Constant.GAME_WIDTH-width-5){
			degree = Math.PI - degree;
		}// end if
	}// end move()
}// end Bullet Class