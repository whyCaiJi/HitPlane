package heyang.plane;

/**
 * Explosion Class
 * @author wuheyang0617
 * @date 28-Jul-2016
 * @OS Windows 10
 * @version 1.0
 */

import java.awt.Graphics;
import java.awt.Image;

public class Explode {
	// Instance variables
	double x,y;	
	
	// Initialize count
	static int count = 0;
	
	// Draw method to display the explosion effect
	public void draw(Graphics g, Image[] imgs)
	{
		if(count < 23){
			g.drawImage(imgs[count],(int)x,(int)y,null);
			count++;
		}// end if
	}// end draw()
	
	// Constructor method
	public Explode(double x,double y){
		this.x = x;
		this.y = y;
	}// end Explode()
}// end Explode Class
