package me.wiedzmin137.spelldown.screen;

import me.wiedzmin137.spelldown.Main;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

	public Main game;
	
	public AbstractScreen(Main game) {
		this.game = game;
	}
	public abstract void update(float delta);
	public abstract void draw(float delta);
	
	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	@Override
	public void resize(int width, int height) {}
	
	@Override
	public void show() {}
	
	@Override
	public void hide() {}
	
	@Override
	public void pause() {}
	
	@Override
	public void resume() {}
	
	@Override
	public void dispose () {}
	
}
