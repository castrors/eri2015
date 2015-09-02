package br.ufms.eri.todolist;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by rodrigo on 26/08/15.
 */
public class TODOListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
