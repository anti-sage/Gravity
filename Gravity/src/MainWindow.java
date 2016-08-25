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
		PLACE, AIM, FLY
	}
	
	private Step step = Step.PLACE;
	private AimLine line;
	private Ship ship;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(700, 700);
		
		World world = World.createRandom(700, 700);
		
		ship = new Ship(world);
		ship.affixToPlanet(world.getPlanet(world.getStartPlanet()));
		
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
		
		if(step == Step.FLY) {
			ship.step();
		}

		for(Planet planet : world.getPlanets()) {
			planet.draw(dh);
		}

		if(ship != null) ship.draw(dh);
		if(line != null) line.draw(dh);
	}
	
	private void setCanvasEvents(Canvas canvas, World world) {
		canvas.setOnMouseMoved(e -> {
			Point2D cursor = new Point2D(e.getX(), e.getY());
			
			if(step == Step.PLACE) {
				ship.updatePlanetPos(cursor);
			}
			
			else if(step == Step.AIM) {
				line.setEnd(cursor);
			}
		});
		
		canvas.setOnMouseClicked(e -> {
			Point2D cursor = new Point2D(e.getX(), e.getY());
			
			if(step == Step.PLACE) {
				this.line = new AimLine(ship.getPos(), cursor);
				step = Step.AIM;
			}
			
			else if(step == Step.AIM) {
				ship.launch(cursor);
				step = Step.FLY;
			}
		});
	}
}
