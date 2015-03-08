package me.wiedzmin137.spelldown;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Utils {
	
	private static Vector3 checkPosA = new Vector3();
	private static Vector3 checkPosB = new Vector3();

	public static boolean isInRadius(final ModelInstance a, final ModelInstance b, final float radius) {
		a.transform.getTranslation(checkPosA);
		b.transform.getTranslation(checkPosB);
		if (checkPosA.dst2(checkPosB) < radius) {
			return true;
		} else {
			return false;
		}
	}
}
