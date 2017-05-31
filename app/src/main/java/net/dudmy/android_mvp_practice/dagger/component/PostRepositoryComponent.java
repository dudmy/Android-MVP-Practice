package net.dudmy.android_mvp_practice.dagger.component;

import net.dudmy.android_mvp_practice.dagger.module.ApplicationModule;
import net.dudmy.android_mvp_practice.dagger.module.PostRepositoryModule;
import net.dudmy.android_mvp_practice.data.source.PostRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Singleton
@Component(modules = {PostRepositoryModule.class, ApplicationModule.class})
public interface PostRepositoryComponent {

    PostRepository getPostRepository();
}
