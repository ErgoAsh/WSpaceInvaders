package me.wiedzmin137.spelldown.screen;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.object.Phase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen extends AbstractScreen {

	@SuppressWarnings("unused")
	private final Main game;
	
	private final Table table;
	
	private final TextButton gameNew; //TODO add endlessMode button
	private final TextButton gameQuit;
	private final Window gameWindow;
	private final Stage stage;
	
	private int width;
	private int height;
	
	public MainScreen(final Main game) {
		super(game);
		this.game = game;
		
		stage = new Stage();
		table = new Table();
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		gameNew = new TextButton("New Game", skin, "default");
		gameNew.setHeight(gameNew.getHeight() * 2);
		gameNew.setWidth(gameNew.getWidth() * 2);
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
		gameQuit.setHeight(gameQuit.getHeight() * 2);
		gameQuit.setWidth(gameQuit.getWidth() * 2);
		gameQuit.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				Gdx.app.exit();
			}
		});
		gameWindow = new Window("Main menu", skin, "default");
		
		table.add(gameNew).expand().row();
		table.add(gameQuit).expand().row();

		table.setFillParent(true);
		stage.addActor(new Image(new Texture(Gdx.files.internal("data/space.png"))));
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
		stage.getViewport().update(width, height);
		table.setWidth(width);
		table.setHeight(height);
		
		float scaleWidth = width / this.width;
		float scaleHeight = height / this.height;
		
		for (Cell<?> cell : table.getCells()) {
			cell.setActorHeight(cell.getActorHeight() * scaleHeight);
			cell.setActorWidth(cell.getActorWidth() * scaleWidth);
		}
	}
	
	public Stage getStage() { return stage; }

}
