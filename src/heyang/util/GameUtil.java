package heyang.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Game dev useful tool class
 * @author wuheyang0617
 * @date 28-Jul-2016
 * @version 1.0
 * @OS Windows 10
 */
public class GameUtil {
	
	private GameUtil(){}	// Constructor is often private
	
	// Load image from image folder
	public static Image getImage(String path){
		return Toolkit.getDefaultToolkit().getImage(GameUtil.class.getClassLoader().getResource(path));
	}// end getImage()
}// end GameUtil Class
