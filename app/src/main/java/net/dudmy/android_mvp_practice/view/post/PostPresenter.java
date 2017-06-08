package net.dudmy.android_mvp_practice.view.post;

import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.data.source.PostRepository;
import net.dudmy.android_mvp_practice.utils.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class PostPresenter implements PostContract.Presenter {

    private PostContract.View view;
    private final PostRepository repository;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    public PostPresenter(PostContract.View view, PostRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void detachView() {
        this.view = null;
        disposable.clear();
    }

    @Override
    public void loadPost(String id) {
        disposable.add(repository.getPost(id)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableObserver<Post>() {
                    @Override
                    public void onNext(@NonNull Post post) {
                        view.showPost(post);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.toastCannotFindItem();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void deletePost(String id) {
        repository.deletePost(id);
        view.toastDeletedMessage();
        view.goToBoardPage();
    }
}
