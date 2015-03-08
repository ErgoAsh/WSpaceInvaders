package me.wiedzmin137.spelldown.object;

import java.util.Arrays;

import me.wiedzmin137.spelldown.Main;

import com.badlogic.gdx.math.Matrix4;

public enum Phase {
	ONE(1) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 1; r < 11; r++) {
				Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition.cpy(), byNumber);
				something.transform.translate(-12 + (r * 2), 0, 10);
				enemies[count] = something;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	TWO(2) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 1; r < 11; r++) {
				Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition.cpy(), byNumber);
				something.transform.translate(-12 + (r * 2), 0, 10);
				enemies[count] = something;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	THREE(3) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int r = 1; r < 11; r++) {
				Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition.cpy(), byNumber);
				something.transform.translate(-12 + (r * 2), 0, 10);
				enemies[count] = something;
				count++;
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	FOUR(4){
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition.cpy(), byNumber);
					something.transform.translate(-12 + (r * 2), 0, 8 + (c * 2));
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	FIVE(5) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition.cpy(), byNumber);
					something.transform.translate(-12 + (r * 2), 0, 8 + (c * 2));
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	SIX(6) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			int count = 0;
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition.cpy(), byNumber);
					something.transform.translate(-12 + (r * 2), 0, 8 + (c * 2));
					enemies[count] = something;
					count++;
				}	
			}
			return Arrays.copyOf(enemies, count);
		}
	},
	SEVEN(7) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	EIGHT(8) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	NINE(9) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TEN(10) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	ELEVEN(11) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWELVE(12) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	THIRTEEN(13) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	FOURTEEN(14) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	FIFTEEN(15) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	SIXTEEN(16) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	SEVENTEEN(17) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	EIGHTEEN(18) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	NINETEEN(19) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWENTY(20) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWENTY_ONE(21) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWENTY_TWO(22) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWENTY_THREE(23) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWENTY_FOUR(24) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	TWENTY_FIVE(25) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			Enemy[] enemies = new Enemy[32];
			for (int c = 1; c < 3; c++) {
				for (int r = 1; r < 11; r++) {
					Enemy something = new Enemy(Main.getInstance().getStorage().getEnemyModel(), basicPosition, byNumber);
					enemies[r * c] = something;
				}	
			}
			Main.getInstance().log("Size: " + enemies.length);
			return enemies;
		}
	},
	
	END(0) {
		@Override
		public Enemy[] getEnemies(final Matrix4 basicPosition) {
			return new Enemy[32];
		}
	};
	
	public int byNumber;
	private Phase(final int number) {
		byNumber = number;
	}
	
	public abstract Enemy[] getEnemies(final Matrix4 basicPosition);
	
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
}
