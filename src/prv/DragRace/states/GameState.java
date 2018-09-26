package prv.DragRace.states;

import java.awt.Paint;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import prv.DragRace.Game;
import prv.DragRace.Entities.Car;
import prv.DragRace.Entities.TestCar;
import prv.DragRace.gfx.Assets;
import prv.DragRace.gfx.ImageUtils;
import prv.DragRace.input.KeyInput;
import prv.DragRace.profiles.CarProfiles;
import prv.DragRace.utils.Handler;

public class GameState extends State {

	// Car h = new Car(null, null);

	private Line speedometer, tachometer, throttle;
	private Rotate rot;
//	private Line tachometer;
	private Rotate rpm, throttleRot;
	private String speedLabel, rpmLabel;
	private TestCar tc;// delete this

	public GameState(Handler handler) {
		super(handler);
		tc = new TestCar(handler, CarProfiles.WRX_STI);

		speedometer = new Line(600, 620, 500, 620);

		rot = new Rotate();
		rot.pivotXProperty().bind(speedometer.startXProperty());
		rot.pivotYProperty().bind(speedometer.startYProperty());
		speedometer.getTransforms().add(rot);

		tachometer = new Line(360, 620, 260, 620);

		rpm = new Rotate();
		rpm.pivotXProperty().bind(tachometer.startXProperty());
		rpm.pivotYProperty().bind(tachometer.startYProperty());
		tachometer.getTransforms().add(rpm);
		
		throttle = new Line(800, 620, 760, 620);
		throttleRot = new Rotate();
		throttleRot.pivotXProperty().bind(throttle.startXProperty());
		throttleRot.pivotYProperty().bind(throttle.startYProperty());
		throttle.getTransforms().add(throttleRot);
	}

	@Override
	public void tick() {
		tc.tick();
		handler.getGameCamera().centerOnCar(tc);
//		if(KeyInput.oneClickKey(27)){
//			System.out.println("A");
//		}
		// System.out.println(tc.getSprite());
		rot.setAngle(tc.getSpeed() * 3.6 / 300 * 180);
		rpm.setAngle(tc.getRpm() / 9000 * 200);
		throttleRot.setAngle(tc.getThrottlePosition()*180);
	}

	@Override
	public void render(GraphicsContext gc) {
		

		speedLabel = String.format("%3.1f km/h", tc.getSpeed() * 3.6);
		rpmLabel = String.format("%3.0f rmp", tc.getRpm());
//		System.out.println(handler.getGameCamera().getOffsetX());
		
//		gc.save(); // saves the current state on stack, including the current
//		// transform
//		ImageUtils.rotate(gc, -tc.getTheta(),  Game.WIDTH/2, Game.HEIGHT/2);
//		gc.drawImage(Assets.tsukuba, -handler.getGameCamera().getOffsetX(), -handler.getGameCamera().getOffsetY());
//		gc.restore();
		
		gc.drawImage(Assets.tsukuba, -handler.getGameCamera().getOffsetX(), -handler.getGameCamera().getOffsetY());
		
		// ImageUtils.drawRotatedImage(gc, Assets.tsukuba,
		// -handler.getGameCamera().getOffsetAngle(),
		// -handler.getGameCamera().getOffsetX()*Math.sin(180/tc.getTheta()),
		// -handler.getGameCamera().getOffsetY()*Math.cos(180/tc.getTheta()));
		
		gc.setFill(Color.RED);
		gc.setFont(new Font(12));
		gc.fillText(speedLabel, 570, 640);
		gc.fillText(rpmLabel, 330, 640);
		gc.fillText("Throttle", 780, 640);
		
//		ImageUtils.drawRotatedImage(gc, Assets.WRX_STI, 30, 0, 0);
		
		gc.setFont(new Font(30));
		gc.fillText("" + (tc.getGear() + 1), 470, 620);
		tc.render(gc);

	}

	public Group nonGraphicsEliments() {
		Group g = new Group();
		tachometer.setStroke(Color.RED);
		speedometer.setStroke(Color.RED);
		throttle.setStroke(Color.RED);
		g.getChildren().add(tachometer);
		g.getChildren().add(speedometer);
		g.getChildren().add(throttle);
		return g;
	}

	public Line getSpeedometer() {
		return speedometer;
	}

	public Line getTachometer() {
		return tachometer;
	}

}
