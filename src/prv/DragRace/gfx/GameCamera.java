package prv.DragRace.gfx;

import prv.DragRace.Game;
import prv.DragRace.Entities.Car;
import prv.DragRace.Entities.GameObject;
import prv.DragRace.utils.Handler;

public class GameCamera {
	
	private double offsetX, offsetY, offsetAngle;
	
	private Handler handler;
	
	public GameCamera(Handler handler){
		this.handler = handler;
	}
	
	/**
	 * Creates offsets that can be used to keep the display centered on a car
	 * @param go Car Object
	 */
	public void centerOnCar(Car go){
		offsetX = go.getX() - Game.WIDTH/2+go.getSprite().getWidth()/2;
//		System.out.println(go.getX());
		offsetY = go.getY() - Game.HEIGHT/2+go.getSprite().getHeight()/2;
		offsetAngle = go.getTheta();
//		System.out.println(go.getSprite().getWidth());
	}

	public double getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}
	
	public double getOffsetAngle(){
		return offsetAngle;
	}
	
	
}
