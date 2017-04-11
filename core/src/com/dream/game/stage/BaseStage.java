package com.dream.game.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dream.game.MainGdxGame;

/**
 * Created by Deity on 2017/3/31.
 */

public class BaseStage extends Stage {

    private MainGdxGame mainGdxGame;

    /**舞台可见*/
    private boolean isVisiblity = true;

    public BaseStage(MainGdxGame mainGdxGame,Viewport viewport){
        super(viewport);
        this.mainGdxGame = mainGdxGame;
    }

    public MainGdxGame getMainGdxGame() {
        return mainGdxGame;
    }

    public void setMainGdxGame(MainGdxGame mainGdxGame) {
        this.mainGdxGame = mainGdxGame;
    }

    public boolean isVisiblity() {
        return isVisiblity;
    }

    public void setVisiblity(boolean visiblity) {
        isVisiblity = visiblity;
    }
}
