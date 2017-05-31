package net.dudmy.android_mvp_practice.view.post;

import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.data.source.PostDataSource;
import net.dudmy.android_mvp_practice.data.source.PostRepository;

import javax.inject.Inject;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class PostPresenter implements PostContract.Presenter {

    private PostContract.View view;
    private final PostRepository repository;

    @Inject
    public PostPresenter(PostContract.View view, PostRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadPost(String id) {
        repository.getPost(id, new PostDataSource.LoadPostCallback() {
            @Override
            public void onPostLoaded(Post post) {
                if (view != null) {
                    view.showPost(post);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (view != null) {
                    view.toastCannotFindItem();
                }
            }
        });
    }

    @Override
    public void deletePost(String id) {
        repository.deletePost(id);
        view.toastDeletedMessage();
        view.goToBoardPage();
    }
}
