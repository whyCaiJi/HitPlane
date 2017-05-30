/**
 * Initializing Frame
 * Author: Wu Heyang
 * Date: 28-Jul-2016
 * OS: Windows 10
 * Version: 1.0
 */

package heyang.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	/**
	 * Load window
	 */
	public void launchFrame(){
		// Set the window's parameters
		setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		setBackground(Color.black);
		setLocation(20,20);
		setVisible(true);
		setTitle("火星撞地球（是男人就坚持30秒！）");
		
		// Start a new thread
		new PaintThread().start();
		
		// Make the cross button works
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}// end windowClosing()
		});
	}// end launchFrame()
	
	// Double buffer to prevent flickering
	// Override update method
	private Image offscreenImage = null;
	public void update(Graphics g){
		if(offscreenImage == null){
			offscreenImage = this.createImage(Constant.GAME_HEIGHT,Constant.GAME_WIDTH);
		}// end if
		Graphics gOff = offscreenImage.getGraphics();
		
		paint(gOff);
		g.drawImage(offscreenImage, 0, 0, null);
	}// end update()
	
	// Repaint the screen using thread
	class PaintThread extends Thread{
		// Override the run method
		public void run(){
			while(true){
				repaint();
				try {
					// The the frame time to be 60ms
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}// end while
		}//end run()
	}// end PaintThread inner class
}// end MyFrame class
