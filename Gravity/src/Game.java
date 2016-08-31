import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Game {
	private static final String RETRY_TEXT = "Retry";
	private static final String NEXT_TEXT = "Next";
	
	private World world;
	private Ship ship;
	private AimLine aimLine;
	private Button retry;
	private Button next;
	
	private double width;
	private double height;
	
	public enum Step {
		PLACE, AIM, FLY, CRASH
	}
	
	private Step step;
	
	public Game(double width, double height) {
		this.width = width;
		this.height = height;
		world = World.createRandom(width, height);

		resetAll();
	}

	public void update() {
		if(step == Step.FLY) {
			if(ship.step()) {
				nextStep(null);
			}
		}
	}
	
	public void updateCursor(Point2D cursor) {
		if(step == Step.PLACE) {
			ship.updatePlanetPos(cursor);
		}
		
		else if(step == Step.AIM) {
			aimLine.setEnd(cursor);
		}
	}
	
	public void nextStep(Point2D cursor) {
		if(step == Step.PLACE) {
			this.aimLine = new AimLine(ship.getPos(), cursor);
			step = Step.AIM;
		}
		
		else if(step == Step.AIM) {
			ship.launch(aimLine.getEnd());
			step = Step.FLY;
		}
		
		else if(step == Step.FLY) {
			step = Step.CRASH;
			retry = new Button(RETRY_TEXT, new Point2D(width - Button.WIDTH - Button.MARGIN, height - Button.HEIGHT - Button.MARGIN));
			if(ship.getPlanet() == world.getPlanet(world.getEndPlanet())) {
				next = new Button(NEXT_TEXT, new Point2D(width - Button.WIDTH*2 - Button.MARGIN*2, height - Button.HEIGHT - Button.MARGIN));
			}
		}
		
		else if(step == Step.CRASH) {
			if(retry != null && retry.contains(cursor)) {
				resetAll();
			} else if(next != null && next.contains(cursor)) {
				world = World.createRandom(width, height);
				resetAll();
			}
		}
	}

	private void resetShip() {
		ship.affixToPlanet(world.getPlanet(world.getStartPlanet()), new Point2D(0, 0));
	}
	
	private void resetAll() {
		ship = new Ship(world);
		resetShip();
		aimLine = null;
		retry = null;
		next = null;
		step = Step.PLACE;
	}
	
	public void draw(GraphicsContext gc) {
		world.draw(gc);
		if(ship != null) ship.draw(gc);
		if(aimLine != null) aimLine.draw(gc);
		if(retry != null) retry.draw(gc);
		if(next != null) next.draw(gc);
	}
	
	public World getWorld() {
		return world;
	}
}
