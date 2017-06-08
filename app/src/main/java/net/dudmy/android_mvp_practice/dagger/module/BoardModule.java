package net.dudmy.android_mvp_practice.dagger.module;

import net.dudmy.android_mvp_practice.utils.SchedulerProvider;
import net.dudmy.android_mvp_practice.view.board.BoardContract;
import net.dudmy.android_mvp_practice.view.board.BoardPresenter;
import net.dudmy.android_mvp_practice.view.board.adapter.BoardAdapter;
import net.dudmy.android_mvp_practice.view.board.adapter.BoardAdapterContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Module
public class BoardModule {

    private final BoardAdapter adapter;
    private final BoardContract.View view;

    public BoardModule(BoardAdapter adapter, BoardContract.View view) {
        this.adapter = adapter;
        this.view = view;
    }

    @Provides
    BoardAdapterContract.Model provideBoardAdapterModel() {
        return adapter;
    }

    @Provides
    BoardAdapterContract.View provideBoardAdapterView() {
        return adapter;
    }

    @Provides
    BoardContract.Presenter provideBoardPresenter(BoardPresenter boardPresenter) {
        return boardPresenter;
    }

    @Provides
    BoardContract.View provideBoardView() {
        return view;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
