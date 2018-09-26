package prv.DragRace.states;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import prv.DragRace.gfx.menu.Menu;
import prv.DragRace.gfx.menu.MenuAction;
import prv.DragRace.gfx.menu.MenuModule;
import prv.DragRace.input.KeyInput;
import prv.DragRace.utils.Handler;

public class MenuState extends State {

	private Menu currentMenu;

	public MenuState(Handler handler) {
		super(handler);
		currentMenu = new Menu();
		currentMenu.addMenuModule(new MenuModule("Play", 30, 30, new MenuAction(){

			@Override
			public void action() {
				handler.setState(handler.getGameStateInstance());
			}
			
		}));
		currentMenu.addMenuModule(new MenuModule("Garage", 30, 50, new MenuAction(){

			@Override
			public void action() {
				System.out.println("WOOOO");
			}
			
		}));
		currentMenu.setFocusedModule(currentMenu.getMenuModules().find(0));
	}

	@Override
	public void tick() {
		
		if (KeyInput.arrowKeys(0)) {
			if (currentMenu.getFocusedModule().getPrevious() != null)
				currentMenu.setFocusedModule(currentMenu.getFocusedModule().getPrevious());
		}
		if(KeyInput.arrowKeys(2))
			if(currentMenu.getFocusedModule().getNext()!=null)
				currentMenu.setFocusedModule(currentMenu.getFocusedModule().getNext());
		
		if(KeyInput.arrowKeys(1))
			if(currentMenu.getFocusedModule()!=null)
				currentMenu.getFocusedModule().onPress();

	}

	@Override
	public void render(GraphicsContext gc) {
		currentMenu.render(gc);
	}

	@Override
	public Group nonGraphicsEliments() {
		// TODO Auto-generated method stub
		return null;
	}

}
