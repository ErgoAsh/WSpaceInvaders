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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class ThrowingBoss extends Enemy {

	public ThrowingBoss(final Model copyFrom, final Vector3 position, final Vector3 target, final double health) {
		super(copyFrom, position, target, health);
	}
	
	@Override
	public void update(final float delta) {
		if (Math.random() < 0.025) { //TODO set to another color
			Main.getInstance().getStorage().addShot(new Shot(
					EModel.ENEMY, 
					transform.getTranslation(Utils.checker).cpy(), 
					false, 
					1.0D));
		}
		super.update(delta);
	}

	@Override
	public void move(final float delta) {
		transform.getTranslation(Utils.checker);
		if (!isMoving) {
			target.set(MathUtils.random(-11, 11), 0, MathUtils.random(-10, 5));
			isMoving =  true;
		}
		super.move(delta / 2);
	}

	@Override
	public void onDeath(final Shot shot) {
		double r = Math.random();
		ModelInstance newModel;
		if (r > 0.75) {
			newModel = new Present(EModel.PRESENT_RED, Utils.checker);
		} else if (r > 0.50) {
			newModel = new Present(EModel.PRESENT_ORANGE, Utils.checker);
		} else if (r > 0.25) {
			newModel = new Present(EModel.PRESENT_ORANGE, Utils.checker);
		} else {
			newModel = new Upgrade(EModel.UPGRADE, Utils.checker);
//			newModel = new Present(EModel.PRESENT_GREEN, Utils.checker);
		}
		Storage.getInstance().add(newModel);
		
		Main.getInstance().getStorage().ship.addScore(100);
		Storage.getInstance().remEnemy(this);
	}
}
