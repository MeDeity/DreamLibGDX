package com.dream.game.compnonet;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Deity on 2017/4/4.
 */

public class RenderCompnonet extends Component {
    private Sprite sprite;

    public RenderCompnonet(TextureRegion textureRegion){
        sprite = new Sprite(textureRegion);
    }

    public RenderCompnonet(TextureRegion textureRegion, float width, float height) {
        this(textureRegion);
        sprite.setSize(width, height);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x - sprite.getOriginX(), y - sprite.getOriginY());
    }

    public float getOriginY(){
        return sprite.getOriginY();
    }

    public float getOriginX(){
        return sprite.getOriginX();
    }

    public void setCenter(float x,float y){
        sprite.setCenter(x,y);
    }

    public void setHorizonTalWidth(){
        sprite.setSize(Gdx.graphics.getWidth(),sprite.getHeight());
    }

    public void setRegion(TextureRegion textureRegion){
        sprite.setRegion(textureRegion);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

}
