package com.ares.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


/**
 * 基础命令
 * Created by Deity on 2016/12/27.
 */

public class BaseUtils {

    /**清屏指令，render必须调用*/
    public static void clearScreen(float red, float green, float blue, float alpha){
        Gdx.gl.glClearColor(red, green, blue, alpha);// 设置背景颜色为白色
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// 清屏
    }
}
