package com.battlecity.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

	private GameScreen gameScreen;
	
	@Override
	public void create() {
		SpriteBatch batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		gameScreen.create();
	}

	@Override
	public void render() {
		gameScreen.render();
	}

}
