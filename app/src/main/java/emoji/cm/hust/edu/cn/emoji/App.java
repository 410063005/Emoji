package emoji.cm.hust.edu.cn.emoji;

import android.app.Application;

import timber.log.Timber;

/**
 * 一些全局配置
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Emoji.init(this);
    }
}
