package com.dream.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dream.game.data.AppParameters;
import com.dream.game.data.GameResManager;
import com.dream.game.screen.PlayScreen;

public class MainGdxGame extends Game {
	private SpriteBatch batch;
	private Animation<TextureRegion> animation;
	private float stateTime;
	private float worldWidth;
	private float worldHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();
		GameResManager.getInstance().initResource();
		GameResManager.getInstance().playSound(AppParameters.VOICE_TEST);
		animation = GameResManager.getInstance().obtainAnimation(AppParameters.GUAN_UP_NORMAL);
//		AnimationActor animationActor = new AnimationActor(animation);
		initParams();
		setScreen(new PlayScreen(MainGdxGame.this));
	}

	private void initParams(){
		worldWidth = AppParameters.FIX_WORLD_WIDTH;
		worldHeight = AppParameters.FIX_WORLD_WIDTH*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
	}

	@Override
	public void render () {
		super.render();
//		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
//		batch.begin();
//		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
//		batch.draw(currentFrame, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public float getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(float worldWidth) {
		this.worldWidth = worldWidth;
	}

	public float getWorldHeight() {
		return worldHeight;
	}

	public void setWorldHeight(float worldHeight) {
		this.worldHeight = worldHeight;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
