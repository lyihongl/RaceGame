package prv.DragRace.Entities;

import javafx.scene.image.Image;
import prv.DragRace.input.KeyInput;
import prv.DragRace.profiles.CarProfiles;
import prv.DragRace.utils.Handler;

public abstract class Car extends GameObject {

	protected double x, y;// x and y of the car
	protected double theta;// angle car is driving at in degrees
	protected double speed;// speed in m/s
	protected double fdr;// final drive ratio
	protected double wheelRadius;// wheel radius in meters
	protected double currentGR;// current gear ratio
	protected double rpm;// engine rpm
	protected double mass;// car mass in kg
	protected double netForceMag;// magnitude of net force on car
	protected double coefficientF;// coefficient of friction
	protected int gear = 0;// current gear
	protected double gearRatios[];// gear ratios
	protected double tireFriction;// determines turning radius
	protected double turnRate;// radians/s
	protected double throttlePosition; // the desired speed
	// protected Image sprite;

	public Car(Image sprite, Handler handler, CarProfiles CP) {
		super(sprite, handler);
		this.fdr = CP.getFDR();
		this.wheelRadius = CP.getWheelRadius();
		this.mass = CP.getMass();
		this.coefficientF = CP.getCoefficientF();
		this.gearRatios = CP.getGearRatios();
		tireFriction = 5;
		this.sprite = sprite;
		throttlePosition = 0;
	}

	/**
	 * Updates the engine rpm based on speed and gear ratios
	 */
	protected void updateRPM() {
		rpm = speed * 60 * fdr * currentGR / (2 * Math.PI * wheelRadius);
	}

	protected abstract double torque(double rpm);

	/**
	 * Calculates turn speed based on current vehicle speed, and lateral tire
	 * friction
	 */
	protected void calculateTurnSpeed() {
		double turnRadius = mass * Math.pow(speed, 2) / (tireFriction * mass * 9.81);
		turnRate = 6 / (turnRadius * 2 * Math.PI / (speed));
		if (turnRate < 0)
			turnRate = 0;
		if (turnRate > 7)
			turnRate = 7;
		// System.out.println(turnRadius);
	}

	/**
	 * Calculates the net force on car
	 */
	protected void updateForce() {

		// netForceMag = -friction(coefficientF);
		// if (KeyInput.getKeys()['w'])
		if (speed < 0.1)
			netForceMag = ((torque(rpm) * throttlePosition * fdr * currentGR / wheelRadius)
					* Math.pow(throttlePosition, 3));
		else
			netForceMag = ((torque(rpm) * throttlePosition * fdr * currentGR / wheelRadius)
					* Math.pow(throttlePosition, 3)) - friction(coefficientF);
		// System.out.println(torque(rpm));
		// System.out.println(netForceMag);
		// System.out.println(netForceMag);
		// System.out.println("a");
	}

	/**
	 * Passively slow car
	 */
	protected void passiveSlow() {
		netForceMag = -friction(coefficientF);
		// System.out.println(speed);
		// System.out.println("passive");
	}

	/**
	 * Update speed based on net force
	 */
	protected void updateSpeed() {
		double acceleration = netForceMag / mass;

		speed += acceleration / 60;

	}

	/**
	 * Calculating the tire friction and air friction on car
	 * 
	 * @param coefficient
	 *            COefficient of tire friction
	 * @return Double
	 */
	protected double friction(double coefficient) {
		double drag = 1.225 * speed * 0.33 * 2.06;
		double friction = coefficient * mass * 9.81;
		return drag + friction;

	}

	/**
	 * Braking force on car
	 * 
	 * @param breakForce
	 *            the amount of force the breaks apply
	 */
	public void brake(double breakForce) {
		// if (speed - breakForce / mass > 0)
		// speed -= breakForce / mass;
		netForceMag -= breakForce;
		// System.out.println("BRAKE");
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getFdr() {
		return fdr;
	}

	public void setFdr(double fdr) {
		this.fdr = fdr;
	}

	public double getWheelRadius() {
		return wheelRadius;
	}

	public void setWheelRadius(double wheelRadius) {
		this.wheelRadius = wheelRadius;
	}

	public double getCurrentGR() {
		return currentGR;
	}

	public void setCurrentGR(double currentGR) {
		this.currentGR = currentGR;
	}

	public double getRpm() {
		return rpm;
	}

	public void setRpm(double rpm) {
		this.rpm = rpm;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public double getThrottlePosition() {
		return throttlePosition;
	}
	

}
