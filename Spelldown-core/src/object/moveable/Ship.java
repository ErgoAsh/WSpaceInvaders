package me.wiedzmin137.spelldown.object.moveable;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Health;
import me.wiedzmin137.spelldown.object.Moveable;
import me.wiedzmin137.spelldown.object.Phase;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.Weapon;
import me.wiedzmin137.spelldown.object.item.Shot;
import me.wiedzmin137.spelldown.screen.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ship extends ModelInstance implements Updateable, Moveable, Health {
	
	public final static float SHIP_RADIUS = 0.5F;
	public final static float SHIP_VELOCITY = 5F;
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	
	private boolean canShot = true;
	private boolean canMove = true;
	
	private int score;
	private int life = 3;
	private boolean isImmune = false;
	private Weapon weapon = Weapon.RED_ONE;
	private short weaponLevel = 1;
	
	private final Model model;
	
	private Vector3 currentVelocity = new Vector3();
	private Vector3 touchLoc = new Vector3();
	private Rectangle touchRect = new Rectangle();

	public Ship(final Model model) {
		super(model);
		this.model = model;
	}
	
	@Override
	public void update(final float delta) {
		move(delta);
	}
	
	@Override
	public void move(final float delta) {
		if (!canMove) {
			return;
		}
		
		currentVelocity.set(0, 0, 0);
		transform.getTranslation(Utils.checker);
		if (Main.getInstance().isAndroid) { //TODO change negation
			if (Gdx.input.isTouched()) {
				touchLoc.x = Gdx.input.getX();
				touchLoc.y = Gdx.input.getY();
				touchLoc.z = 0;
				touchRect.set(touchLoc.x - 5, touchLoc.y - 5, 10, 10);
				
				GameScreen screen = (GameScreen) Main.getInstance().getActualScreen();
				if (screen.getShotButtonRect().overlaps(touchRect)) {
					return;
				}
				
				Viewport view = screen.getCurrentViewport();
				view.unproject(touchLoc.set(touchLoc.x, touchLoc.y, 0));
//				cam.unproject(touchLoc.set(touchLoc.x, touchLoc.y, 0), 0, 0, cam.viewportWidth, cam.viewportHeight);
				
				currentVelocity.add(-(Utils.checker.x - touchLoc.x) * delta * 2, 0,
					-(Utils.checker.z - touchLoc.z) * delta * 2);
//				Main.log("Touch: X: " + touchLoc.x + " Y: " + touchLoc.y + " Z: " + touchLoc.z,
//					"Ship: X: " + Utils.checker.x + " Y: " + Utils.checker.y + " Z:" + Utils.checker.z);
			}
		} else {
			if (moveLeft ^ moveRight) {
				if (moveLeft) currentVelocity.x = -SHIP_VELOCITY * delta;
				if (moveRight) currentVelocity.x = SHIP_VELOCITY * delta;
			}
			
			if (moveUp ^ moveDown) {
				if (moveUp) currentVelocity.z = -SHIP_VELOCITY * 2 * delta;
				if (moveDown) currentVelocity.z = SHIP_VELOCITY * 2 * delta;
			}
		}
		
		if ((Utils.checker.x > 12 && currentVelocity.x > 0) || (Utils.checker.x < -12 && currentVelocity.x < 0)) {
			currentVelocity.x = 0;
		}
		if ((Utils.checker.z > 14.5 && currentVelocity.z > 0) || (Utils.checker.z < -13 && currentVelocity.z < 0)) {
			currentVelocity.z = 0;
		}
		
		transform.trn(currentVelocity);
	}
	
	public void shot() {
		if (canShot) {
			canShot = false;
			 
			weapon.shot(transform.getTranslation(Utils.checker));
			
			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {
					canShot = true;
				}
			}, weapon.cooldown);
			
		}
	}
	
	@Override
	public void damage(final Shot shot, final double damage) {
		if (life <= 0) {
			onDeath(shot);
			return;
		}
		life--;
		isImmune = true;
		
		//TODO add ring on ship to show its immunity
		canMove = false;
		canShot = true;
		transform.setTranslation(0, 0, 10);
		//TODO add explosion here
		Timer.schedule(new Task() {
			@Override
			public void run() {
				canMove = true;
				canShot = true;
			}
		}, 0.5F);
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				isImmune = false;
			}
		}, 3F);
	}

	@Override
	public void onDeath(final Shot shot) {
		Main.getInstance().getGameScreeen().setCurrentPhase(Phase.END); //TODO if other isn't dead
	}
	
	@Override
	public double getHealth() { return life; }

	public Model getModel() { return model; }
	public Weapon getWeapon() { return weapon; }
	public Vector3 getTouchLoc() { return touchLoc; }
	public int getWeaponLevel() { return weaponLevel; }
	public int getScore() { return score; }
	public int getLives() { return life; }
	
	public boolean isImmune() { return isImmune; }
	public boolean isMoveLeft() { return moveLeft; }
	public boolean isMoveRight() { return moveRight; }
	public boolean isMoveUp() { return moveUp; }
	public boolean isMoveDown() { return moveDown; }
	
	public void addScore (final int addScore) { this.score += addScore; }
	public void setMoveLeft(final boolean moveLeft) { this.moveLeft = moveLeft; }
	public void setMoveRight(final boolean moveRight) { this.moveRight = moveRight;}
	public void setMoveUp(final boolean moveUp) { this.moveUp = moveUp; }
	public void setMoveDown(final boolean moveDown) { this.moveDown = moveDown; }
	public void setScore(final int newScore) { this.score = newScore; }
	public void setWeaponLevel(final int level) { this.weaponLevel = (short) level; }
	public void setLives(final int lifes) { this.life = lifes; }
	public void setImmunity(final boolean isImmune) { this.isImmune = isImmune; }
	public void setWeapon(final Weapon weapon) { this.weapon = weapon; }
}
