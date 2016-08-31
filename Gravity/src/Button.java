import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Button {
	public static final double MARGIN = 20;
	public static final double WIDTH = 100;
	public static final double HEIGHT = 50;
	
	private String text;
	
	private Point2D pos;
	
	public Button(String text, Point2D pos) {
		this.text = text;
		this.pos = pos;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(pos.getX(), pos.getY(), WIDTH, HEIGHT);
		gc.setStroke(Color.INDIANRED);
		gc.setTextBaseline(VPos.CENTER);
		gc.strokeText(text, pos.getX() + MARGIN, pos.getY() + HEIGHT/2);
	}
	
	public boolean contains(Point2D point) {
		return (point.getX() >= pos.getX() && point.getX() <= pos.getX() + WIDTH && point.getY() >= pos.getY() && point.getY() <= pos.getY() + HEIGHT);
	}
}