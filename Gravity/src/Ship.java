import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Ship {
	public static final double length = 0.02;
	private Point2D pos;
	private double rot;
	
	public Ship(Point2D pos, double rot) {
		this.pos = pos;
		this.rot = rot;
	}

	public Ship() {
		
	}

	public void draw(DrawHelper dh) {
		dh.setFill(Color.YELLOW);
		
		dh.fillOval(pos.getX() - length/2, pos.getY() - length/2, length, length);
		
		//dh.fillPolygon(new double[]{0.5, 0.5, 0.6}, new double[]{0.5, 0.6, 0.5}, 3);
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
