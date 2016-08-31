import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Game {
	private World world;
	private Ship ship;
	private AimLine aimLine;
	private ReplayButton replay;
	
	public enum Step {
		PLACE, AIM, FLY, CRASH
	}
	
	private Step step;
	
	public Game(double width, double height) {
		world = World.createRandom(width, height);
				
		ship = new Ship(world);
		resetShip();
		
		step = Step.PLACE;
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
			ship.launch(cursor);
			step = Step.FLY;
		}
		
		else if(step == Step.FLY) {
			step = Step.CRASH;
			replay = new ReplayButton();
		}
		
		else if(step == Step.CRASH) {
			if(replay.contains(cursor)) {
				resetShip();
				aimLine = null;
				replay = null;
				step = Step.PLACE;
			}
		}
	}

	private void resetShip() {
		ship.affixToPlanet(world.getPlanet(world.getStartPlanet()), new Point2D(0, 0));
	}
	
	public void draw(GraphicsContext gc) {
		world.draw(gc);
		if(ship != null) ship.draw(gc);
		if(aimLine != null) aimLine.draw(gc);
		if(replay != null) replay.draw(gc);
	}
	
	public World getWorld() {
		return world;
	}
}
