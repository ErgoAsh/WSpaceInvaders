package me.wiedzmin137.spelldown.screen;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.object.Phase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen extends AbstractScreen {

	@SuppressWarnings("unused")
	private final Main game;
	
	private final Table table;
	
	private final TextButton gameNew;
	private final TextButton gameQuit;
	private final Button hasSound;
	private final Stage stage;
	
	public MainScreen(final Main game) {
		super(game);
		this.game = game;
		
		stage = new Stage();
		table = new Table();
		
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
			
		hasSound = new Button(skin, "default");
//		hasSound.set
		
		gameNew = new TextButton("New Game", skin, "default");
		gameNew.addListener(new ClickListener() {
			@Override 
			public void clicked(final InputEvent event, final float x, final float y) {
//				gameNew.setSkin(buttonClicked);
				
				GameScreen gameScreen = game.getGameScreeen();
				if (gameScreen.getCurrentPhase() == Phase.END) {
					gameScreen.setCurrentPhase(Phase.ONE);
				}
				
				game.setScreen(gameScreen);
			}
		});
		
		gameQuit = new TextButton("Quit", skin, "default");
		gameQuit.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				Gdx.app.exit();
			}
		});
		
		table.add(gameNew).expand().row();
		table.add(gameQuit).expand().row();
		table.add(hasSound).expand();

		table.setFillParent(true);
		table.setDebug(true);
		stage.addActor(table);
	}

	@Override
	public void update(final float delta) {}

	@Override
	public void draw(final float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	@Override
	public void resize(final int width, final int height) {
		table.setWidth(Gdx.graphics.getWidth());
		table.setHeight(Gdx.graphics.getHeight());
	}
	
	public Stage getStage() { return stage; }

}
