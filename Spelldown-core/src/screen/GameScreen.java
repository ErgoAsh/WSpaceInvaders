package me.wiedzmin137.spelldown.screen;

import java.util.ArrayList;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Storage;
import me.wiedzmin137.spelldown.Storage.EModel;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Phase;
import me.wiedzmin137.spelldown.object.item.Shot;
import me.wiedzmin137.spelldown.object.moveable.Enemy;
import me.wiedzmin137.spelldown.object.moveable.Meteorite;
import me.wiedzmin137.spelldown.object.moveable.SniperBoss;
import me.wiedzmin137.spelldown.object.moveable.ThrowingBoss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends AbstractScreen {
	
	private boolean endlessMode = false;
	private int phase = 1;

	private final Camera camera;
	private final ModelBatch batch;
	private final Environment environment;
	private final Stage stage;
	private final Viewport viewport;
	
	@SuppressWarnings("unused")
	private int width;
	private int height;
	
	private final Image image;
	private final Image image2;
	private final Skin skin;
	private final Table locationTable;
	
	private final Window stats;
	private final Label score;
	private final Label life;
	private final Label phaseL;
	
	private final Button shotButton;
	private final Rectangle rect;
	
	private final Table waveTable;
	private final Label waveNumber;
	private final Label waveComment;
	
	private final Window escapeWindow;
	private final TextButton backButton;
	private final TextButton endButton;
	
	private ArrayList<Shot> removedShots = new ArrayList<Shot>();
	private Phase currentPhase = Phase.ONE;
	private Matrix4 matrixForSpawning;
	private Timer.Task spawnTask;
	private final Timer.Task showWaveInfo;
	private float runningTime;
	private boolean isStopped;
	
	public GameScreen(final Main game) {
		super(game);
		
		isStopped = false;
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		camera = new OrthographicCamera();
//		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new StretchViewport(25, 25, camera);
		batch = new ModelBatch();
		runningTime = 0;
		
		environment = new Environment();
		environment.add(new DirectionalLight().set(Color.WHITE, new Vector3(-1, -0.5f, 0).nor()));
		
		camera.position.set(0, 10, 7);
		camera.direction.set(0, -10, -5).sub(camera.position).nor();
//		camera.position.set(0, 12, 0);
//		camera.direction.set(0, -12, -6).sub(camera.position).nor();
		camera.near = 3;
		camera.update();
		
		stage = new Stage();
		locationTable = new Table();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		image = new Image(game.getStorage().getImageTexture());
		image.setPosition(0, 0);
//		image.rotateBy(270);
//		image.setPosition((image.getImageHeight() - width) / 2, image.getWidth());
		image2 = new Image(game.getStorage().getImageTexture());
		image2.setPosition(0, image.getHeight());
//		image2.rotateBy(270);
//		image2.setPosition((image2.getImageHeight() - width) / 2, 0);
		
		waveTable = new Table().center();
		waveNumber = new Label("Number", skin, "default");
		waveComment = new Label("Comment", skin, "default");
//		waveTable.setFillParent(true);
		waveTable.add(waveNumber).bottom().row();
		waveTable.add(waveComment).top().row();
		waveTable.setScale(200, 200);
		waveTable.setVisible(false);
		
		backButton = new TextButton("Back to game", skin, "default");
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				toggleStop();
			}
		});
		endButton = new TextButton("Quit", skin, "default");
		endButton.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				Main.getInstance().setScreen(Main.getInstance().getMainScreen());
			}
		});
		escapeWindow = new Window("Options", skin, "default");
		escapeWindow.add(backButton).expand().row();
		escapeWindow.add(endButton).expand().row();
		escapeWindow.setFillParent(true); //TODO set size and scale it
		escapeWindow.center();
		escapeWindow.setVisible(false);
		
		score = new Label("Score", skin, "default");
		score.setColor(Color.BLACK);
		life = new Label("Life", skin, "default");
		life.setColor(Color.BLACK);
		phaseL = new Label("Phase", skin, "default");
		phaseL.setColor(Color.BLACK);
		
		shotButton = new Button(skin, "default");
		shotButton.setHeight(200); //TODO Scale it
		shotButton.setWidth(200);
		shotButton.addListener(new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				Main.getInstance().getStorage().ship.shot();
			}
		});
		if (!Main.getInstance().isAndroid) { shotButton.setVisible(false); }
		rect = new Rectangle(shotButton.getX(), shotButton.getY(), shotButton.getWidth(), shotButton.getHeight());
		
		showWaveInfo = new Task() {
			@Override
			public void run() {
				if (endlessMode) {
					waveNumber.setText("Wave " + phase);
					waveComment.setText(" ");
				} else {
					waveNumber.setText("Wave " + Phase.getNextPhase(currentPhase).byNumber);
					waveComment.setText(Phase.getNextPhase(currentPhase).getDescription());
				}
				waveTable.setVisible(true);
			}
		};
		
		stats = new Window("Stats", skin, "default");
		stats.add(score).bottom().row();
		stats.add(life).left().row(); //TODO Add hearth picture here
		stats.add(phaseL).left().row();
		stats.left();
		stats.top();
		
		locationTable.setFillParent(true);
		locationTable.add(stats).top().left().expand().row();
		locationTable.add(waveTable).align(Align.center).expand().row();
		locationTable.add(shotButton).bottom().left().expand();
		
//		stage.addActor(image);
//		stage.addActor(image2);
//		stage.setDebugAll(true);
		stage.addActor(locationTable);
	}

	@Override
	public void update(final float delta) { //TODO move some things info shot/enemy classes
		boolean checkA = game.getStorage().isLoading();
		boolean checkB = game.getStorage().isLoaded();
		if (checkA) { return; }
		if (!checkB) {
			game.getStorage().loadAssets();
			return;
		}
		
		if (!isStopped) {
			if (matrixForSpawning == null) 
				matrixForSpawning = Main.getInstance().getStorage().ship.transform.cpy();
			
			removedShots.clear();
			
			if (currentPhase == Phase.END) {
				game.setScreen(game.getMainScreen());
				
				GameScreen gameScreen = game.getGameScreeen();
				gameScreen.setCurrentPhase(Phase.ONE);
				game.getStorage().cleanUp();
				return;
			}
			
			game.getStorage().update(delta);
			
//			Main.log("ImagePosition: X: " + image.getX() + " Y: " + image.getY());
			
			if (image.getY() < -image.getHeight() - 16) {
				image.setPosition(image.getX(), image2.getY() + image2.getHeight());
			}
			if (image.getY() < -image.getHeight() - 16) {
				image2.setPosition(image2.getX(), image.getY() + image.getHeight());
			}
				
			image.setPosition(image.getX(), image.getY() - 25 * delta); //FIXME 10x
			image2.setPosition(image2.getX(), image2.getY() - 25 * delta);
			
			int size = game.getStorage().getCurrentEnemies().size;
			if (size == 0) {
				if (currentPhase == Phase.ONE && game.getStorage().ship.getScore() == 0) {
					Storage.getInstance().addEnemy(currentPhase.getEnemies());
				} else {
					if (spawnTask == null || spawnTask.getExecuteTimeMillis() == 0) {
						spawnTask = new Task() {
							@Override
							public void run() {
								if (endlessMode) {
									currentPhase = Phase.getRandomPhase(1, 25);
									phase++;
								} else {
									currentPhase = Phase.getNextPhase(currentPhase);
								}
								Enemy.IS_READY = false;
								game.getStorage().addEnemy(currentPhase.getEnemies());
								waveTable.setVisible(false);
								spawnTask = null;
							}
						};
						
						Timer.schedule(showWaveInfo, 2F);
						Timer.schedule(spawnTask, 4F);
					}
				}
			} else {
				if (game.getStorage().getCurrentEnemies().size > 0) {
					if (Math.random() < 0.01 * (size / 4)) {
						Enemy enemy = game.getStorage().getCurrentEnemies().random();
						if (!(enemy instanceof Meteorite || enemy instanceof ThrowingBoss || enemy instanceof SniperBoss)) {
							Shot shot = new Shot(EModel.ENEMY,
									enemy.transform.getTranslation(Utils.checker), false, 1);
							game.getStorage().addShot(shot);
						}
					}
					
					if (Math.random() < 0.01) {
						Enemy enemy = game.getStorage().getCurrentEnemies().random();
						if (!enemy.isMoving() && !(enemy instanceof Meteorite || enemy instanceof ThrowingBoss)) {
							enemy.goToShip(game.getStorage().ship);
						}
					}
					
					double deltaHeight = Math.sin(runningTime + delta) - Math.sin(runningTime);
					if (Enemy.IS_READY == null) {
						for (Enemy enemy : game.getStorage().getCurrentEnemies()) {
							if (!(enemy instanceof Meteorite || enemy instanceof ThrowingBoss)) {
								if (enemy.firstLoc != null) {
									enemy.firstLoc.x += deltaHeight;
								}
								
								if (!enemy.isMoving()) {
									enemy.transform.getTranslation(Utils.checker);
									Utils.checker.x += deltaHeight;
									enemy.transform.setTranslation(Utils.checker);
								}
							}
						}
					} else {
						if (Enemy.IS_READY) {
							Enemy.IS_READY = null;
						} else {
							for (Enemy enemy : game.getStorage().getCurrentEnemies()) {
								if (enemy.isFirstTurn()) {
									Enemy.IS_READY = false;
								} else {
									Enemy.IS_READY = true;
									break;
								}
							}
						}
					}
					runningTime += delta;
					//TODO add more "AI" behaviors here
				}
			}
			
			for (Shot shot : removedShots) {
				game.getStorage().remShot(shot);
			}
			
			score.setText("Score: " + game.getStorage().ship.getScore());
			life.setText(" Lifes: " + game.getStorage().ship.getLives());
			phaseL.setText(" Wave: " + currentPhase.byNumber);
			
		}
		camera.update();
	}

	@Override
	public void draw(final float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		if (game.getStorage().isLoaded() == true) {
			stage.act(delta);
			Batch b = stage.getBatch();
			if (b != null) {
				b.setProjectionMatrix(stage.getCamera().combined);
				
				b.begin();
				image2.draw(b, 1);
				image.draw(b, 1);
				b.end();
				
				batch.begin(camera);
				batch.render(game.getStorage().getCurrentModels(), environment);
				batch.render(game.getStorage().getCurrentShots(), environment);
				batch.render(game.getStorage().getCurrentEnemies(), environment);
				batch.end();
				
//				b.begin();
//				stats.draw(b, 1);
//				if (shotButton.isVisible()) shotButton.draw(b, 1);
//				if (waveTable.isVisible()) waveTable.draw(b, 1);
				stage.draw();
//				locationTable.draw(b, 1);
//				if (escapeWindow.isVisible()) escapeWindow.draw(b, 1);
//				b.end();
			}
		}
	}
	
	//TODO do it better
	@Override
	public void resize(final int width, final int height) {
		int multiplier = height / this.height;
//		int aspectRatio = width / height;
		
//		escapeWindow.scaleBy(multiplier);
//		escapeWindow.setSize(50 * multiplier, 100 * height);
		escapeWindow.setSize(escapeWindow.getHeight() * multiplier, escapeWindow.getWidth() * height);
		
		this.width = width;
		this.height = height;
		
		viewport.update(width, height);
//		camera.viewportHeight = 25 * multiplier;
//		camera.viewportWidth = 25 * multiplier * aspectRatio;
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		game.getStorage().dispose();
		batch.dispose();
		stage.dispose();
	}
	
	public void toggleStop() { 
		isStopped = !isStopped; 
		escapeWindow.setVisible(isStopped);
	}
	
	public void setStop(final boolean stop) { 
		isStopped = stop; 
		escapeWindow.setVisible(stop);
	}
	
	public Stage getCurrentStage() { return stage; }
	public Camera getCurrentCamera() { return camera; }
	public Button getShotButton() { return shotButton; }
	public Rectangle getShotButtonRect() { return rect; }
	public Phase getCurrentPhase() { return currentPhase; }
	public Viewport getCurrentViewport() { return viewport; }
	public void setCurrentPhase(final Phase phase) { this.currentPhase = phase; }
	public void setEndlessMode(final boolean isTrue) { this.endlessMode = isTrue; }
}
