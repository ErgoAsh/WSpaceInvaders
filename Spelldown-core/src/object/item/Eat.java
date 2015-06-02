package me.wiedzmin137.spelldown.object.item;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Updateable;
import me.wiedzmin137.spelldown.object.moveable.Ship;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Eat extends ModelInstance implements Updateable {
	
	private int value = 0;
	private int jumps = 0;
	private Vector3 dir;
	
	public Eat(final Model model, final Vector3 position, final Vector3 dir, final int value) {
		super(model);
		
		transform.setTranslation(position);
		this.value = value;
		this.dir = dir.scl(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public void update(final float delta) {
		Ship ship = Main.getInstance().getStorage().ship;
		if (Utils.isInRadius(this, ship, 0.75F)) {
			ship.setScore(ship.getScore() + value);
			Main.getInstance().getStorage().remInstance(this);
			return;
		}
		
		transform.getTranslation(Utils.checker);
		if (Utils.checker.z > 15) {
			if (jumps == 3) {
				Main.getInstance().getStorage().remInstance(this);
			} else {
				transform.getTranslation(Utils.checker);
				transform.setTranslation(Utils.checker.x, Utils.checker.y, 15);
				dir.scl(0.25F, 0, -0.5F);
				jumps++;
			}
		}
		
		if (Utils.checker.x > 12) {
			transform.setTranslation(12, Utils.checker.y, Utils.checker.z);
			dir.scl(-0.5F, 0, 1);
		}
		if (Utils.checker.x < -12) {
			transform.setTranslation(-12, Utils.checker.y, Utils.checker.z);
			dir.scl(-0.5F, 0, 1);
		}
		
		dir.add(0, 0, 0.5F * delta);
		transform.trn(dir);
	}

}
