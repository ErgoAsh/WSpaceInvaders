package me.wiedzmin137.spelldown.object.item;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Storage.EModel;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.Weapon;
import me.wiedzmin137.spelldown.object.moveable.Ship;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Present extends ModelInstance implements Updateable {
	
	private EModel model;

	public Present(final EModel model, final Vector3 position) {
		super(model.model);
		
		transform.setTranslation(position);
		this.model = model;
	}

	@Override
	public void update(final float delta) {
		Ship ship = Main.getInstance().getStorage().ship;
		if (Utils.isInRadius(this, ship, Ship.SHIP_RADIUS)) {
			Weapon curr = ship.getWeapon();
			if (curr.color.equals(EModel.getWeaponFromPresent(model))) {
				ship.setWeapon(Weapon.getNextLevel(curr));
			} else {
				ship.setWeapon(Weapon.getWeapon(EModel.getWeaponFromPresent(model), curr.level));
			}
			Main.getInstance().getStorage().remInstance(this);
			return;
		} 
		
		transform.getTranslation(Utils.checker);
		if (Utils.checker.y >= 15) {
			Main.getInstance().getStorage().remInstance(this);
			return;
		}
		
		transform.trn(0, 0, (Shot.SHOT_VELOCITY / 3) * delta);
	}

}
