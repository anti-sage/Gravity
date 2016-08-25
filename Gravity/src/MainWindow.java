import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainWindow extends Application {
	public static final String WINDOW_NAME = "Gravity";
//	public final int WINDOW_WIDTH = 800;
//	public final int WINDOW_HEIGHT = 600;
	public static final Random rand = new Random();
	
	public enum Step {
		PLACE, AIM, LAUNCH
	}
	
	private Step step = Step.PLACE;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(700, 700);
		
		World world = World.createRandom();
		
		Pane pane = new Pane(canvas);
		pane.setStyle("-fx-background-color: black;");
		
		setCanvasEvents(canvas, world);
		
		Scene scene = new Scene(pane);
		stage.setTitle(WINDOW_NAME);
		stage.setScene(scene);
		stage.show();
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				updateCanvas(canvas.getGraphicsContext2D(), world);
			}
		};
		
		timer.start();
	}
	
	private void updateCanvas(GraphicsContext gc, World world) {
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		DrawHelper dh = new DrawHelper(gc, world);

		for(Planet planet : world.getPlanets()) {
			planet.draw(dh);
		}
		
		if(world.getShip() != null) world.getShip().draw(dh);
		
		//world.getPlanet(world.getStartPlanet()).
	}
	
	private void setCanvasEvents(Canvas canvas, World world) {
		canvas.setOnMouseMoved(e -> {
			Point2D cursor = new Point2D(e.getX(), e.getY()).multiply(1/canvas.getWidth());
			
			if(step == Step.PLACE) {
				Planet startPlanet = world.getPlanet(world.getStartPlanet());
				
				double distance = cursor.distance(startPlanet.getPos());
				Point2D shipPos = startPlanet.getPos().add(cursor.subtract(startPlanet.getPos()).multiply(1/distance).multiply(startPlanet.getRadius() + Ship.length/2));
				
				if(world.getShip() == null)
					world.setShip(new Ship());
				world.getShip().setPos(shipPos);
			}
			
			else if(step == Step.AIM) {
				canvas.getGraphicsContext2D().setStroke(Color.RED);
				canvas.getGraphicsContext2D().strokeLine(cursor.getX(), cursor.getY(), world.getShip().getPos().getX(), world.getShip().getPos().getY());
			}
		});
		
		canvas.setOnMouseClicked(e -> {
			if(step == Step.PLACE) {
				step = Step.AIM;
			}
			
			else if(step == Step.AIM) {
				step = Step.LAUNCH;
			}
		});
	}
}
