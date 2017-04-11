package com.dream.game.compnonet;

import com.artemis.Component;

/**
 * Created by Deity on 2017/4/5.
 */

public class StatusComponent extends Component {

    private String currentStatus;
    private float deltaTime;

    public StatusComponent(String currentStatus){
        this.currentStatus = currentStatus;
        this.deltaTime = 0;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public void addDeltaTime(float deltaTime){
        this.deltaTime += deltaTime;
    }
}
