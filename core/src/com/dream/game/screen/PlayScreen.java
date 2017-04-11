package com.dream.game.screen;

import com.ares.common.BaseUtils;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dream.game.MainGdxGame;
import com.dream.game.builder.ActorBuilder;
import com.dream.game.data.AppParameters;
import com.dream.game.data.GameResManager;
import com.dream.game.stage.GameStage;
import com.dream.game.system.AnimationSystem;
import com.dream.game.system.RenderSystem;
import com.dream.game.system.StatusSystem;

/**
 * 游戏主界面
 * Created by Deity on 2017/3/31.
 */

public class PlayScreen extends ScreenAdapter {
    private GameStage gameStage;
    private MainGdxGame mainGdxGame;
    private SpriteBatch batch;

    private World b2dWorld;
    private com.artemis.World world;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Stage stage;


    public PlayScreen(MainGdxGame mainGdxGame){
        this.mainGdxGame = mainGdxGame;
        batch = mainGdxGame.getBatch();
        init();
    }

    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(mainGdxGame.getWorldWidth(), mainGdxGame.getWorldHeight(), camera);
        camera.position.set(mainGdxGame.getWorldWidth() / 2, mainGdxGame.getWorldHeight() / 2, 0);
        stage = new Stage(viewport);
        //背景图片
        Image backgroundImage = new Image(GameResManager.getInstance().getAtlas().findRegion(AppParameters.IMAGE_GAME_BG));
        backgroundImage.setPosition(0,0);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
    }

    public void init(){
        gameStage = new GameStage(mainGdxGame,
                new StretchViewport(
                        mainGdxGame.getWorldWidth(),
                        mainGdxGame.getWorldHeight()
                ));
        b2dWorld = new World(new Vector2(0,-9.8f),true);
        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
                .with(new RenderSystem(mainGdxGame.getBatch()),
                        new AnimationSystem(),
                        new StatusSystem()).build();
        world = new com.artemis.World(worldConfiguration);
        ActorBuilder actorBuilder = ActorBuilder.init(b2dWorld,world,mainGdxGame);
//        actorBuilder.createBackground();
        actorBuilder.createGround();
        actorBuilder.createPerson(0,AppParameters.FLOOR_HEIGHT, AppParameters.GUAN_UP_MELEE);

    }

    @Override
    public void render(float delta) {
        BaseUtils.clearScreen(0.0f,1.0f,1.0f,1.0f);
        batch.setProjectionMatrix(camera.combined);
        super.render(delta);

        stage.act(delta);
        stage.draw();

        // draw ground
        world.setDelta(delta);
        world.process();

    }
}
