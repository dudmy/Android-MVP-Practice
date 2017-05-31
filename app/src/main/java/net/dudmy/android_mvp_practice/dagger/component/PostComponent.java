package net.dudmy.android_mvp_practice.dagger.component;

import net.dudmy.android_mvp_practice.dagger.module.PostModule;
import net.dudmy.android_mvp_practice.dagger.scope.PreActivity;
import net.dudmy.android_mvp_practice.view.post.PostActivity;

import dagger.Component;

/**
 * Created by yujin on 2017. 5. 31..
 */

@PreActivity
@Component(dependencies = PostRepositoryComponent.class, modules = PostModule.class)
public interface PostComponent {

    void inject(PostActivity postActivity);
}
