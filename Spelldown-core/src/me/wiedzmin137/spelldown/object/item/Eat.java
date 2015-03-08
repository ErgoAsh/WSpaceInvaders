package me.wiedzmin137.spelldown.object.item;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Ship;
import me.wiedzmin137.spelldown.object.Updateable;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Eat extends ModelInstance implements Updateable {
	
	private int value = 0;
	private int jumps = 0;
	private Vector3 dir;
	
	public final static Vector3 tmpV = new Vector3();
	
	public Eat(final Model model, final Matrix4 transform, final Vector3 dir, final int value) {
		super(model, transform);
		
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
		
		if (transform.getValues()[14] > 1) {
			if (jumps == 3) {
				Main.getInstance().getStorage().remInstance(this);
			} else {
				dir.scl(0.25F, 0, -0.5F);
				jumps++;
			}
		}
		
		transform.getTranslation(tmpV);
		if (tmpV.x > 9 || tmpV.x < -9) {
			dir.scl(-0.5F, 0, 1);
		}
		
		dir.add(0, 0, 0.5F * delta);
		transform.trn(dir);
	}

}
