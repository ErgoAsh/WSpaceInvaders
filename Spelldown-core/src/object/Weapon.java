package me.wiedzmin137.spelldown.object;

import me.wiedzmin137.spelldown.Main;
import me.wiedzmin137.spelldown.Storage.EModel;
import me.wiedzmin137.spelldown.object.item.Shot;

import com.badlogic.gdx.math.Vector3;

public enum Weapon {

	RED_ONE(1, 1, 0.25F, EModel.WEAPON_RED) {
		@Override
		public void shot(final Vector3 pos) {
			Main.getInstance().getStorage().addShot(new Shot(color, pos, true, 1));
		} //TODO send info to shot that it is RED one to make proper speed and damage multiplier etc.
	},
	RED_TWO(2, 2, 0.25F, EModel.WEAPON_RED) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	RED_THREE(3, 4, 0.25F, EModel.WEAPON_RED) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[3];
			shots[0] = new Shot(color, pos.cpy().add(-0.75F, 0, 0), true, 1, -0.5F);
			shots[1] = new Shot(color, pos.cpy().add(0, 0, -0.4F), true, 1);
			shots[2] = new Shot(color, pos.cpy().add(0.75F, 0, 0), true, 1, 0.5F);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	RED_FOUR(4, 6, 0.25F, EModel.WEAPON_RED) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[4];
			shots[0] = new Shot(color, pos.cpy().add(-0.75F, 0, 0), true, 1, -1F);
			shots[1] = new Shot(color, pos.cpy().add(-0.4F, 0, -0.25F), true, 1);
			shots[2] = new Shot(color, pos.cpy().add(0.4F, 0, -0.25F), true, 1);
			shots[3] = new Shot(color, pos.cpy().add(0.75F, 0, 0), true, 1, 1F);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	RED_FIVE(5, 10, 0.25F, EModel.WEAPON_RED) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[6];
			shots[0] = new Shot(color, pos.cpy().add(-0.75F, 0, 0.5F), true, 1, -4F);
			shots[1] = new Shot(color, pos.cpy().add(-0.75F, 0, 0.25F), true, 1, -2F);
			shots[2] = new Shot(color, pos.cpy().add(-0.4F, 0, -0.25F), true, 1);
			shots[3] = new Shot(color, pos.cpy().add(0.4F, 0, -0.25F), true, 1);
			shots[4] = new Shot(color, pos.cpy().add(0.75F, 0, 0.25F), true, 1, 2F);
			shots[5] = new Shot(color, pos.cpy().add(0.75F, 0, 0.5F), true, 1, 4F);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	
	ORANGE_ONE(1, 1, 0.15F, EModel.WEAPON_ORANGE) {
		@Override
		public void shot(final Vector3 pos) {
			Main.getInstance().getStorage().addShot(new Shot(color, pos, true, 1));
		}
	},
	ORANGE_TWO(2, 3, 0.15F, EModel.WEAPON_ORANGE) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[3];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(0.0F, 0, -0.5F), true, 1);
			shots[2] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	ORANGE_THREE(3, 5, 0.15F, EModel.WEAPON_ORANGE) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[4];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(-0.25F, 0, -0.5F), true, 1);
			shots[2] = new Shot(color, pos.cpy().add(0.25F, 0, -0.5F), true, 1);
			shots[3] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	ORANGE_FOUR(4, 7, 0.15F, EModel.WEAPON_ORANGE) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[4];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(-0.25F, 0, -0.5F), true, 1.5);
			shots[2] = new Shot(color, pos.cpy().add(0.25F, 0, -0.5F), true, 1.5);
			shots[3] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	ORANGE_FIVE(5, 10, 0.15F, EModel.WEAPON_ORANGE) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[5];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(-0.25F, 0, -0.5F), true, 1.5);
			shots[2] = new Shot(color, pos.cpy().add(0.0F, 0, -1F), true, 2);
			shots[3] = new Shot(color, pos.cpy().add(0.25F, 0, -0.5F), true, 1.5);
			shots[4] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	
	GREEN_ONE(1, 1, 0.45F, EModel.WEAPON_GREEN) {
		@Override
		public void shot(final Vector3 pos) {
			Main.getInstance().getStorage().addShot(new Shot(color, pos, true, 1));
		}
	},
	GREEN_TWO(2, 2, 0.45F, EModel.WEAPON_GREEN) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	GREEN_THREE(3, 4, 0.45F, EModel.WEAPON_GREEN) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	GREEN_FOUR(4, 6, 0.45F, EModel.WEAPON_GREEN) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	},
	GREEN_FIVE(5, 8, 0.45F, EModel.WEAPON_GREEN) {
		@Override
		public void shot(final Vector3 pos) {
			Shot[] shots = new Shot[2];
			shots[0] = new Shot(color, pos.cpy().add(-0.5F, 0, 0), true, 1);
			shots[1] = new Shot(color, pos.cpy().add(0.5F, 0, 0), true, 1);
			Main.getInstance().getStorage().addShot(shots);
		}
	};
	
	public int level;
	public int levelNeeded;
	public float cooldown;
	public EModel color;
	public abstract void shot(Vector3 pos);
	
	private Weapon(final int level, final int levelNeeded, final float cooldown, final EModel color) {
		this.levelNeeded = levelNeeded;
		this.level = level;
		this.cooldown = cooldown;
		this.color = color;
	}
	
	public static Weapon getWeapon(final EModel color, final int level) {
		switch (color) {
		case WEAPON_RED: 
			switch (level) {
			case 1: return RED_ONE;
			case 2: return RED_TWO;
			case 3: return RED_THREE;
			case 4: return RED_FOUR;
			case 5: return RED_FIVE;
			default: return RED_ONE;
			}
		case WEAPON_ORANGE:
			switch (level) {
			case 1: return ORANGE_ONE;
			case 2: return ORANGE_TWO;
			case 3: return ORANGE_THREE;
			case 4: return ORANGE_FOUR;
			case 5: return ORANGE_FIVE;
			default: return ORANGE_ONE;
			}
		case WEAPON_GREEN:
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
		
		case GREEN_ONE: return GREEN_TWO;
		case GREEN_TWO: return GREEN_THREE;
		case GREEN_THREE: return GREEN_FOUR;
		case GREEN_FOUR: return GREEN_FIVE;
		case GREEN_FIVE: return GREEN_FIVE;
		default: return RED_ONE;
		}
	}
}
