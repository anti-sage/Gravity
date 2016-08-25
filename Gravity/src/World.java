import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;

public class World {
	public static final int MIN_PLANETS = 3;
	public static final int MAX_PLANETS = 10;
	public static final double MIN_PLANET_SIZE = 50;
	public static final double MAX_PLANET_SIZE = 200;
	
	private List<Planet> planets = new ArrayList<>();
	private int startPlanet;
	private int endPlanet;
	
	private Ship ship;
	
	public void addPlanet(Planet planet) {
		planets.add(planet);
	}
	
	public List<Planet> getPlanets() {
		return planets;
	}
	
	public int numPlanets() {
		return planets.size();
	}
	
	public static World createRandom(double width, double height) {
		Random r = MainWindow.rand;
		
		if(MAX_PLANETS < 2) return null;
		
		int numPlanets = r.nextInt(MAX_PLANETS + 1 - MIN_PLANETS) + MIN_PLANETS;
		
		World world = new World();
		
		for(int i = 0; i < numPlanets; i++) {
			double radius = r.nextDouble() * (MAX_PLANET_SIZE - MIN_PLANET_SIZE) + MIN_PLANET_SIZE;
			double x = r.nextDouble() * (width-radius*2) + radius;
			double y = r.nextDouble() * (height-radius*2) + radius;
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
				i--;
			}
		}
		
		int startPlanetId = r.nextInt(world.numPlanets());
		int endPlanetId = r.nextInt(world.numPlanets() - 1);
		
		if(endPlanetId >= startPlanetId) {
			endPlanetId++;
		}

		world.setStartPlanet(startPlanetId);
		world.setEndPlanet(endPlanetId);
		
		return world;
	}

	public int getStartPlanet() {
		return startPlanet;
	}

	public void setStartPlanet(int startPlanet) {
		this.startPlanet = startPlanet;
	}

	public int getEndPlanet() {
		return endPlanet;
	}

	public void setEndPlanet(int endPlanet) {
		this.endPlanet = endPlanet;
	}
	
	public Planet getPlanet(int i) {
		return planets.get(i);
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
}