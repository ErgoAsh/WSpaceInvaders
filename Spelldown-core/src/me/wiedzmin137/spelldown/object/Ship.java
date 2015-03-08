package me.wiedzmin137.spelldown.object;

import me.wiedzmin137.spelldown.Main;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Ship extends ModelInstance implements Updateable, Health {
	
	public final static float SHIP_RADIUS = 0.5F;
	public final static float SHIP_ACCELERATION = 1F;
	public final static float SHIP_MAX_VELOCITY = 5F;
	public final static double SHIP_HEALTH = 1;
	private static Vector3 oldVelocity = new Vector3();
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	
	private boolean isShooting;
	private boolean canShot = true;
	
	private int score;
	private int life = 3;
	private boolean isImmune = false;
	private Weapon weapon = Weapon.RED_ONE;
	
	private final Model model;
	
	private Vector3 currentVelocity = new Vector3();

	public Ship(final Model model) {
		super(model);
		this.model = model;
	}
	
	@Override
	public void update(final float delta) {
		oldVelocity.set(currentVelocity);

		if (moveLeft)
			currentVelocity.x = -Math.min(oldVelocity.x + SHIP_ACCELERATION * delta, SHIP_MAX_VELOCITY);
		
		if (moveRight) 
			currentVelocity.x = Math.min(oldVelocity.x + SHIP_ACCELERATION * delta, SHIP_MAX_VELOCITY);
		
		if (moveUp) 
			currentVelocity.z = -Math.min(oldVelocity.z + SHIP_ACCELERATION * delta, SHIP_MAX_VELOCITY);
		
		if (moveDown) 
			currentVelocity.z = Math.min(oldVelocity.z + SHIP_ACCELERATION * delta, SHIP_MAX_VELOCITY);

		Main.getInstance().log("Velocity Before: " + oldVelocity.x + " " + oldVelocity.z);
		
		transform.trn(oldVelocity.add(currentVelocity).scl(delta / 2));

		Main.getInstance().log("Velocity: " + oldVelocity.x + " " + oldVelocity.z);

		if (isShooting) {
			if (canShot) {
				canShot = false;
				 
				weapon.shot(transform.cpy());
				
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						canShot = true;
					}
				}, weapon.cooldown);
				
			}
		}
	}
	
	@Override
	public void damage(final Shot shot, final double damage) {
		if (life != 0) {
			life--;
			isImmune = true;
			
			//TODO add ring on ship to show its immunity
			
			Timer.schedule(new Task() {
				@Override
				public void run() {
					isImmune = false;
				}
			}, 3F);
		} else {
			onDeath(shot);
		}
	}

	@Override
	public double getHealth() {
		return SHIP_HEALTH;
	}

	@Override
	public void onDeath(final Shot shot) {
		Main.getInstance().getGameScreeen().setCurrentPhase(Phase.END);
	}

	public Model getModel() { return model; }
	public Weapon getWeapon() { return weapon; }
	public int getScore() { return score; }
	public int getLives() { return life; }
	
	public boolean isImmune() { return isImmune; }
	public boolean isMoveLeft() { return moveLeft; }
	public boolean isMoveRight() { return moveRight; }
	public boolean isMoveUp() { return moveUp; }
	public boolean isMoveDown() { return moveDown; }
	
	public void setMoveLeft(final boolean moveLeft) { this.moveLeft = moveLeft; }
	public void setMoveRight(final boolean moveRight) { this.moveRight = moveRight;}
	public void setMoveUp(final boolean moveUp) { this.moveUp = moveUp; }
	public void setMoveDown(final boolean moveDown) { this.moveDown = moveDown; }
	public void setShot(final boolean isShooting) { this.isShooting = isShooting; }
	public void setScore(final int newScore) { this.score = newScore; }
	public void setLives(final int lifes) { this.life = lifes; }
	public void setImmunity(final boolean isImmune) { this.isImmune = isImmune; }
	public void setWeapon(final Weapon weapon) { this.weapon = weapon; }
}
