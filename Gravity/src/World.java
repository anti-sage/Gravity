import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;

public class World {
	public static final int MIN_PLANETS = 3;
	public static final int MAX_PLANETS = 10;
	public static final double MIN_PLANET_SIZE = 0.1;
	public static final double MAX_PLANET_SIZE = 0.4;
	
	private List<Planet> planets = new ArrayList<>();
	
	public void addPlanet(Planet planet) {
		planets.add(planet);
	}
	
	public List<Planet> getPlanets() {
		return planets;
	}
	
	public static World createRandom() {
		Random r = MainWindow.rand;
		
		int numPlanets = r.nextInt(MAX_PLANETS + 1 - MIN_PLANETS) + MIN_PLANETS;
		
		
		World world = new World();
		
		for(int i = 0; i < numPlanets; ++i) {
			double radius = r.nextDouble() * (MAX_PLANET_SIZE - MIN_PLANET_SIZE) + MIN_PLANET_SIZE;
			double x = r.nextDouble() * (1-radius*2) + radius;
			double y = r.nextDouble() * (1-radius*2) + radius;
			Planet planet = new Planet(new Point2D(x, y), radius);
			
			boolean skip = false;
			
			for(Planet p : world.getPlanets()) {
				if(planet.overlaps(p)) {
					skip = true;
					break;
				}
			}
			
			if(!skip) {
				world.addPlanet(planet);
			}
			
			else {
				--i;
			}
		}
		
		return world;
	}
}
