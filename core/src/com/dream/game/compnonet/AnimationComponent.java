package com.dream.game.compnonet;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Deity on 2017/4/5.
 */

public class AnimationComponent extends Component {

    public enum CONSTRUCTE{
        MOVING,STOP;
    }

    public enum PersonStatus{
        WALKING_LEFT,WALKING_RIGHT,WALKING_UP,WALKING_DOWN,
        FIGHT_LEFT,FIGHT_RIGHT,FIGHT_UP,FIGHT_DOWN,
        IDLE_LEFT,IDLE_RIGHT,IDLE_UP,IDLE_DOWN;
    }
    /**动画集合*/
    private HashMap<String,Animation<TextureRegion>> animationHashMap;

    public AnimationComponent(HashMap<String,Animation<TextureRegion>> animationHashMap){
        this.animationHashMap = animationHashMap;
    }

    public HashMap<String, Animation<TextureRegion>> getAnimationHashMap() {
        return animationHashMap;
    }

    public void setAnimationHashMap(HashMap<String, Animation<TextureRegion>> animationHashMap) {
        this.animationHashMap = animationHashMap;
    }

    public TextureRegion setTextureRegion(String currentStatus,float deltaTime){
        return animationHashMap.get(currentStatus).getKeyFrame(deltaTime);
    }
}
