package com.dream.game.screen;

import com.ares.common.BaseUtils;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private NinePatch border; //边框
    private Texture upATexture;
    private Texture downATexture;
    private Texture upBTexture;
    private Texture downBTexture;
    // 按钮
    private Button buttonA;
    private Button buttonB;



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
        //对话框
        border = new NinePatch(GameResManager.getInstance().getAssetManager().get(AppParameters.BASE_ATLAS_PATH+AppParameters.WEDGET_BORDER, Texture.class),10,10,10,10);
        Image borderImage = new Image(border);
        borderImage.setWidth(mainGdxGame.getWorldWidth());
        borderImage.setPosition(0,mainGdxGame.getWorldHeight()/2);

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        Label label = new Label("99*100=?", labelStyle);
        label.setText("99*100=?  A:990   B:9900");
        label.setX(border.getLeftWidth()*3);
        label.setY(mainGdxGame.getWorldHeight()/2+borderImage.getHeight()/2-label.getHeight()/2);

        //按钮添加
        upATexture = GameResManager.getInstance().getAssetManager().get(AppParameters.BASE_ATLAS_PATH+AppParameters.WEDGET_BTN_A_NORMAL);
        downATexture = GameResManager.getInstance().getAssetManager().get(AppParameters.BASE_ATLAS_PATH+AppParameters.WEDGET_BTN_A_PRESS);
        upBTexture = GameResManager.getInstance().getAssetManager().get(AppParameters.BASE_ATLAS_PATH+AppParameters.WEDGET_BTN_B_NORMAL);
        downBTexture = GameResManager.getInstance().getAssetManager().get(AppParameters.BASE_ATLAS_PATH+AppParameters.WEDGET_BTN_B_PRESS);


        Button.ButtonStyle styleA = new Button.ButtonStyle();
        styleA.up = new TextureRegionDrawable(new TextureRegion(upATexture));
        styleA.down = new TextureRegionDrawable(new TextureRegion(downATexture));
        buttonA = new Button(styleA);
        buttonA.setHeight(64);
        buttonA.setWidth(64);
        buttonA.setPosition(0,0);

        Button.ButtonStyle styleB = new Button.ButtonStyle();
        styleB.up = new TextureRegionDrawable(new TextureRegion(upBTexture));
        styleB.down = new TextureRegionDrawable(new TextureRegion(downBTexture));
        buttonB = new Button(styleB);
        buttonB.setHeight(64);
        buttonB.setWidth(64);
        buttonB.setPosition(mainGdxGame.getWorldWidth()-buttonB.getWidth(),0);
        buttonB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Test", "按钮被点击了");
            }
        });


        stage.addActor(backgroundImage);
        stage.addActor(borderImage);
        stage.addActor(buttonA);
        stage.addActor(buttonB);
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);
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
        actorBuilder.createPerson(mainGdxGame.getWorldWidth()/2,AppParameters.FLOOR_HEIGHT, AppParameters.GUAN_UP_NORMAL);

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
