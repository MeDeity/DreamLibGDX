package com.dream.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.dream.game.compnonet.AnimationComponent;
import com.dream.game.compnonet.RenderCompnonet;
import com.dream.game.compnonet.StatusComponent;

/**
 * Created by Deity on 2017/4/5.
 */

public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<AnimationComponent> animationComponentMapper;
    private ComponentMapper<RenderCompnonet> renderComponentMapper;
    private ComponentMapper<StatusComponent> statusComponentMapper;
    /**
     * Creates a new EntityProcessingSystem.
     */
    public AnimationSystem() {
        super(Aspect.all(AnimationComponent.class, RenderCompnonet.class, StatusComponent.class));
    }

    /**
     * Process a entity this system is interested in.
     *
     * @param entityId the entity to process
     */
    @Override
    protected void process(int entityId) {
        AnimationComponent animationComponent = animationComponentMapper.get(entityId);
        RenderCompnonet renderCompnonet = renderComponentMapper.get(entityId);
        StatusComponent statusComponent = statusComponentMapper.get(entityId);

        renderCompnonet.setRegion(animationComponent.setTextureRegion(statusComponent.getCurrentStatus(),statusComponent.getDeltaTime()));

    }
}
