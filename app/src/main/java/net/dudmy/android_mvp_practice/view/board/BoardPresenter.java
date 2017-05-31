package net.dudmy.android_mvp_practice.view.board;

import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.data.source.PostDataSource;
import net.dudmy.android_mvp_practice.data.source.PostRepository;
import net.dudmy.android_mvp_practice.listener.OnItemClickListener;
import net.dudmy.android_mvp_practice.view.board.adapter.BoardAdapterContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class BoardPresenter implements BoardContract.Presenter, OnItemClickListener {

    private BoardContract.View view;
    private final BoardAdapterContract.View adapterView;
    private final BoardAdapterContract.Model adapterModel;
    private final PostRepository repository;

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
    }

    @Override
    public void loadPosts() {
        repository.getPosts(new PostDataSource.LoadPostsCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                if (view != null) {
                    view.setPlaceholder(false);
                    adapterModel.addItems(posts);
                    adapterView.refresh();
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (view != null) {
                    view.setPlaceholder(true);
                }
            }
        });
    }

    @Override
    public void addPost(String title, String content, String name) {
        if (title.isEmpty()) {
            view.toastEnterTitle();
            return;
        }

        if (content.isEmpty()) {
            view.toastEnterContent();
            return;
        }

        Post post = new Post(title, content, name);

        repository.savePost(post);

        adapterModel.addItem(post);
        adapterView.refresh();

        view.setPlaceholder(false);
        view.showSavedMessage();
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
