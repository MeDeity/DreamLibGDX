package com.dream.game.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.dream.game.compnonet.StatusComponent;

/**
 * Created by Deity on 2017/4/6.
 */

public class StatusSystem extends IteratingSystem {
    private ComponentMapper<StatusComponent> statusComponentMapper;

    public StatusSystem() {
        super(Aspect.all(StatusComponent.class));
    }

    /**
     * Process a entity this system is interested in.
     *
     * @param entityId the entity to process
     */
    @Override
    protected void process(int entityId) {
        StatusComponent statusComponent = statusComponentMapper.get(entityId);
        statusComponent.addDeltaTime(world.getDelta());
    }
}
