import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class AimLine {
	private Point2D start;
	private Point2D end;
	
	public AimLine(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}
	
	public void draw(DrawHelper dh) {
		dh.setColor(Color.RED);
		dh.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
	
	public Point2D getStart() {
		return start;
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public Point2D getEnd() {
		return end;
	}

	public void setEnd(Point2D end) {
		this.end = end;
	}
}
