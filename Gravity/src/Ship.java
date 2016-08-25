import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Ship {
	public static final double LENGTH = 20;
	private Point2D pos;
	private double rot;
	private World world;
	private Planet planetFixedTo;
	
	public Ship(World world) {
		this.world = world;
	}

	public Ship() {
		
	}
	
	public void launch(Point2D dir) {
		
	}
	
	public void affixToPlanet(Planet planet) {
		this.planetFixedTo = planet;
		updatePlanetPos(new Point2D(0,0));
	}
	
	public void updatePlanetPos(Point2D cursor) {
		double distance = cursor.distance(planetFixedTo.getPos());
		Point2D shipPos = planetFixedTo.getPos().add(cursor.subtract(planetFixedTo.getPos()).multiply(1/distance).multiply(planetFixedTo.getRadius() + LENGTH/2));
		
		setPos(shipPos);
	}

	public void draw(DrawHelper dh) {
		dh.setColor(Color.YELLOW);
		dh.fillOval(pos.getX() - LENGTH/2, pos.getY() - LENGTH/2, LENGTH, LENGTH);
	}

	public Point2D getPos() {
		return pos;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}

	public double getRot() {
		return rot;
	}

	public void setRot(double rot) {
		this.rot = rot;
	}
}
