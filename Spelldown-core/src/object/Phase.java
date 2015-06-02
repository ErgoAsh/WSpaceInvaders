package me.wiedzmin137.spelldown.object;

import java.util.Arrays;

import me.wiedzmin137.spelldown.Storage;
import me.wiedzmin137.spelldown.object.moveable.Enemy;
import me.wiedzmin137.spelldown.object.moveable.Meteorite;
import me.wiedzmin137.spelldown.object.moveable.SniperBoss;
import me.wiedzmin137.spelldown.object.moveable.ThrowingBoss;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public enum Phase {
	ONE(1) {
		@Override
		public String getDescription() {
			return "Fist encounter!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 5; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(), pos.set(-6 + (r * 2), 0, -10), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWO(2) {
		@Override
		public String getDescription() {
			return "Standard phase";
		}
		
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 11; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(),
						pos.set(-12 + (r * 2), 0, -15 - r).cpy(), dir.set(-12 + (r * 2), 0, -10).cpy(), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	THREE(3) {
		@Override
		public String getDescription() {
			return "Standard phase again";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 11; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(),
						pos.set(-12 + (r * 2), 0, -15 - r).cpy(), dir.set(-12 + (r * 2), 0, -10).cpy(), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	FOUR(4){
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 3);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 3);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 3);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	FIVE(5) {
		@Override
		public String getDescription() {
			return "MMM";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Storage.getInstance().getEnemyModel(), 
							pos.set(-12 + (r * 2), 0, -10 + (c * 2)), byNumber / 2);
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	SIX(6) {
		@Override
		public String getDescription() {
			return "MMM";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Storage.getInstance().getEnemyModel(), 
							pos.set(-12 + (r * 2), 0, -10 + (c * 2)), byNumber / 2);
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	SEVEN(7) {
		@Override
		public String getDescription() {
			return "MMM";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 4; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Storage.getInstance().getEnemyModel(), 
							pos.set(-12 + (r * 2), 0, -8 + (c * 2)), byNumber / 2);
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	EIGHT(8) {
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 3);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 3);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 3);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	NINE(9) {
		@Override
		public String getDescription() {
			return "Standard phase";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			enemies[0] = new Enemy(Storage.getInstance().getEnemyModel(), pos.set(-10, 0, -8).cpy(),
					dir.set(16, 0, -8).cpy(), byNumber, true);
			return Arrays.copyOf(enemies, 0);
		}
	},
	TEN(10) {
		@Override
		public String getDescription() {
			return "Beware - throwing boss approach!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[1];
			Enemy enemy = new ThrowingBoss(Storage.getInstance().getBossModel(),
					pos.set(0, 0, -20), dir.setZero(), byNumber * 2);
			enemy.transform.scl(0.25F);
			enemies[0] = enemy;
			return enemies;
		}
	},
	ELEVEN(11) {
		@Override
		public String getDescription() {
			return "Fist encounter!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 5; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(), pos.set(-6 + (r * 2), 0, -10), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWELVE(12) {
		@Override
		public String getDescription() {
			return "Standard phase";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 11; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(),
						pos.set(-12 + (r * 2), 0, -15 - r).cpy(), dir.set(-12 + (r * 2), 0, -10).cpy(), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	THIRTEEN(13) {
		@Override
		public String getDescription() {
			return "Standard phase again";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 11; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(),
						pos.set(-12 + (r * 2), 0, -15 - r).cpy(), dir.set(-12 + (r * 2), 0, -10).cpy(), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	FOURTEEN(14) {
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 5);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 5);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 5);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	FIFTEEN(15) {
		@Override
		public String getDescription() {
			return "Standard double phase";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 11; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(),
						pos.set(-12 + (r * 2), 0, -15 - r).cpy(), dir.set(-12 + (r * 2), 0, -10).cpy(), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	SIXTEEN(16) {
		@Override
		public String getDescription() {
			return "Standard phase again";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 11; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(),
						pos.set(-12 + (r * 2), 0, -15 - r).cpy(), dir.set(-12 + (r * 2), 0, -10).cpy(), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	SEVENTEEN(17) {
		@Override
		public String getDescription() {
			return "MMM";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Storage.getInstance().getEnemyModel(), 
							pos.set(-12 + (r * 2), 0, -8 + (c * 2)), byNumber);
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	EIGHTEEN(18) {
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 3);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 3);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 3);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	NINETEEN(19) {
		@Override
		public String getDescription() {
			return "Standard phase";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			enemies[0] = new Enemy(Storage.getInstance().getEnemyModel(), pos.set(-10, 0, -8).cpy(),
					dir.set(16, 0, -8).cpy(), byNumber, true);
			return Arrays.copyOf(enemies, 0);
		}
	},
	TWENTY(20) {
		@Override
		public String getDescription() {
			return "Beware - throwing boss approach!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[1];
			Enemy enemy = new ThrowingBoss(Storage.getInstance().getBossModel(),
					pos.set(0, 0, -20), dir.setZero(), byNumber * 2);
			enemy.transform.scl(0.25F);
			enemies[0] = enemy;
			return enemies;
		}
	},
	TWENTY_ONE(21) {
		@Override
		public String getDescription() {
			return "Third first encounter!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 2; r < 5; r++) {
				Enemy enemy = new Enemy(Storage.getInstance().getEnemyModel(), pos.set(-6 + (r * 2), 0, -10), byNumber);
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWENTY_TWO(22) {
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 3);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 3);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 3);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWENTY_THREE(23) {
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead (again)!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 3);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 3);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 3);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWENTY_FOUR(24) {
		@Override
		public String getDescription() {
			return "Beware - meteorites ahead!";
		}
		@Override
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 0; c < 25; c++) {
				dir.set(MathUtils.random(2F, 6F), 0, MathUtils.random(4F, 12F)).scl(2).nor();
				pos.set(MathUtils.random(-30, -15), 0, MathUtils.random(-50, -15));
				
				Enemy enemy;
				double r = Math.random();
				if (r < 0.33) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteSmall(), pos, dir.cpy(), byNumber, (short) 1, 5);
				} else if (r < 0.66) {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteMedium(), pos, dir.cpy(), byNumber + 1, (short) 2, 5);
				} else {
					enemy = new Meteorite(Storage.getInstance().getMeteoriteHuge(), pos, dir.cpy(), byNumber + 2, (short) 3, 5);
				}
				enemies[count] = enemy;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWENTY_FIVE(25) {
		@Override
		public String getDescription() {
			return "The last one. FIGHT!";
		}
		@Override //TODO add progressbas
		public Enemy[] getEnemies() {
			Enemy[] enemies = new Enemy[1];
			Enemy enemy = new SniperBoss(Storage.getInstance().getBossModel(),
					pos.set(0, 0, -20), dir.setZero(), byNumber * 3);
			enemy.transform.scl(0.25F);
			enemies[0] = enemy;
			return enemies;
		}
	},
	
	END(0) {
		@Override
		public String getDescription() {
			return "null";
		}
		@Override
		public Enemy[] getEnemies() {
			return new Enemy[32];
		}
	};
	
	public int byNumber;
	private Phase(final int number) {
		byNumber = number;
	}
	
	public abstract Enemy[] getEnemies();
	public abstract String getDescription();
	
	public static Phase getRandomPhase(final int start, final int end) {
		return Phase.getPhase(MathUtils.random(start, end));
	}
	
	public static Phase getPhase(final int number) {
		switch (number) {
		case 1: return ONE;
		case 2: return TWO;
		case 3: return THREE;
		case 4: return FOUR;
		case 5: return FIVE;
		case 6: return SIX;
		case 7: return SEVEN;
		case 8: return EIGHT;
		case 9: return NINE;
		case 10: return TEN;
		case 11: return ELEVEN;
		case 12: return TWELVE;
		case 13: return THIRTEEN;
		case 14: return FOURTEEN;
		case 15: return FIFTEEN;
		case 16: return SIXTEEN;
		case 17: return SEVENTEEN;
		case 18: return EIGHTEEN;
		case 19: return NINETEEN;
		case 20: return TWENTY;
		case 21: return TWENTY_ONE;
		case 22: return TWENTY_TWO;
		case 23: return TWENTY_THREE;
		case 24: return TWENTY_FOUR;
		case 25: return TWENTY_FIVE;
		case 0: return END;
		default: return ONE;
		}
	}
	
	public static Phase getNextPhase(final Phase current) {
		switch (current) {
		case ONE: return TWO;
		case TWO: return THREE;
		case THREE: return FOUR;
		case FOUR: return FIVE;
		case FIVE: return SIX;
		case SIX: return SEVEN;
		case SEVEN: return EIGHT;
		case EIGHT: return NINE;
		case NINE: return TEN;
		case TEN: return ELEVEN;
		case ELEVEN: return TWELVE;
		case TWELVE: return THIRTEEN;
		case THIRTEEN: return FOURTEEN;
		case FOURTEEN: return FIFTEEN;
		case FIFTEEN: return SIXTEEN;
		case SIXTEEN: return SEVENTEEN;
		case SEVENTEEN: return EIGHTEEN;
		case EIGHTEEN: return NINETEEN;
		case NINETEEN: return TWENTY;
		case TWENTY: return TWENTY_ONE;
		case TWENTY_ONE: return TWENTY_TWO;
		case TWENTY_TWO: return TWENTY_THREE;
		case TWENTY_THREE: return TWENTY_FOUR;
		case TWENTY_FOUR: return TWENTY_FIVE;
		case TWENTY_FIVE: return END;
		case END: return ONE;
		default: return ONE;
		}
	}
	
	private static Vector3 pos = new Vector3();
	private static Vector3 dir = new Vector3();
}
