package me.wiedzmin137.spelldown.object;

import me.wiedzmin137.spelldown.object.item.Shot;

public interface Health {

	public void damage(Shot shot, double damage);
	
	public double getHealth();
	
	public void onDeath(Shot shot);
}
