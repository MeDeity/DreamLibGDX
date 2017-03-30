package com.ares.voice;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * 一款优秀的游戏，音频/声音必不可少。LibdGDX 中的游戏音频可分为 音乐（Music） 和 音效（Sound）。
 * LibGDX 支持的音频格式有 mp3、ogg、wav 等，推荐使用 ogg 格式。
 * Created by Deity on 2016/12/26.
 */

public class VoiceUtils {

    /**加载音乐Music 支持暂停、恢复、循环播放等操作。当不需要使用到这个 Music 实例时，
     * 需要调用它的 dispose() 方法释放资源。
     * Music 在播放时会随着 应用 的 暂停（paused） 和 恢复（resumed） 自动 暂停 和 恢复
     * 设置循环播放 Musice.setLoop()
     * 设置开始播放 Musice.play()
     * 设置停止播放 Musice.stop()
     * @param mFileMusicPath 文件路径
     * @return 返回加载成功后的music
     * */
    public static Music loadMusic(String mFileMusicPath){
        return Gdx.app.getAudio().newMusic(Gdx.files.internal(mFileMusicPath));
    }

    /**
     * 一个 Sound 实例表示一个简短的音频剪辑，可以并行播放无数次，Sound 会把整个音效都加载到内存中，
     * 所以只能加载一些较小的音频文件，适合用来表示游戏中的各种音效（例如 打击音效，碰撞音效，得分音效 等）。
     * 当一个 Sound 实例不再需要被使用到时，需要调用它的 dispose() 方法释放资源。
     * @param mFileSoundPath 文件路径
     * @return 返回加载成功后的sound
     */
    public static Sound loadSound(String mFileSoundPath){
        return Gdx.app.getAudio().newSound(Gdx.files.internal(mFileSoundPath));
    }
}
