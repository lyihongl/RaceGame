package prv.DragRace.gfx.menu;

import java.util.LinkedList;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Menu {
	
	private MenuList menuList;
	
	/**
	 * The current module in which the user pointer is hovering over
	 */
	private MenuModule focusedModule;
	
//	private MenuModule holder;
	
	/**
	 * Organizes Menu Lists
	 */
	public Menu(){
		menuList = new MenuList();
	}
	
	public void addMenuModule(MenuModule m){
		menuList.push(m);
		focusedModule = m;
	}
	
	public void render(GraphicsContext gc){
		
//		while(holder!=null){
//			
//			holder.render(gc);
//			
//			holder = holder.getNext();
//		}
//		if(holder == null)
//			holder = menuModules.find(0);
		
		for(MenuModule m:menuList){
			m.render(gc);
		}
		
		gc.strokeLine(focusedModule.getX()-10, focusedModule.getY()-5, focusedModule.getX()-20, focusedModule.getY()-5);
	}
	
	public MenuList getMenuModules() {
		return menuList;
	}

	public MenuModule getFocusedModule() {
		return focusedModule;
	}

	public void setFocusedModule(MenuModule focusedModule) {
		this.focusedModule = focusedModule;
	}
	
	
	
	
}
