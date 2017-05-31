package net.dudmy.android_mvp_practice;

import android.app.Application;

import net.dudmy.android_mvp_practice.dagger.component.DaggerPostRepositoryComponent;
import net.dudmy.android_mvp_practice.dagger.component.PostRepositoryComponent;
import net.dudmy.android_mvp_practice.dagger.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class MyApplication extends Application {

    private PostRepositoryComponent postRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        postRepositoryComponent = DaggerPostRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }

    public PostRepositoryComponent getPostRepositoryComponent() {
        return postRepositoryComponent;
    }
}
