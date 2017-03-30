package com.ares.storage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * 数据存储工具集
 * Created by Deity on 2016/12/27.
 */

public class StorageUtils {

    /**获取仓库管理类*/
    public static Preferences obtainStorage(String StorageName){
        return Gdx.app.getPreferences(StorageName);
    }

    /**
     * 保存数据
     * @param preferences 保存数据的preferences
     * @param keyName key
     * @param saveValue value
     */
    public static void saveStorageData(Preferences preferences,String keyName,Object saveValue){
        if (null==preferences) throw new IllegalArgumentException("preferences require not null");
        if (null==keyName) throw new IllegalArgumentException("keyName require not null");
        if (null==saveValue) throw new IllegalArgumentException("saveValue require not null");
        if (saveValue instanceof Integer){
            preferences.putInteger(keyName, (Integer) saveValue);
        }else if (saveValue instanceof Long){
            preferences.putLong(keyName, (Long) saveValue);
        }else if (saveValue instanceof String){
            preferences.putString(keyName, (String) saveValue);
        }else if (saveValue instanceof Boolean){
            preferences.putBoolean(keyName, (Boolean) saveValue);
        }else if (saveValue instanceof Float){
            preferences.putFloat(keyName, (Float) saveValue);
        }else {
            throw new IllegalArgumentException("saveValue is invalid type");
        }
        preferences.flush();
    }

    /**
     * 获取偏好设置的值
     * @param preferences 偏好设置
     * @param keyName key
     * @param defaultValue defaultValue
     */
    public static Object obtainStorageData(Preferences preferences,String keyName,Object defaultValue){
        if (null==preferences) throw new IllegalArgumentException("preferences require not null");
        if (null==keyName) throw new IllegalArgumentException("keyName require not null");
        if (null==defaultValue) throw new IllegalArgumentException("defaultValue require not null");
        if (defaultValue instanceof Integer){
            return preferences.getInteger(keyName, (Integer) defaultValue);
        }else if (defaultValue instanceof Long){
            return preferences.getLong(keyName, (Long) defaultValue);
        }else if (defaultValue instanceof String){
            return preferences.getString(keyName, (String) defaultValue);
        }else if (defaultValue instanceof Boolean){
            return preferences.getBoolean(keyName, (Boolean) defaultValue);
        }else if (defaultValue instanceof Float){
            return preferences.getFloat(keyName, (Float) defaultValue);
        }else {
            throw new IllegalArgumentException("defaultValue is invalid type");
        }
    }

}
