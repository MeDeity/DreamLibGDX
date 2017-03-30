package com.ares.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * 输入输出工具集
 * Created by Deity on 2016/12/27.
 */

public class IOUtils {

    public static FileHandle obtainAssetsSource(String mFilePath){
        return Gdx.files.internal(mFilePath);
    }
}
