package com.dream.game;

import com.ares.common.BaseUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dream.game.data.AppParameters;
import com.dream.game.data.GameResManager;

public class MainGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		GameResManager.getInstance().initResource();
		Sound sound = GameResManager.getInstance().getAssetManager().get(AppParameters.BASE_VOICE_PATH+AppParameters.VOICE_BULLET, Sound.class);
		sound.play(1.0f,1.0f,1.0f);

	}

	@Override
	public void render () {
		BaseUtils.clearScreen(0.0f,1.0f,1.0f,1.0f);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
