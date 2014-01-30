import java.awt.Point;
import java.awt.image.PixelGrabber;
import java.util.Vector;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
 
public class GameTest extends BasicGame {
	private Image imageBackground;
	private Image imageCollsionMap;
	private boolean checkpoint;
	private int lap = 0;
	/*private int bestTime;
	private int currentTime;*/
	private Image test;
	private Vector2f p;
	private float angle;
	
	public GameTest(String title) { 
		super(title); 
	}
 
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameTest("Race"));
		app.setShowFPS(false);
		app.start();
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		imageBackground = new Image("texture/level/1.png");
		imageCollsionMap = new Image("texture/level/1c.png");
		test = new Image("texture/test.png");		
		p = new Vector2f(163,309);
		angle = 6;
	}
 
	@Override
	public void render(GameContainer gc, Graphics g)	throws SlickException {
		imageBackground.draw();
		
		test.draw(p.getX(), p.getY());
		
		g.drawString("lap: "+lap, 16, 32);
	}
 
	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		Input in = gc.getInput();
		
		if(in.isKeyDown(in.KEY_W)) {
			p.x += (Math.sin(angle)/8)*dt;
		    p.y += (Math.cos(angle)/8)*dt;
		}
		
		if(in.isKeyDown(in.KEY_A)) {
			angle += 0.005;
		}
		if(in.isKeyDown(in.KEY_D)) {
			angle -= 0.005;
		}
		
		if(imageCollsionMap.getColor((int)p.getX(), (int)p.getY()).equals(new Color(0, 255, 0))) {
			checkpoint = true;
		}
		
		if(imageCollsionMap.getColor((int)p.getX(), (int)p.getY()).equals(new Color(0, 0, 255)) && checkpoint) {
			checkpoint = false;
			lap++;
			//TODO: Get current time
			/*if(currentTime>bestTime) {
				bestTime = currentTime;
			}*/
		}
		
		if(imageCollsionMap.getColor((int)p.getX(), (int)p.getY()).equals(new Color(255, 0, 0))) {
			//TODO: Make velocity slower in case of being outside track
		}
	}
}