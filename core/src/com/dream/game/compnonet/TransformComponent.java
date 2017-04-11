package com.dream.game.compnonet;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Deity on 2017/4/10.
 */

public class TransformComponent extends Component {
    /**位置X*/
    private float posX;
    /**位置y*/
    private float posY;

    private float z;//for checking drawing sequence
    /**缩放*/
    private float scaleX;
    /**缩放*/
    private float scaleY;

    private float rotation;//旋转


    public TransformComponent(float posX,float posY,float z,float scaleX,float scaleY,float rotation){
        this.posX = posX;
        this.posY = posY;
        this.z = z;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotation = rotation;
    }

    public TransformComponent(float posX,float posY){
        this(posX,posY,0,1,1,0);
    }

    public void setPosition(Vector2 voctor){
        this.posX = voctor.x;
        this.posY = voctor.y;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getZ() {
        return z;
    }

    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public float getRotation() {
        return rotation;
    }
}
