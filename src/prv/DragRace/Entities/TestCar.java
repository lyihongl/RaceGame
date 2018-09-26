package prv.DragRace.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import prv.DragRace.Game;
import prv.DragRace.gfx.Assets;
import prv.DragRace.gfx.ImageUtils;
import prv.DragRace.input.KeyInput;
import prv.DragRace.input.MouseInput;
import prv.DragRace.input.ScrollInput;
import prv.DragRace.profiles.CarProfiles;
import prv.DragRace.utils.Handler;

public class TestCar extends Car {

	public TestCar(Handler handler, CarProfiles CP) {
		super(CP.getSprite(), handler, CP);
		x = 2110;
		y = 1330;
		theta = 60;
	}

	@Override
	protected double torque(double rpm) {

		double torque;

		// if ((500 * Math.sqrt(1 - Math.pow((rpm - 3700) / 4000, 2))) >
		// ((600000) / (rpm - 5000) -150) || rpm < 6088.84) {
		// torque = 500 * Math.sqrt(1 - Math.pow((rpm - 3700) / 4000, 2));
		// } else {
		// torque = (600000) / (rpm - 5000) - 150;
		//
		// }
		// System.out.println(rpm);
		if (((-1.0 / 15000.0) * Math.pow(rpm - 3800, 2) + 1000) < 450)
			torque = (-1.0 / 15000.0) * Math.pow(rpm - 3800, 2) + 1000;
		else
			torque = 450;
		if(torque<0)
			torque = -50;

		return torque;
	}

	private long shiftTime;
	private boolean acc = true;

	public void mouseTurn() {
		double mouseX = MouseInput.getX();
		double mouseY = MouseInput.getY();
		double carCenterX = Game.WIDTH / 2;
		double carCenterY = Game.HEIGHT / 2;
		double deltaX = mouseX - carCenterX;
		double deltaY = mouseY - carCenterY;
		double dTheta = Math.atan(deltaX / deltaY);

		if (deltaX >= 0 && deltaY <= 0) {
			dTheta = -dTheta * 180 / Math.PI;
			if (dTheta == -90)
				dTheta = 90;
		} else if (deltaX < 0 && deltaY < 0) {
			dTheta = 360 - dTheta * 180 / Math.PI;
		} else if (deltaX > 0 && deltaY > 0) {
			dTheta = 180 - dTheta * 180 / Math.PI;
		} else if (deltaX <= 0 && deltaY >= 0) {
			dTheta = 180 - dTheta * 180 / Math.PI;
		}

		double difference;

		if (Math.abs(theta - dTheta) > 180) {
			if (theta < dTheta)
				difference = theta - dTheta + 360;
			else{
				difference = dTheta - theta;
			}
		} else
			difference = theta - dTheta;
		
		if (difference > 0 && speed != 0)
			theta -= turnRate;
		
		if (difference < 0 && speed != 0)
			theta += turnRate;
		
		theta %= 360;
		
		if (theta < 0)
			theta = 360 + theta;
	
	}

	@Override
	public void tick() {
		
		calculateTurnSpeed();
		mouseTurn();
		updateForce();
		
		if (ScrollInput.getScrollY() > 0) {
			
			if (throttlePosition < 1)
				throttlePosition += 0.05;
			
		} else if (ScrollInput.getScrollY() < 0) {
			
			if (throttlePosition > 0)
				throttlePosition -= 0.05;
		}
		updateForce();
		if (KeyInput.getKeyCode() == KeyCode.SPACE) {
			throttlePosition = 0;
			brake(10000);

		} else if (throttlePosition == 0) {
			passiveSlow();
		}

		if (KeyInput.oneClickKey('+') || MouseInput.isRightClick()) {
			if (gear + 1 < gearRatios.length)

				gear++;
			shiftTime = System.currentTimeMillis();
			updateRPM();
		}
		if (KeyInput.oneClickKey('-') || MouseInput.isLeftClick()) {
			if (gear > 0)
				gear--;
			shiftTime = System.currentTimeMillis();
			updateRPM();
		}
		if (System.currentTimeMillis() - shiftTime > 300) {
			currentGR = gearRatios[gear];
		}
		if (speed > 0 || throttlePosition > 0) {
			updateRPM();
			updateSpeed();
		}
		x += Math.sin(theta / 180 * Math.PI) * speed / (3);
		y -= Math.cos(theta / 180 * Math.PI) * speed / (3);
		
	}

	@Override
	public void render(GraphicsContext gc) {

		ImageUtils.drawRotatedImage(gc, sprite, theta, x - handler.getGameCamera().getOffsetX(),
				y - handler.getGameCamera().getOffsetY());

	}

}
