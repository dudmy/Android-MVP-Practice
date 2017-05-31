package net.dudmy.android_mvp_practice.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
