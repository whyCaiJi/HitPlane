package heyang.util;
/**
 * Constant class
 * @author wuheyang0617
 * @date 28-Jul-2016
 * @OS Windows 10
 * @version 1.0
 */
public class Constant {
	// The width and height of the frame
	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 500;
	public static final int EXPLOSION_NO = 23;
	public static int BULLET_NO = 55;
	public static int BULLET_SPEED = 4;
	
	// Setter methods for different difficulties
	public static void setBulletNumber(int n){
		BULLET_NO = n;
	}// end setBulletNumber()
	
	public static void setBulletSpeed(int n){
		BULLET_SPEED = n;
	}// end setBulletSpeed()
}// end Constant Class
