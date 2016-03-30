import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Slick2dGame extends BasicGame {

	public static float timeStep = 1.0f / 120.f;
	public static int vIterations = 6;
	public static int pIterations = 2;

	public World world;
	private Ball ball;
	private BrickWall wall; 

	public Slick2dGame(String gamename) {
		super(gamename);
		Vec2 gravity = new Vec2(0.0f, 10.0f);
		world = new World(gravity);
	}

	public void init(GameContainer gc) throws SlickException {	
		wall = new BrickWall(300.f, 400.f, 200.f, 100.f, world);
		ball = new Ball(400.f, 10.f, 64.f, world);
	}

	public void update(GameContainer gc, int i) throws SlickException {
		Input userInput = gc.getInput();
		float mX = userInput.getMouseX();
		float mY = userInput.getMouseY();
		wall.setPosition(mX, mY);
		world.step(timeStep, vIterations, pIterations);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		float ballX = ball.getX() - ball.getRadius();
		float ballY = ball.getY() - ball.getRadius();
		g.drawImage(ball.getImage(), ballX, ballY);

		float wallX = wall.getX() - wall.getWidth()/2;
		float wallY = wall.getY() - wall.getHeight()/2;
		g.drawImage(wall.getImage(), wallX, wallY);
	}

	public static void main(String[] args) {
		try {

			AppGameContainer appgc;
			appgc = new AppGameContainer(new Slick2dGame("Slick Game"));
			appgc.setTargetFrameRate(60);
			appgc.setDisplayMode(800, 600, false);
			appgc.start();

		} catch (SlickException ex) {
			Logger.getLogger(Slick2dGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
