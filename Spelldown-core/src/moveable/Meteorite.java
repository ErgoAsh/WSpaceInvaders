package me.wiedzmin137.spelldown.object.moveable;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Storage;
import me.wiedzmin137.spelldown.Storage.EModel;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.item.Present;
import me.wiedzmin137.spelldown.object.item.Shot;
import me.wiedzmin137.spelldown.object.item.Upgrade;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Meteorite extends Enemy {
	
	private short size;
	private int speed;

	public Meteorite(final Model copyFrom, final Vector3 position, final Vector3 target, final int health, final short size, final int speed) {
		super(copyFrom, position, target, health);
		this.size = size;
		this.speed =  speed;
	}

	@Override
	public void move(final float delta) {
		transform.getTranslation(Utils.checker);
		if (Utils.checker.x < -30 || Utils.checker.x > 13 || Utils.checker.z > 20 || Utils.checker.z < -50) {
			onDeath(null);
			return;
		}
		
		transform.rotate(target, 50 * delta);
		transform.trn(target.x * speed * delta, 0, target.z * speed * delta);
	}
	
	@Override
	public void onDeath(final Shot shot) {
		if (shot != null) {
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
//					newModel = new Present(EModel.PRESENT_GREEN, Utils.checker);
				}
				Storage.getInstance().add(newModel);
			}
			Main.getInstance().getStorage().ship.addScore(5 * size);
		}
		Storage.getInstance().remEnemy(this);
	}
}
