package net.dudmy.android_mvp_practice.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Module
public class PostRepositoryModule {

    public PostRepositoryModule() {
    }

    @Singleton
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
