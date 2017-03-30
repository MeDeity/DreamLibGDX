package com.dream.game;

import com.ares.common.BaseUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dream.game.data.AppParameters;
import com.dream.game.data.GameResManager;

public class MainGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	Animation<TextureRegion> animation;
	float stateTime;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		GameResManager.getInstance().initResource();
		GameResManager.getInstance().playSound(AppParameters.VOICE_TEST);
		animation = GameResManager.getInstance().obtainAnimation(AppParameters.GUAN_UP_NORMAL);
//		AnimationActor animationActor = new AnimationActor(animation);
	}

	@Override
	public void render () {
		BaseUtils.clearScreen(0.0f,1.0f,1.0f,1.0f);
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		batch.begin();
		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
