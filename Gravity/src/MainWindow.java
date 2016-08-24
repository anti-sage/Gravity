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
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(700, 700);
		
		World world = World.createRandom();
		
		Pane pane = new Pane(canvas);
		pane.setStyle("-fx-background-color: black;");
		
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
		DrawHelper dh = new DrawHelper(gc, world);
		
		dh.setFill(Color.WHITE);

		for(Planet planet : world.getPlanets()) {
			planet.draw(dh);
		}
		
		new Ship(new Point2D(0.4, 0.4), 100).draw(dh);
		//world.getPlanet(world.getStartPlanet()).
	}
}
