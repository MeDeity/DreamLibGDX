package com.dream.game.builder;

import com.artemis.Entity;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.dream.game.MainGdxGame;
import com.dream.game.compnonet.AnimationComponent;
import com.dream.game.compnonet.RenderCompnonet;
import com.dream.game.compnonet.StatusComponent;
import com.dream.game.compnonet.TransformComponent;
import com.dream.game.data.AppParameters;
import com.dream.game.data.GameResManager;

import java.util.HashMap;

/**
 * 对象创建及绘制工厂
 * Created by Deity on 2017/4/4.
 */

public class ActorBuilder {
    private World b2dWorld;
    private com.artemis.World world;
    private MainGdxGame mainGdxGame;
    private static ActorBuilder instance = new ActorBuilder();

    protected final float radius = 0.45f;//玩家半径大小

    public static ActorBuilder init(World b2dWorld, com.artemis.World world, MainGdxGame mainGdxGame){
        instance.b2dWorld = b2dWorld;
        instance.world = world;
        instance.mainGdxGame =mainGdxGame;
        return instance;
    }

    /**创建背景*/
    public void createBackground(float x,float y){
        TextureRegion textureRegion = GameResManager.getInstance().getAtlas().findRegion(AppParameters.IMAGE_GAME_BG);
        RenderCompnonet renderCompnonet = new RenderCompnonet(textureRegion);

        new EntityBuilder(world).with(renderCompnonet,new TransformComponent(x,y)).build();
    }

    public void createGround(){
        TextureRegion textureRegion = GameResManager.getInstance().getAtlas().findRegion(AppParameters.IMAGE_GAME_FLOOR);
        textureRegion.setRegionWidth(Gdx.graphics.getWidth());

//        TextureRegion nextTextureRegion = GameResManager.getInstance().getAtlas().findRegion(AppParameters.IMAGE_GAME_FLOOR);
//        nextTextureRegion.setRegionWidth(Gdx.graphics.getWidth());
//        nextTextureRegion.flip(true,false);

        Array<TextureRegion> keyFrames = new Array<TextureRegion>();
        keyFrames.add(textureRegion);
//        keyFrames.add(nextTextureRegion);

        Animation<TextureRegion> animations = new Animation<TextureRegion>(0.25f,keyFrames, Animation.PlayMode.LOOP);
        RenderCompnonet renderCompnonet = new RenderCompnonet(textureRegion);

        HashMap<String,Animation<TextureRegion>> mHashMap = new HashMap<String, Animation<TextureRegion>>();
        mHashMap.put(AnimationComponent.CONSTRUCTE.MOVING.name(),animations);

        StatusComponent statusComponent = new StatusComponent(AnimationComponent.CONSTRUCTE.MOVING.name());

        AppParameters.FLOOR_HEIGHT = renderCompnonet.getOriginY()-10f;//TODO 10f是因为计算中存在10f的误差导致人物停留在半空中,矫正用
        new EntityBuilder(world).with(renderCompnonet,new AnimationComponent(mHashMap),statusComponent,new TransformComponent(textureRegion.getRegionWidth()/2,0)).build();
    }

    /**
     * 创建主角
     */
    public void createPerson(float x, float y, String atlasFileName){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;//动态物体
        bodyDef.position.set(x,y);
        bodyDef.linearDamping = 12.0f;//用于降低线性速度

        Body body = b2dWorld.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.filter.categoryBits = GameResManager.BIT_PLAYER;
        fixtureDef.filter.maskBits = GameResManager.BIT_ENEMY;

        body.createFixture(fixtureDef);
        circleShape.dispose();

        Animation<TextureRegion> animation = GameResManager.getInstance().obtainAnimation(atlasFileName);
        RenderCompnonet renderCompnonet = new RenderCompnonet(animation.getKeyFrames()[0]);
        HashMap<String,Animation<TextureRegion>> mHashMap = new HashMap<String, Animation<TextureRegion>>();

        TextureRegion[][] textureRegions = GameResManager.getInstance().obtainAnimationArray(atlasFileName);
        Array<TextureRegion> keyFrames = new Array<TextureRegion>();
        keyFrames.addAll(textureRegions[0]);
        //walking left
        Animation<TextureRegion> animationLeft = new Animation<TextureRegion>(0.25f,keyFrames, Animation.PlayMode.LOOP);
        mHashMap.put(AnimationComponent.PersonStatus.WALKING_DOWN.name(),animationLeft);
        //walking left
        keyFrames.clear();
        keyFrames.addAll(textureRegions[1]);
        Animation<TextureRegion> animationRight = new Animation<TextureRegion>(0.25f,keyFrames,Animation.PlayMode.LOOP);
        mHashMap.put(AnimationComponent.PersonStatus.WALKING_LEFT.name(),animationRight);
        //walking up
        keyFrames.clear();
        keyFrames.addAll(textureRegions[2]);
        Animation<TextureRegion> animationUp = new Animation<TextureRegion>(0.25f,keyFrames,Animation.PlayMode.LOOP);
        mHashMap.put(AnimationComponent.PersonStatus.WALKING_RIGHT.name(),animationUp);
        //walking down
        keyFrames.clear();
        keyFrames.addAll(textureRegions[3]);
        Animation<TextureRegion> animationDown = new Animation<TextureRegion>(0.25f,keyFrames,Animation.PlayMode.LOOP);
        mHashMap.put(AnimationComponent.PersonStatus.WALKING_UP.name(),animationDown);

        AnimationComponent animationComponent = new AnimationComponent(mHashMap);
        StatusComponent statusComponent = new StatusComponent(AnimationComponent.PersonStatus.WALKING_RIGHT.name());

        Entity entity = new EntityBuilder(world).with(renderCompnonet,animationComponent,statusComponent,new TransformComponent(x+renderCompnonet.getOriginX(),y+renderCompnonet.getOriginY())).build();
        body.setUserData(entity);
    }
}
