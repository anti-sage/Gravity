import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DrawHelper {
	private GraphicsContext gc;
	private World world;
	
	public DrawHelper(GraphicsContext gc, World world) {
		this.gc = gc;
		this.world = world;
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
		gc.strokeLine(x1, y1, x2, y2);
	}
	
	public void fillOval(double x, double y, double w, double h) {
		gc.fillOval(x, y, w, h);
	}
	
	public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints) {
		gc.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public void setColor(Paint p) {
		gc.setFill(p);
		gc.setStroke(p);
	}
}