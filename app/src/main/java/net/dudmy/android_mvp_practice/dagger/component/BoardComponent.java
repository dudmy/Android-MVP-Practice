package net.dudmy.android_mvp_practice.dagger.component;

import net.dudmy.android_mvp_practice.dagger.module.BoardModule;
import net.dudmy.android_mvp_practice.dagger.scope.PreActivity;
import net.dudmy.android_mvp_practice.view.board.BoardActivity;

import dagger.Component;

/**
 * Created by yujin on 2017. 5. 31..
 */

@PreActivity
@Component(dependencies = PostRepositoryComponent.class, modules = BoardModule.class)
public interface BoardComponent {

    void inject(BoardActivity boardActivity);
}
