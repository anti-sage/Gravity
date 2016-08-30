import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Planet {
	private Point2D pos;
	private double radius;
	private double density = 30;
	
	public Planet(Point2D pos, double radius) {
		this.pos = pos;
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setSize(double radius) {
		this.radius = radius;
	}
	
	public double getMass() {
		return ((4/3) * Math.PI * Math.pow(radius/World.MIN_PLANET_SIZE, 3) * density);
	}

	public Point2D getPos() {
		return pos;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}
	
	public boolean overlaps(Planet other) {
		return getPos().distance(other.getPos()) < this.getRadius() + other.getRadius();
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(pos.getX() - radius, pos.getY() - radius, radius * 2, radius * 2);
	}
}
