package me.wiedzmin137.spelldown.object.item;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Ship;
import me.wiedzmin137.spelldown.object.Shot;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.Weapon;
import me.wiedzmin137.spelldown.object.Weapon.WeaponModel;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;

public class Present extends ModelInstance implements Updateable {
	
	private WeaponModel color;

	public Present(final Model model, final Matrix4 transform, final WeaponModel color) {
		super(model, transform);
		
		this.color = color;
	}

	@Override
	public void update(final float delta) {
		Ship ship = Main.getInstance().getStorage().ship;
		if (Utils.isInRadius(this, ship, Ship.SHIP_RADIUS)) {
			Weapon curr = ship.getWeapon();
			if (curr.color == color) {
				ship.setWeapon(Weapon.getNextLevel(curr));
				Main.getInstance().getStorage().remInstance(this);
				return;
			} else {
				ship.setWeapon(Weapon.getWeapon(color, curr.level));
			}
		} 
		
		if (transform.getValues()[14] >= 15) { //TODO change value per resolution or check it out
			Main.getInstance().getStorage().remInstance(this);
			return;
		}
		
		transform.trn(0, 0, (Shot.SHOT_VELOCITY / 2) * delta); //TODO change it because shot will be more random
	}

}
