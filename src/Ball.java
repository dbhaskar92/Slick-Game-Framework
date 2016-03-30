import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.shapes.CircleShape;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball{

	private Body body;
	private Image img;
	private float radius;

	public Ball(float x, float y, float diameter, World world) throws SlickException{
		
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(x/100,y/100);	// center position
		
		// fixture definition
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(diameter/200);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 0.2f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 1.0f;

		// create body
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		
		// get Image and set radius
		img = new Image("../imgs/ball.png");
		this.radius = diameter/2;
	}

	public float getX(){
		return body.getPosition().x * 100;		
	}

	public float getY(){
		return body.getPosition().y * 100;
	}

	public float getRadius(){
		return radius;
	}

	public Image getImage(){
		return img;
	}

}
