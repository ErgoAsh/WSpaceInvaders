package me.wiedzmin137.spelldown.object.moveable;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Storage;
import me.wiedzmin137.spelldown.Storage.EModel;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Health;
import me.wiedzmin137.spelldown.object.Moveable;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.item.Eat;
import me.wiedzmin137.spelldown.object.item.Present;
import me.wiedzmin137.spelldown.object.item.Shot;
import me.wiedzmin137.spelldown.object.item.Upgrade;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Enemy extends ModelInstance implements Updateable, Moveable, Health {

	public static Boolean IS_READY;
	
	public Vector3 firstLoc;
	private Vector3 currentVelocity = new Vector3();
	private boolean isGoBack;
	private boolean firstTurn = false;
	private boolean isPresentEnemy = false;
	boolean isMoving = false;
	double health;
	Vector3 target;
	
	private Timer.Task waitTask;
	
	public Enemy(final Model copyFrom, final Vector3 position, final Vector3 target, final double health, final boolean isPresentEnemy) {
		this(copyFrom, position, health);
		
		isMoving = true;
		this.target = target;
		this.isPresentEnemy = isPresentEnemy;
		if (!(this instanceof Meteorite) && !(this instanceof SniperBoss) && !(this instanceof ThrowingBoss)) {
			firstTurn = true;
		}
	}
	
	public Enemy(final Model copyFrom, final Vector3 position, final Vector3 target, final double health) {
		this(copyFrom, position, health);
		
		isMoving = true;
		this.target = target;
		if (!(this instanceof Meteorite) && !(this instanceof SniperBoss) && !(this instanceof ThrowingBoss)) {
			firstTurn = true;
		}
	}
	
	public Enemy(final Model copyFrom, final Vector3 position, final double health) {
		super(copyFrom);

		this.transform.setTranslation(position);
		this.health = health;
		this.target = new Vector3();
		
		if (!(this instanceof Meteorite) && !(this instanceof SniperBoss) && !(this instanceof ThrowingBoss)) {
			transform.scl(0.25F);
		}
	}
	
	public Enemy(final Model copyFrom) {
		super(copyFrom);
	}
	
	public void goToShip(final Ship ship) {
		isMoving = true;
		ship.transform.getTranslation(target);
		
	}

	@Override
	public void update(final float delta) {
		if (health == 0) {
			Main.getInstance().getStorage().remEnemy(this);
			return;
		}
		
		if (Utils.isInRadius(this, Main.getInstance().getStorage().ship, transform.getScaleX())) {
			Ship ship = Main.getInstance().getStorage().ship;
			if (!ship.isImmune()) {
				ship.damage(null, 1);
				damage(null, 10);
			}
		}
		
		//TODO if (isOnPosition)
		move(delta);
	}
	
	@Override
	public void move(final float delta) {
		if (firstLoc == null) {
			this.firstLoc = new Vector3();
			transform.getTranslation(firstLoc); //TODO change it for first checked position
		}
		
		if (isMoving) {
			if (isGoBack) {
				target.set(firstLoc);
				Utils.checker = transform.getTranslation(Utils.checker).sub(target).scl(-1, 1, -1);
				
				if (Utils.checker.len2() < 0.01) {
					isGoBack = false;
					isMoving = false;
				} else {
					currentVelocity = Utils.checker.scl(delta);
					transform.trn(currentVelocity);
				}
			} else {
				Utils.checker = transform.getTranslation(Utils.checker);
				if (isPresentEnemy && Utils.checker.x > 16) {
					Main.log("Enemy: X: " + Utils.checker.x + " Y: " + Utils.checker.y);
					onDeath(null);
					return;
				}
				Utils.checker.sub(target).scl(-1, 1, -1);
				if (firstTurn) {
					if (Utils.checker.len2() < 0.01) {
						firstTurn = false;
						isMoving = false;
						return;
					} else {
						currentVelocity = Utils.checker.scl(delta);
						if (this instanceof SniperBoss || this instanceof ThrowingBoss) currentVelocity.scl(0.5F);
						transform.trn(currentVelocity);
					}
				} else {
					if (Utils.checker.len2() < 1) {
						if (firstTurn) {
							firstTurn = false;
							isMoving = false;
							return;
						}
						if (waitTask == null || waitTask.getExecuteTimeMillis() == 0) {
							waitTask = new Task() {
								@Override
								public void run() {
									if (Enemy.this instanceof SniperBoss || Enemy.this instanceof ThrowingBoss) {
										isMoving = false;
									} else {
										isGoBack = true;
										firstTurn = false;
									}
									waitTask = null;
								}
							};
							Timer.schedule(waitTask, 1F);
						} 
					} else {
						currentVelocity = Utils.checker.scl(delta);
						if (this instanceof SniperBoss || this instanceof ThrowingBoss) currentVelocity.scl(0.5F);
						transform.trn(currentVelocity);
					}
				}
			}
		}
	}
	
	@Override
	public void damage(final Shot shot, final double damage) {
		if (health - damage > 0) {
			health -= damage;
		} else {
			onDeath(shot);
		}
	}

	@Override
	public void onDeath(final Shot shot) {
		if (shot != null) {
			transform.getTranslation(Utils.checker);
			if (isPresentEnemy) {
				double another = Math.random();
				ModelInstance newModel;
				
				if (another > 0.75) {
					newModel = new Upgrade(EModel.UPGRADE, Utils.checker);
				} else if (another > 0.50) {
					newModel = new Present(EModel.PRESENT_RED, Utils.checker);
				} else if (another > 0.25) {
					newModel = new Present(EModel.PRESENT_ORANGE, Utils.checker);
				} else {
					newModel = new Present(EModel.PRESENT_GREEN, Utils.checker);
				}
				Storage.getInstance().add(newModel);
			} else {
				if (Math.random() < 0.1) {
					double another = Math.random();
					ModelInstance newModel;
					
					if (another > 0.75) {
						newModel = new Present(EModel.PRESENT_RED, Utils.checker);
					} else if (another > 0.50) {
						newModel = new Present(EModel.PRESENT_ORANGE, Utils.checker);
					} else if (another > 0.25) {
						newModel = new Present(EModel.PRESENT_ORANGE, Utils.checker);
					} else {
						newModel = new Upgrade(EModel.UPGRADE, Utils.checker);
//						newModel = new Present(EModel.PRESENT_GREEN, Utils.checker);
					}
					Storage.getInstance().add(newModel);
				} else {
					Vector3 V1 = new Vector3();
					Vector3 V2 = new Vector3();
					shot.transform.getTranslation(V1);
					transform.getTranslation(V2);	
					
					//TODO change amount per type, change model	
					ModelInstance item = new Eat(EModel.EAT.model, Utils.checker, V1.sub(V2).scl(-1), 25);
					Storage.getInstance().add(item);
				}
			}
			
//			Main.getInstance().getStorage().add(new Present(EModel.PRESENT_ORANGE, Utils.checker));
			Storage.getInstance().ship.addScore(10); 
		}
		
		Storage.getInstance().remEnemy(this);
	}
	
	@Override
	public double getHealth() { return health; }
	public boolean isMoving() { return isMoving; }
	public boolean isFirstTurn() { return firstTurn; }
}
