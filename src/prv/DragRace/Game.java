package prv.DragRace;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import prv.DragRace.gfx.Assets;
import prv.DragRace.gfx.GameCamera;
import prv.DragRace.input.KeyInput;
import prv.DragRace.input.MouseInput;
import prv.DragRace.input.ScrollInput;
import prv.DragRace.states.GameState;
import prv.DragRace.states.MenuState;
import prv.DragRace.states.StateManager;
import prv.DragRace.utils.Handler;

public class Game extends Application {
	/*
	 * Author: Yihong Liu
	 * 
	 */

	public static final int WIDTH = 1000, HEIGHT = 650;

	private Canvas root;

	private KeyInput keyInput;

	private ScrollInput scrollInput;

	private MouseInput mouseInput;

	private Handler handler;

	private Image img, img2;// delete this

	private GameState gameState;

	private GameCamera gCamera;

	private Group mainGroup;

	public Group getMainGroup() {
		return mainGroup;
	}

	public void setMainGroup(Group mainGroup) {
		this.mainGroup = mainGroup;
	}

	private void tick() throws Exception {

		StateManager.getCurrentState().tick();
		// if(tc.getSpeed()*3.6>100){
		// System.out.println("Time: "+(System.currentTimeMillis()-sTime));
		// }

	}

	// delete this

	private void render(GraphicsContext gc) {

		gc.clearRect(0, 0, WIDTH, HEIGHT);
		StateManager.getCurrentState().render(gc);

	}
	
	/**
	 * Begins the game loop
	 */
	private void createContent() {

		root = new Canvas(WIDTH, HEIGHT);

		mainGroup = new Group(root);

		GraphicsContext gc = root.getGraphicsContext2D();
		// if (StateManager.getCurrentState() == gameState)
		// group.getChildren().add(gameState.nonGraphicsEliments());
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				try {
					tick();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				render(gc);

			}
		};
		timer.start();

	}

	@Override
	public void init() {

		Assets.init();

		handler = new Handler();

		keyInput = new KeyInput();
		scrollInput = new ScrollInput();
		mouseInput = new MouseInput();

		gameState = new GameState(handler);
		gCamera = new GameCamera(handler);

		handler.setGame(this);
		handler.setGameCamera(gCamera);

		handler.setGameStateInstance(gameState);
		handler.setState(new MenuState(handler));

		// KeyInput.simulatePress(a, b);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		createContent();
		Scene mainScene = new Scene(mainGroup);

		mainScene.setOnKeyPressed(keyInput);
		mainScene.setOnKeyReleased(keyInput);
		mainScene.setOnScroll(scrollInput);
		mainScene.setOnMousePressed(mouseInput);
		mainScene.setOnMouseReleased(mouseInput);
		mainScene.setOnMouseMoved(mouseInput);
		// mainScene.setOnScrollStarted(scrollInput);
		// mainScene.setOnScrollFinished(scrollInput);

		primaryStage.setScene(mainScene);

		primaryStage.setResizable(false);

		primaryStage.show();

	}

	public static void main(String[] args) {

		launch();

	}

}
