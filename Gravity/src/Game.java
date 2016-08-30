import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Game {
	private World world;
	private Ship ship;
	private AimLine aimLine;
	
	public enum Step {
		PLACE, AIM, FLY
	}
	
	private Step step;
	
	public Game(double width, double height) {
		world = World.createRandom(width, height);
				
		ship = new Ship(world);
		ship.affixToPlanet(world.getPlanet(world.getStartPlanet()));
		
		step = Step.PLACE;
	}

	public void update() {
		if(step == Step.FLY) {
			ship.step();
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
	}
	
	public void draw(DrawHelper dh) {
		for(Planet planet : world.getPlanets()) {
			planet.draw(dh);
		}

		if(ship != null) ship.draw(dh);
		if(aimLine != null) aimLine.draw(dh);
	}
	
	public World getWorld() {
		return world;
	}
}
