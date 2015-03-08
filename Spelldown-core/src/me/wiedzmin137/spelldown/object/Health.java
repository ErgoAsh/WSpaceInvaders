package me.wiedzmin137.spelldown.object;

public interface Health {

	public void damage(Shot shot, double damage);
	
	public double getHealth();
	
	public void onDeath(Shot shot);
}
