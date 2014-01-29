import java.awt.image.PixelGrabber;

import org.newdawn.slick.*;
 
public class GameTest extends BasicGame {
	private Image imageBackground;
	private Image imageCollsionMap;
	private boolean checkpoint;
	private int lap = 0;
	/*private int bestTime;
	private int currentTime;*/
	private Image test;
	private int px,py;
	
	public GameTest(String title) { 
		super(title); 
	}
 
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameTest("Race"));
		app.start();
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		imageBackground = new Image("texture/level/1.png");
		imageCollsionMap = new Image("texture/level/1c.png");
		test = new Image("texture/test.png");
	}
 
	@Override
	public void render(GameContainer gc, Graphics g)	throws SlickException {
		imageBackground.draw();
		
		if(imageCollsionMap.getColor(px, py).equals(new Color(255, 0, 0))) {
			test.draw(16, 16);
		}
		g.drawString("lap: "+lap, 16, 32);
		g.drawString("cp: "+checkpoint, 16, 48);
	}
 
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		px = gc.getInput().getAbsoluteMouseX();
		py = gc.getInput().getAbsoluteMouseY();
		
		if(imageCollsionMap.getColor(px, py).equals(new Color(0, 255, 0))) {
			checkpoint = true;
		}
		
		if(imageCollsionMap.getColor(px, py).equals(new Color(0, 0, 255)) && checkpoint) {
			checkpoint = false;
			lap++;
			/*if(currentTime>bestTime) {
				bestTime = currentTime;
			}*/
		}
	}
}