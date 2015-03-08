package me.wiedzmin137.spelldown.object;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;

public class Shot extends ModelInstance implements Updateable {

	public static float SHOT_VELOCITY = 10; //TODO make it configurable
	
	private boolean isShipShot;
	private double damage;
	private float axisX = 0;
	
	public Shot(final Model model, final Matrix4 position, final boolean isShipShot, final double damage, final float axisX) {
		this(model, position, isShipShot, damage);
		
		this.axisX = axisX;
	}
	
	public Shot(final Model model, final Matrix4 position, final boolean isShipShot, final double damage) {
		super(model, position);
		
		this.isShipShot = isShipShot;
		this.damage = damage;
	}

	@Override
	public void update(final float delta) {
		if (transform.getValues()[14] <= -30 || transform.getValues()[14] >= 15) { //TODO change value per resolution or check it out
			Main.getInstance().getStorage().remInstance(this);
			return;
		}
		
		if (isShipShot) //TODO get type for both
			transform.trn(axisX * delta, 0, -SHOT_VELOCITY * delta);
		else
			transform.trn(axisX * delta, 0, (SHOT_VELOCITY / 2) * delta);
		
		if (isShipShot) {
			for (ModelInstance model : Main.getInstance().getStorage().getCurrentEnemies()) { //TODO getCurrentDestroyables instead
				if (Utils.isInRadius(this, model, Ship.SHIP_RADIUS)) {
					((Enemy) model).damage(this, damage);
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
