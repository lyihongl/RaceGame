package prv.DragRace.profiles;

import javafx.scene.image.Image;
import prv.DragRace.Entities.GearBox;
import prv.DragRace.gfx.Assets;

public enum CarProfiles {
	
	
	WRX_STI(3.9, 0.28, 1240, 0.015, GearBox.WRX_STI, Assets.WRX_STI),
	GLA_45AMG(2.44, 0.28, 1560, 0.015, GearBox.GLA45_AMG, Assets.WRX_STI);
	
	private final double FDR;
	private final double wheelRadius;
	private final double mass;
	private final double coefficientF;
	private final double[] gearRatios;
	private final Image sprite;
	
	
	CarProfiles(double FDR, double wheelRadius, double mass, double coefficientF, double[] gearRatios, Image sprite){
		this.FDR = FDR;
		this.wheelRadius = wheelRadius;
		this.mass = mass;
		this.coefficientF = coefficientF;
		this.gearRatios = gearRatios;
		this.sprite = sprite;
		
	}


	public double getFDR() {
		return FDR;
	}


	public double getWheelRadius() {
		return wheelRadius;
	}


	public double getMass() {
		return mass;
	}


	public double getCoefficientF() {
		return coefficientF;
	}


	public double[] getGearRatios() {
		return gearRatios;
	}


	public Image getSprite() {
		return sprite;
	}
	
	
	
}
