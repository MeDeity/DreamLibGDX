package com.dream.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Deity on 2017/3/30.
 */

public class AnimationActor extends Actor {
    private TextureRegion textureRegion;
    /**纹理图集*/
    private Animation<TextureRegion> animations;
    /**当前是否播放动画*/
    private boolean isAnimationRuning = false;
    /**时间流逝*/
    private float stateTime;

    /** 不播放动画时固定显示的帧索引 */
    private int fixedShowKeyFrameIndex;

    /**这是其中一个构造方法*/
    public AnimationActor(Animation<TextureRegion> animations){
        this.animations = animations;
    }


    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public int getFixedShowKeyFrameIndex() {
        return fixedShowKeyFrameIndex;
    }

    public void setFixedShowKeyFrameIndex(int fixedShowKeyFrameIndex) {
        this.fixedShowKeyFrameIndex = fixedShowKeyFrameIndex;
    }

    /**逻辑运算*/
    @Override
    public void act(float delta) {
        super.act(delta);
        if (null!=animations) {
            // 如果需要播放动画, 则累加时间步, 并按累加值获取需要显示的关键帧
            TextureRegion region = null;
            if (isAnimationRuning) {
                stateTime += delta;
                region = animations.getKeyFrame(stateTime);
            }else{
                // 不需要播放动画, 则获取 fixedShowKeyFrameIndex 指定的关键帧
                TextureRegion[] animationsKeyFrames = animations.getKeyFrames();
                if (fixedShowKeyFrameIndex>=0&&fixedShowKeyFrameIndex<animationsKeyFrames.length){
                    region = animationsKeyFrames[fixedShowKeyFrameIndex];
                }
            }
            setTextureRegion(region);
        }
    }

    /**绘图*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color currentColor = batch.getColor();//保存当前的颜色状态值
        Color childColor = getColor();
        //将演员的颜色结合parentAlpha 设置到batch中
        batch.setColor(childColor.r,childColor.g,childColor.b,childColor.a*parentAlpha);
        // 结合演员的属性绘制表示演员的纹理区域
        batch.draw(textureRegion,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
        // 还原 batch 原本的 Color
        batch.setColor(currentColor);
    }
}
