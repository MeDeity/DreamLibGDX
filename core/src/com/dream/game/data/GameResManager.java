package com.dream.game.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 请注意AssetManager不能使用单例模式，根据官方的提示，很可能会出现资源错乱的情况
 * Created by Deity on 2017/3/30.
 */

public class GameResManager {
    private volatile static GameResManager gameResManager;
    private AssetManager assetManager;

    public static final short BIT_PLAYER=1;
    public static final short BIT_ENEMY=1<<1;

    private GameResManager(){
        assetManager = new AssetManager();
    }

    public static GameResManager getInstance(){
        if (null==gameResManager){
            synchronized (GameResManager.class){
                if (null==gameResManager){
                    gameResManager = new GameResManager();
                }
            }
        }
        return gameResManager;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void playSound(String voiceFileName){
        Sound sound = assetManager.get(AppParameters.BASE_VOICE_PATH+voiceFileName,Sound.class);
        sound.play();
    }

    public Animation<TextureRegion> obtainAnimation(String atlasFileName){
        Texture texture = assetManager.get(AppParameters.BASE_ATLAS_PATH+atlasFileName,Texture.class);
        TextureRegion[][] textureRegions = TextureRegion.split(texture,texture.getWidth()/AppParameters.ROW_SIZE,texture.getHeight()/AppParameters.COLCUME_SIZE);
        TextureRegion[] animationFrames = new TextureRegion[AppParameters.COLCUME_SIZE*AppParameters.ROW_SIZE];
        int index=0;
        for (int i = 0;i<AppParameters.ROW_SIZE;i++){
            for (int j = 0;j<AppParameters.COLCUME_SIZE;j++){
                animationFrames[index++]=textureRegions[i][j];
            }
        }
        Animation<TextureRegion> animation = new Animation<TextureRegion>(0.25f,animationFrames);
        return animation;
    }

    public TextureRegion[][] obtainAnimationArray(String atlasFileName){
        Texture texture = assetManager.get(AppParameters.BASE_ATLAS_PATH+atlasFileName,Texture.class);
        TextureRegion[][] textureRegions = TextureRegion.split(texture,texture.getWidth()/AppParameters.ROW_SIZE,texture.getHeight()/AppParameters.COLCUME_SIZE);
        return textureRegions;
    }

    public void initResource(){
        assetManager.load(AppParameters.BASE_VOICE_PATH+AppParameters.VOICE_TEST, Sound.class);
        assetManager.load(AppParameters.BASE_ATLAS_PATH+AppParameters.GUAN_UP_NORMAL, Texture.class);
        assetManager.load(AppParameters.BASE_ATLAS_PATH+AppParameters.GUAN_UP_MELEE, Texture.class);
        assetManager.load(AppParameters.BASE_ATLAS_PATH+AppParameters.GUAN_UP_DEAD, Texture.class);
        assetManager.load(AppParameters.BASE_ATLAS_PATH+AppParameters.BASE_ATLAS_IMAGE, TextureAtlas.class);
        assetManager.finishLoading();
    }

    public TextureAtlas getAtlas(){
        return assetManager.get(AppParameters.BASE_ATLAS_PATH+AppParameters.BASE_ATLAS_IMAGE,TextureAtlas.class);
    }



}
