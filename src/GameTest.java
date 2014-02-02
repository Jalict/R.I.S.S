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
	private long bestTime;
	private long currentTime;
	private long checkTime;
	private Image imageCar;
	private Vector2f p;
	private float angle;
	private float speed;
	private final float speedLimit = 0.15f;
	
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
		imageCar = new Image("texture/car.png");		
		p = new Vector2f(163,309);
		angle = 6;
		
		checkTime = gc.getTime();
	}
 
	@Override
	public void render(GameContainer gc, Graphics g)	throws SlickException {
		imageBackground.draw();		
		
		imageCar.draw(p.getX(), p.getY());
		
		g.drawString("lap: "+lap, 16, 32);
		g.drawString("time: "+(currentTime/1000), 16, 64);
		g.drawString("best: "+bestTime, 16, 64);
	}
 
	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		Input in = gc.getInput();
		currentTime = gc.getTime()-checkTime;
		
		if(in.isKeyDown(in.KEY_W) && speed < speedLimit) {
			speed += 0.0003f*dt;
		}
		
		
		if(in.isKeyDown(in.KEY_A)) {
			angle += 0.005*dt;
		}
		if(in.isKeyDown(in.KEY_D)) {
			angle -= 0.005*dt;
		}
		
		if(imageCollsionMap.getColor((int)p.getX(), (int)p.getY()).equals(new Color(0, 255, 0))) {
			checkpoint = true;
		}
		
		if(imageCollsionMap.getColor((int)p.getX(), (int)p.getY()).equals(new Color(0, 0, 255)) && checkpoint) {
			checkpoint = false;
			lap++;
			if(currentTime>bestTime) {
				bestTime = currentTime;
			}
		}
		
		if(imageCollsionMap.getColor((int)p.getX(), (int)p.getY()).equals(new Color(255, 0, 0)) && speed > speedLimit/3) {
			speed -= 0.002f*dt;
		}
		
		p.x += (Math.sin(angle)*speed)*dt;
	    p.y += (Math.cos(angle)*speed)*dt;
		
		imageCar.setRotation((float) Math.toDegrees(-angle));
	}
}