package me.wiedzmin137.spelldown.object.item;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Storage.EModel;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Health;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.moveable.Ship;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Shot extends ModelInstance implements Updateable {

	public static float SHOT_VELOCITY = 10; //TODO make it configurable
	
	private EModel model;
	private boolean isShipShot;
	private double damage;
	private float axisX = 0;
	private Vector3 dir;
	
	public Shot(final EModel model, final Vector3 position, final Vector3 target, final boolean isShipShot, final double damage) {
		this(model, position, isShipShot, damage);
		
		this.dir = position.sub(target).scl(-1).nor().cpy();
	}
	
	public Shot(final EModel model, final Vector3 position, final boolean isShipShot, final double damage, final float axisX) {
		this(model, position, isShipShot, damage);
		
		this.axisX = axisX;
	}
	
	public Shot(final EModel model, final Vector3 position, final boolean isShipShot, final double damage) {
		super(model.model);
		
		transform.setTranslation(position);
		this.isShipShot = isShipShot;
		this.damage = damage;
		this.model = model;
	}

	@Override
	public void update(final float delta) {
		if (transform.getTranslation(Utils.checker).x < -30 || transform.getTranslation(Utils.checker).x > 15) {
			Main.getInstance().getStorage().remShot(this);
			return;
		}
		
		if (isShipShot) { //TODO get type for both
			transform.trn(axisX * delta, 0, -SHOT_VELOCITY * delta);
		} else {
			if (dir != null) {
				transform.trn(dir.x * 3 * delta, 0, dir.z * 3 * delta);
			} else {
				transform.trn(axisX * delta, 0, (SHOT_VELOCITY / 2) * delta);
			}
		}
		if (isShipShot) {
			for (ModelInstance enemy : Main.getInstance().getStorage().getCurrentEnemies()) { //TODO getCurrentDestroyables instead
				if (Utils.isInRadius(this, enemy, Ship.SHIP_RADIUS)) {
					((Health) enemy).damage(this, damage);
					Main.getInstance().getStorage().remShot(this);
				}
			}
		} else {
			Ship ship = Main.getInstance().getStorage().ship;
			if (!ship.isImmune()) {
				if (Utils.isInRadius(this, ship, Ship.SHIP_RADIUS * 2)) {
					ship.damage(this, damage);
					
					Main.getInstance().getStorage().remShot(this);
				}
			}
		}
	}
	
	public boolean isShipShot() { return isShipShot; }
}
