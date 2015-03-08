package me.wiedzmin137.spelldown.object;

import me.wiedzmin137.spelldown.Main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;

public enum Weapon {

	RED_ONE(1, 0.25F, WeaponModel.RED) {
		@Override
		public void shot(final Matrix4 pos) {
			Main.getInstance().getStorage().addShot(new Shot(WeaponModel.RED.model, pos, true, 1));
		} //TODO send info to shot that it is RED one to make proper speed and damage multiplier etc.
	},
	RED_TWO(2, 0.25F, WeaponModel.RED) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	RED_THREE(3, 0.25F, WeaponModel.RED) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[3];
			shots[0] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.75F, 0, 0), true, 1, -0.5F);
			shots[1] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0, 0, -0.4F), true, 1);
			shots[2] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.75F, 0, 0), true, 1, 0.5F);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	RED_FOUR(4, 0.25F, WeaponModel.RED) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[4];
			shots[0] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.75F, 0, 0), true, 1, -1F);
			shots[1] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.4F, 0, -0.25F), true, 1);
			shots[2] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.4F, 0, -0.25F), true, 1);
			shots[3] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.75F, 0, 0), true, 1, 1F);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	RED_FIVE(5, 0.25F, WeaponModel.RED) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[6];
			shots[0] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.75F, 0, 0.5F), true, 1, -4F);
			shots[1] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.75F, 0, 0.25F), true, 1, -2F);
			shots[2] = new Shot(WeaponModel.RED.model, pos.cpy().trn(-0.4F, 0, -0.25F), true, 1);
			shots[3] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.4F, 0, -0.25F), true, 1);
			shots[4] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.75F, 0, 0.25F), true, 1, 2F);
			shots[5] = new Shot(WeaponModel.RED.model, pos.cpy().trn(0.75F, 0, 0.5F), true, 1, 4F);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	
	ORANGE_ONE(1, 0.15F, WeaponModel.ORANGE) {
		@Override
		public void shot(final Matrix4 pos) {
			Main.getInstance().getStorage().addShot(new Shot(WeaponModel.ORANGE.model, pos, true, 1));
		}
	},
	ORANGE_TWO(2, 0.15F, WeaponModel.ORANGE) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	ORANGE_THREE(3, 0.15F, WeaponModel.ORANGE) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	ORANGE_FOUR(4, 0.15F, WeaponModel.ORANGE) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	ORANGE_FIVE(5, 0.15F, WeaponModel.ORANGE) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.ORANGE.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	
	GREEN_ONE(1, 0.45F, WeaponModel.GREEN) {
		@Override
		public void shot(final Matrix4 pos) {
			Main.getInstance().getStorage().addShot(new Shot(WeaponModel.GREEN.model, pos, true, 1));
		}
	},
	GREEN_TWO(2, 0.45F, WeaponModel.GREEN) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	GREEN_THREE(3, 0.45F, WeaponModel.GREEN) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	GREEN_FOUR(4, 0.45F, WeaponModel.GREEN) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	GREEN_FIVE(5, 0.45F, WeaponModel.GREEN) {
		@Override
		public void shot(final Matrix4 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(WeaponModel.GREEN.model, pos.cpy().trn(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	};
	
	public int level;
	public float cooldown;
	public WeaponModel color;
	public abstract void shot(Matrix4 pos);
	
	private Weapon(final int level, final float cooldown, final WeaponModel color) { //TODO deal more damages per levels
		this.level = level;
		this.cooldown = cooldown;
		this.color = color;
	}
	
	public static Weapon getWeapon(final WeaponModel color, final int level) {
		switch (color) {
		case RED: 
			switch (level) {
			case 1: return RED_ONE;
			case 2: return RED_TWO;
			case 3: return RED_THREE;
			case 4: return RED_FOUR;
			case 5: return RED_FIVE;
			default: return RED_ONE;
			}
		case ORANGE:
			switch (level) {
			case 1: return ORANGE_ONE;
			case 2: return ORANGE_TWO;
			case 3: return ORANGE_THREE;
			case 4: return ORANGE_FOUR;
			case 5: return ORANGE_FIVE;
			default: return ORANGE_ONE;
			}
		case GREEN:
			switch (level) {
			case 1: return GREEN_ONE;
			case 2: return GREEN_TWO;
			case 3: return GREEN_THREE;
			case 4: return GREEN_FOUR;
			case 5: return GREEN_FIVE;
			default: return GREEN_ONE;
			}
		default: return RED_ONE;
		}
	}
	
	public static Weapon getNextLevel(final Weapon weapon) {
		switch (weapon) {
		case RED_ONE: return RED_TWO;
		case RED_TWO: return RED_THREE ;
		case RED_THREE: return RED_FOUR;
		case RED_FOUR: return RED_FIVE;
		case RED_FIVE: return RED_FIVE;
		
		case ORANGE_ONE: return ORANGE_TWO;
		case ORANGE_TWO: return ORANGE_THREE;
		case ORANGE_THREE: return ORANGE_FOUR;
		case ORANGE_FOUR: return ORANGE_FIVE;
		case ORANGE_FIVE: return ORANGE_FIVE;
		
		case GREEN_ONE: return GREEN_ONE;
		case GREEN_TWO: return GREEN_TWO;
		case GREEN_THREE: return GREEN_THREE;
		case GREEN_FOUR: return GREEN_FOUR;
		case GREEN_FIVE: return GREEN_FIVE;
		default: return RED_ONE;
		}
	}
	
	public static enum WeaponModel {
		RED(createModel(Color.RED)),
		ORANGE(createModel(Color.ORANGE)),
		GREEN(createModel(Color.GREEN));
		
		public Model model;
		
		private WeaponModel(final Model model) {
			this.model = model;
		}
		
		private static Model createModel(final Color color) { //TODO make it more customable or powerful
	        ModelBuilder modelBuilder = new ModelBuilder();
	        return modelBuilder.createBox(0.5f, 0.5f, 0.5f, 
	                new Material(ColorAttribute.createDiffuse(color)),
	                Usage.Position | Usage.Normal);
		}
	}
}
