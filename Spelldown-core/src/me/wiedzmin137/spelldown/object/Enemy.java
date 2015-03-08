package me.wiedzmin137.spelldown.object;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Utils;
import me.wiedzmin137.spelldown.object.Weapon.WeaponModel;
import me.wiedzmin137.spelldown.object.item.Eat;
import me.wiedzmin137.spelldown.object.item.Present;
import me.wiedzmin137.spelldown.object.item.Upgrade;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Enemy extends ModelInstance implements Updateable, Health {

	private double health;
	
	public Enemy(final Model copyFrom, final Matrix4 transform, final double health) {
		super(copyFrom, transform);

		this.health = health;
	}
	
	public Enemy(final Model copyFrom) {
		super(copyFrom);
	}

	@Override
	public void update(final float delta) {
		if (health == 0) {
			Main.getInstance().getStorage().remEnemy(this);
		}
		//TODO if (isOnPosition)
		//TODO move left or right
	}
	
	@Override
	public void damage(final Shot shot, final double damage) {
		if (health - damage > 0) {
			health -= damage;
		} else {
			onDeath(shot);
		}

	}

	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void onDeath(final Shot shot) {	
		if (Utils.isInRadius(this, Main.getInstance().getStorage().ship, 1F)) {
			Main.getInstance().getStorage().ship.damage(null, 1);
		}
		if (Math.random() < 0.9) {
			double another = Math.random();
			ModelInstance newModel;
			
			if (another > 0.75) {
				newModel = new Upgrade(Main.getInstance().getStorage().getUpgradeModel(), transform.cpy());
			} else if (another > 0.50) {
				newModel = new Present(WeaponModel.RED.model, transform.cpy().scale(2, 2, 2), WeaponModel.RED);
			} else if (another > 0.25) {
				newModel = new Present(WeaponModel.ORANGE.model, transform.cpy().scale(2, 2, 2), WeaponModel.ORANGE);
			} else {
				newModel = new Present(WeaponModel.GREEN.model, transform.cpy().scale(2, 2, 2), WeaponModel.GREEN);
			}
			Main.getInstance().getStorage().add(newModel);
		} else {
			Vector3 V1 = new Vector3();
			Vector3 V2 = new Vector3();
			shot.transform.getTranslation(V1);
			transform.getTranslation(V2);	
			
			ModelInstance item = new Eat(Main.getInstance().getStorage().getEatableModel(), transform.cpy(), V1.sub(V2).scl(-1), 50);
			//TODO change amount per type, change model	
			Main.getInstance().getStorage().add(item);
		}
		
		Main.getInstance().getStorage().ship.setScore(Main.getInstance().getStorage().ship.getScore() + 250); 
		Main.getInstance().getStorage().remEnemy(this);
	}
	
}
