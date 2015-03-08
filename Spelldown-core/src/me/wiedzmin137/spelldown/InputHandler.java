package me.wiedzmin137.spelldown;

import me.wiedzmin137.spelldown.screen.GameScreen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
	
	private final Main game;
	private final Storage storage;
	
	public InputHandler(final Main game) {
		this.game = game;
		this.storage = game.getStorage();
	}
	
    @Override
    public boolean keyDown(final int keycode) {
    	if (game.getActualScreen() instanceof GameScreen) {
            switch (keycode) {
            case Keys.LEFT:
            	storage.ship.setMoveLeft(true);
            	storage.ship.setMoveRight(false);
                break;
            case Keys.RIGHT:
            	storage.ship.setMoveRight(true);
            	storage.ship.setMoveLeft(false);
                break;
            case Keys.UP:
            	storage.ship.setMoveUp(true);
            	storage.ship.setMoveDown(false);
            	break;
            case Keys.DOWN:
            	storage.ship.setMoveDown(true);
            	storage.ship.setMoveUp(false);
            	break;
            case Keys.SPACE:
    			storage.ship.setShot(true);
    			break;
            }
            return true;
    	}
    	return false;
    }
    
    @Override
    public boolean keyUp(final int keycode) {
    	if (game.getActualScreen() instanceof GameScreen) {
    		switch (keycode) {
    		case Keys.LEFT:
    			storage.ship.setMoveLeft(false);
    			break;
    		case Keys.RIGHT:
    			storage.ship.setMoveRight(false);
    			break;
            case Keys.UP:
            	storage.ship.setMoveUp(false);
            	break;
            case Keys.DOWN:
            	storage.ship.setMoveDown(false);
            	break;
    		case Keys.SPACE: //TODO change to button if Android
    			storage.ship.setShot(false);
    			break;
    		}
    		return true;
    	}
    	return false;
    }
}
