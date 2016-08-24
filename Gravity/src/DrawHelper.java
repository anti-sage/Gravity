import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DrawHelper {
	private GraphicsContext gc;
	private World world;
	private double width;
	private double height;
	private double scalar;
	
	public DrawHelper(GraphicsContext gc, World world) {
		this.gc = gc;
		this.world = world;
	}
	
	public void fillOval(double x, double y, double w, double h) {
		calcSize();
		
		gc.fillOval(x * scalar, y * scalar, w * scalar, h * scalar);
	}
	
	public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints) {
		calcSize();
		
		for(int i = 0; i < nPoints; ++i) {
			xPoints[i] *= scalar;
			yPoints[i] *= scalar;
		}
		
		gc.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public void setFill(Paint p) {
		gc.setFill(p);
		gc.setStroke(p);
	}
	
	private void calcSize() {
		width = gc.getCanvas().getWidth();
		height = gc.getCanvas().getHeight();
		scalar = Math.min(width, height);
	}
}