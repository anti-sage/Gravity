import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ReplayButton {
	private static final double MARGIN = 20;
	private static final double WIDTH = 100;
	private static final double HEIGHT = 50;
	private static final String TEXT = "Replay";
	
	private double x;
	private double y;
	
	public ReplayButton() {}
	
	public void draw(GraphicsContext gc) {
		Canvas canvas = gc.getCanvas();
		x = canvas.getWidth() - WIDTH - MARGIN;
		y = canvas.getHeight() - HEIGHT - MARGIN;
		
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(x, y, WIDTH, HEIGHT);
		gc.setStroke(Color.INDIANRED);
		gc.setTextBaseline(VPos.CENTER);
		gc.strokeText(TEXT, x + MARGIN, y + HEIGHT/2);
	}
	
	public boolean contains(Point2D point) {
		return (point.getX() >= x && point.getX() <= x + WIDTH && point.getY() >= y && point.getY() <= y + HEIGHT);
	}
}