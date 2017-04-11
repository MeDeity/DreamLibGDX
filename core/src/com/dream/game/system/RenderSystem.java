package com.dream.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.Bag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dream.game.compnonet.RenderCompnonet;
import com.dream.game.compnonet.TransformComponent;

/**
 * Created by Deity on 2017/4/4.
 */

public class RenderSystem extends EntitySystem {
    private ComponentMapper<RenderCompnonet> renderComponentMapper;
    private ComponentMapper<TransformComponent> transformComponentMapper;

    private SpriteBatch batch;
    /**
     * Creates an entity system that uses the specified aspect as a matcher
     * against entities.
     */
    public RenderSystem(SpriteBatch batch) {
        super(Aspect.all(RenderCompnonet.class));
        this.batch = batch;
    }

    @Override
    protected void begin() {
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    /**
     * Process the system.
     */
    @Override
    protected void processSystem() {
        Bag<Entity> entities = getEntities();
        for (Entity e : entities) {
            RenderCompnonet renderCompnonet = renderComponentMapper.get(e);
            TransformComponent transformComponent = transformComponentMapper.get(e);
            renderCompnonet.setPosition(transformComponent.getPosX(),transformComponent.getPosY());
            renderCompnonet.draw(batch);
        }
    }
}
