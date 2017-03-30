package com.dream.game.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

/**
 * 请注意AssetManager不能使用单例模式，根据官方的提示，很可能会出现资源错乱的情况
 * Created by Deity on 2017/3/30.
 */

public class GameResManager {
    private volatile static GameResManager gameResManager;
    private AssetManager assetManager;

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

    public void playSound(){

    }

    public void initResource(){
        assetManager.load(AppParameters.BASE_VOICE_PATH+AppParameters.VOICE_BULLET, Sound.class);
        assetManager.finishLoading();
    }



}
