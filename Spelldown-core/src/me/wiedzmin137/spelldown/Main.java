package me.wiedzmin137.spelldown;

import me.wiedzmin137.spelldown.screen.AbstractScreen;
import me.wiedzmin137.spelldown.screen.GameScreen;
import me.wiedzmin137.spelldown.screen.MainScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

public class Main extends Game {
	
	static Main instance;
	
	Storage storage;
	InputHandler input;
	InputAdapter clearInput;
	
	AbstractScreen actualScreen;
	MainScreen mainScreen;
	GameScreen gameScreen;
	
	@Override
	public void create() {
		instance = this;
		
		storage = new Storage(this);
		input = new InputHandler(this);
		clearInput = new InputAdapter();
		
		mainScreen = new MainScreen(this);
		gameScreen = new GameScreen(this);
		
		setScreen(mainScreen);
	}

	@Override
	public void render() {
		super.render();
	}
	
    @Override
	public void dispose() {
    	if (actualScreen != null) {
    		actualScreen.dispose();
    	}
    }
    
    public void log(final String message) {
    	Gdx.app.log("[Info]", message);
    }
    
    public void log(final String... message) {
    	for (String msg : message) {
    		log(msg);
    	}
    }
    
    public void setScreen(final AbstractScreen screen) {
    	this.setScreen((Screen) screen);
    	actualScreen = screen;
    	
    	if (screen instanceof GameScreen) {
    		Gdx.input.setInputProcessor(input);
    	} else if (screen instanceof MainScreen) {
    		Gdx.input.setInputProcessor(mainScreen.getStage());
    	} else {
    		Gdx.input.setInputProcessor(clearInput);
    	}
    	
    	log("[Info] Changed screen to " + screen.getClass().getName());
    }
    
    public MainScreen getMainScreen() { return mainScreen; }
    public GameScreen getGameScreeen() { return gameScreen; }
    
    public AbstractScreen getActualScreen() { return actualScreen; }
    
    public Storage getStorage() { return storage; }
    
    public static Main getInstance() { return instance; }
}
