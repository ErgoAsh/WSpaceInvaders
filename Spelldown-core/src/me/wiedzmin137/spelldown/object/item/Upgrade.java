package me.wiedzmin137.spelldown.object.item;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Ship;
import me.wiedzmin137.spelldown.object.Shot;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.Weapon;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;

public class Upgrade extends ModelInstance implements Updateable {

	public Upgrade(final Model model, final Matrix4 pos) {
		super(model, pos);
	}

	@Override
	public void update(final float delta) {
		Ship ship = Main.getInstance().getStorage().ship;
		if (Utils.isInRadius(this, ship, Ship.SHIP_RADIUS)) {
			ship.setWeapon(Weapon.getNextLevel(ship.getWeapon()));
			Main.getInstance().getStorage().remInstance(this);
			return;
		} 
		
		if (transform.getValues()[14] >= 15) { //TODO change value per resolution or check it out
			Main.getInstance().getStorage().remInstance(this);
			return;
		}
		
		transform.trn(0, 0, (Shot.SHOT_VELOCITY / 2) * delta); //TODO change it because shot should be more random
	}

}
