package net.dudmy.android_mvp_practice.view.board;

import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.data.source.PostRepository;
import net.dudmy.android_mvp_practice.listener.OnItemClickListener;
import net.dudmy.android_mvp_practice.utils.SchedulerProvider;
import net.dudmy.android_mvp_practice.view.board.adapter.BoardAdapterContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class BoardPresenter implements BoardContract.Presenter, OnItemClickListener {

    private BoardContract.View view;
    private final BoardAdapterContract.View adapterView;
    private final BoardAdapterContract.Model adapterModel;
    private final PostRepository repository;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    public BoardPresenter(BoardContract.View view, BoardAdapterContract.View adapterView,
                          BoardAdapterContract.Model adapterModel, PostRepository repository) {
        this.view = view;
        this.adapterView = adapterView;
        this.adapterModel = adapterModel;
        this.repository = repository;

        this.adapterView.setClickListener(this);
    }

    @Override
    public void detachView() {
        this.view = null;
        disposable.clear();
    }

    @Override
    public void loadPosts() {
        disposable.add(repository.getPosts()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(@NonNull List<Post> posts) {
                        adapterModel.addItems(posts);
                        adapterView.refresh();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        view.setPlaceholder(adapterModel.size() == 0);
                    }
                }));
    }

    @Override
    public void addPost(String title, String content, String name) {
        if (title.isEmpty()) {
            view.toastEnterTitle();
        } else if (content.isEmpty()) {
            view.toastEnterContent();
        } else {
            Post post = new Post(title, content, name);

            repository.savePost(post);

            adapterModel.addItem(post);
            adapterView.refresh();

            view.setPlaceholder(false);
            view.showSavedMessage();
        }
    }

    @Override
    public void onItemClick(int position) {
        Post post = adapterModel.getItem(position);

        if (post == null) {
            view.toastCannotFindItem();
        } else {
            view.startPostPage(post.getId());
        }
    }
}
