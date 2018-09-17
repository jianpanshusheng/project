package demo.checkdemo;

import android.app.Application;


/**
 * Created by admin on 2018/9/4.
 */
public class MyApp extends Application {


    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static MyApp getInstance() {
        return app;
    }

}
