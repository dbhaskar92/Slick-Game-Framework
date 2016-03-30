import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.shapes.PolygonShape;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BrickWall{

	private Body body;
	private Image img;
	private float width;
	private float height;

	public BrickWall(float x, float y, float width, float height, World world) throws SlickException{
		
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.STATIC;
		bodyDef.position.set(x/100, y/100);	// center position
		
		// fixture definition
		PolygonShape wall = new PolygonShape();
		wall.setAsBox(width/200, height/200);	// half width and height
		FixtureDef fd = new FixtureDef();
		fd.shape = wall;
		fd.density = 1.0f;
		fd.friction = 0.2f;
		fd.restitution = 0.1f;

		// create body
		body = world.createBody(bodyDef);
		body.createFixture(fd);
		
		// get Image, set width and height
		img = new Image("../imgs/brickwall.png");
		this.width = width;
		this.height = height;
	}

	public float getX(){
		return body.getPosition().x * 100;		
	}

	public float getY(){
		return body.getPosition().y * 100;
	}

	public float getWidth(){
		return width;
	}

	public float getHeight(){
		return height;
	}

	public Image getImage(){
		return img;
	}

	public void setPosition(float x, float y){
		Vec2 newpos = new Vec2(x/100, y/100);
		body.setTransform(newpos, body.getAngle());
	}

}
