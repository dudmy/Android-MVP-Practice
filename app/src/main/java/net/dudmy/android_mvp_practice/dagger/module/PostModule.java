package net.dudmy.android_mvp_practice.dagger.module;

import net.dudmy.android_mvp_practice.utils.SchedulerProvider;
import net.dudmy.android_mvp_practice.view.post.PostContract;
import net.dudmy.android_mvp_practice.view.post.PostPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Module
public class PostModule {

    private final PostContract.View view;

    public PostModule(PostContract.View view) {
        this.view = view;
    }

    @Provides
    PostContract.View providePostView() {
        return view;
    }

    @Provides
    PostContract.Presenter providePostPresenter(PostPresenter postPresenter) {
        return postPresenter;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
