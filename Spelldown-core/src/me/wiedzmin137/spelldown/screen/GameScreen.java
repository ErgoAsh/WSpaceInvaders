package me.wiedzmin137.spelldown.screen;

import java.util.ArrayList;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.object.Enemy;
import me.wiedzmin137.spelldown.object.Phase;
import me.wiedzmin137.spelldown.object.Shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameScreen extends AbstractScreen {

	private final Camera camera;
	
	private final ModelBatch batch;
	private final Environment environment;
	
	private final Stage stage;
	private final Skin skin;
	private final Label score;
	private final Label life;
	private final Label phaseL;
	private final Table table;
	
	private ArrayList<Shot> removedShots = new ArrayList<Shot>();
	private Phase currentPhase = Phase.ONE;
	
	private Matrix4 matrixForSpawning;
	private Timer.Task task;
	
	public GameScreen(final Main game) {
		super(game);
		
		batch = new ModelBatch();
		
		stage = new Stage();
		table = new Table();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		score = new Label("Score", skin, "default");
		score.setColor(Color.BLACK);
		life = new Label("Life", skin, "default");
		life.setColor(Color.BLACK);
		phaseL = new Label("Phase", skin, "default");
		phaseL.setColor(Color.BLACK);
		
		table.add(score).bottom().row();
		table.add(life).left().row(); //TODO Add hearth picture here
		table.add(phaseL).left();
		table.left();
		table.top();
		table.setFillParent(true);
		
		stage.addActor(table);
		
		environment = new Environment();
		environment.add(new DirectionalLight().set(Color.WHITE, new Vector3(-1, -0.5f, 0).nor()));

		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		camera.position.set(0, 12, 0);
		camera.direction.set(0, -12, -6).sub(camera.position).nor();
        camera.near = 1f;
        camera.far = 300f;
		camera.update();
	}

	@Override
	public void update(final float delta) {
		boolean checkA = game.getStorage().isLoading();
		boolean checkB = game.getStorage().isLoaded();
		if (!checkA) {
			if (!checkB) {
				game.getStorage().loadAssets();
			} else {
				if (matrixForSpawning == null) 
					matrixForSpawning = Main.getInstance().getStorage().ship.transform.cpy();
				
				removedShots.clear();
				
				if (currentPhase == Phase.END) {
					game.setScreen(game.getMainScreen());
					
					GameScreen gameScreen = game.getGameScreeen();
					gameScreen.setCurrentPhase(Phase.ONE);
					game.getStorage().cleanUp();
					//FIXME set ScoreboardScreen instead;
					return;
				}
				
				game.getStorage().update(delta);
				
				int size = game.getStorage().getCurrentEnemies().size;
				if (size == 0) {
					if (currentPhase == Phase.ONE && game.getStorage().ship.getScore() == 0) {
//						game.getStorage().addEnemy(currentPhase.getEnemies(matrixForSpawning.cpy()));
						game.getStorage().addEnemy(currentPhase.getEnemies(matrixForSpawning.cpy()));
					} else {
						if (task == null || task.getExecuteTimeMillis() == 0) {
							task = new Task() {
								@Override
								public void run() {
									currentPhase = Phase.getNextPhase(currentPhase);
									game.getStorage().addEnemy(currentPhase.getEnemies(matrixForSpawning.cpy()));
									task = null;
								}
							};
							Timer.schedule(task, 3F);
						}
					}
				} else {
					if (game.getStorage().getCurrentEnemies().size > 0 && Math.random() < 0.01 * (size / 2)) {
						Enemy enemy = game.getStorage().getCurrentEnemies().random();
						Shot shot = new Shot(game.getStorage().getShotModel(), enemy.transform.cpy(), false, 1);
						game.getStorage().addShot(shot);
					}
					
					//TODO add more "AI" behaviors here
				}
				
				for (Shot shot : removedShots) {
					game.getStorage().remShot(shot);
				}
				
				score.setText(" " + game.getStorage().ship.getScore());
				life.setText(" " + game.getStorage().ship.getLives());
				phaseL.setText(" " + currentPhase.byNumber);
				
				camera.update();
			}
		}
	}

	@Override
	public void draw(final float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
		stage.draw();
        
        batch.begin(camera);
        if (game.getStorage().isLoaded() == true) {
      
        	batch.render(game.getStorage().getCurrentModels(), environment);
        	batch.render(game.getStorage().getCurrentShots(), environment);        	
        	batch.render(game.getStorage().getCurrentEnemies(), environment);
        	
        } //ToDo add loading screen instead of checker
        batch.end();
        
	}
	
	//TODO do it better
	@Override
	public void resize(final int width, final int height) {
		if (width > 640) camera.viewportWidth = width;
		if (height > 480) camera.viewportWidth = height;
		
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		game.getStorage().dispose();
		batch.dispose();
		stage.dispose();
	}
	
	public Phase getCurrentPhase() { return currentPhase; }
	public void setCurrentPhase(final Phase phase) { this.currentPhase = phase; }
}
