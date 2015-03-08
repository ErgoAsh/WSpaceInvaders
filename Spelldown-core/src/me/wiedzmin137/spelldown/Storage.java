package me.wiedzmin137.spelldown;

import me.wiedzmin137.spelldown.object.Enemy;
import me.wiedzmin137.spelldown.object.Ship;
import me.wiedzmin137.spelldown.object.Shot;
import me.wiedzmin137.spelldown.object.Updateable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;

public class Storage {
	
	@SuppressWarnings("unused")
	private final Main game;
	
	private final G3dModelLoader objLoader;
	
	private boolean isLoading = false;
	private boolean isLoaded = false;
	
	public Ship ship;
	
	private Model enemyModel1;
	private Model shipModel;
	private Model shotModel;
	private Model eatableModel;
	private Model upgradeModel;
	
	private Array<ModelInstance> currentModels = new Array<ModelInstance>();
	private Array<Enemy> currentEnemies = new Array<Enemy>();
	private Array<Shot> shotInstances = new Array<Shot>(true, 64);
	
	public Storage(final Main main) {
		this.game = main;
		
		objLoader = new G3dModelLoader(new UBJsonReader());
	}
	
	public void loadAssets() {
		isLoading = true;
		
		shipModel = objLoader.loadModel(Gdx.files.internal("data/ship.g3db"));
		
		//FIXME temporal thing begin
        ModelBuilder modelBuilder = new ModelBuilder();
        
        enemyModel1 = shotModel = modelBuilder.createBox(1f, 1f, 1f, 
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                Usage.Position | Usage.Normal);
        shotModel = modelBuilder.createBox(0.5f, 0.5f, 0.5f, 
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                Usage.Position | Usage.Normal);
        eatableModel = modelBuilder.createBox(0.75f, 0.75f, 0.75f, 
                new Material(ColorAttribute.createDiffuse(Color.YELLOW)),
                Usage.Position | Usage.Normal);
        upgradeModel = modelBuilder.createBox(0.75f, 0.75f, 0.75f, 
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                Usage.Position | Usage.Normal);
        //FIXME temporal thing ends
		
		loadObjects();

	}
	
	public void loadObjects() {
		ship = new Ship(shipModel);
		ship.transform.rotate(0, 1, 0, 180);
		currentModels.add(ship);

		isLoading = false;
		isLoaded = true;
	}
	
	public void update(final float delta) { 
		for (ModelInstance model : currentModels) {
			if (model instanceof Updateable) {
				((Updateable) model).update(delta);
			}
		}
		
		for (Shot shot : shotInstances) {
			shot.update(delta);
		}
	}
	
	public void cleanUp() {
		removeAllShots();
		removeAllEnemies();
		removeAllInstances();

		ship.transform.translate(0, 1, 0);
		ship.setMoveLeft(false);
		ship.setMoveRight(false);
		ship.setMoveUp(false);
		ship.setMoveDown(false);
		ship.setShot(false);
		ship.setScore(0);
		ship.setLives(3);
		ship.setImmunity(false);
		currentModels.add(ship);
	}
	
	public void dispose() {
		enemyModel1.dispose();;
		shipModel.dispose();
		shotModel.dispose();;
		eatableModel.dispose();;
		upgradeModel.dispose();;
	}
	
	public boolean isLoading() { return isLoading; }
	public boolean isLoaded() { return isLoaded; }
	
	public Array<Shot> getCurrentShots() { return shotInstances; }
	public void addShot(final Shot model) { shotInstances.add(model); }
	public void addShot(final Shot[] model) { shotInstances.addAll(model); }
	public void remShot(final Shot model) { shotInstances.removeValue(model, true); }
	public void removeAllShots() { shotInstances.clear(); }
	
	public Array<ModelInstance> getCurrentModels() { return currentModels; }
	public void add(final ModelInstance model) { currentModels.add(model); }
	public void add(final Array<ModelInstance> model) { currentModels.addAll(model); }
	public void remInstance(final ModelInstance model) { currentModels.removeValue(model, true); }
	public void removeAllInstances() { currentModels.clear(); }
	
	public Array<Enemy> getCurrentEnemies() { return currentEnemies; }
	public void addEnemy(final Enemy model) { currentEnemies.add(model); }
	public void addEnemy(final Enemy[] model) { currentEnemies.addAll(model); }
	public void remEnemy(final Enemy model) { currentEnemies.removeValue(model, true); }
	public void removeAllEnemies() { currentEnemies.clear(); }
	
	public Model getEnemyModel() { return enemyModel1; }
	public Model getShotModel() { return shotModel; }
	public Model getEatableModel() { return eatableModel; }
	public Model getUpgradeModel() { return upgradeModel; }
}
