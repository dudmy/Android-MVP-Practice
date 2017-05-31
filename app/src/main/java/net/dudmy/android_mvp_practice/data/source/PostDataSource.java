package net.dudmy.android_mvp_practice.data.source;

import net.dudmy.android_mvp_practice.data.Post;

import java.util.List;

/**
 * Created by yujin on 2017. 5. 31..
 */

public interface PostDataSource {

    interface LoadPostsCallback {

        void onPostsLoaded(List<Post> posts);

        void onDataNotAvailable();
    }

    interface LoadPostCallback {

        void onPostLoaded(Post post);

        void onDataNotAvailable();
    }

    void getPosts(LoadPostsCallback callback);

    void getPost(String id, LoadPostCallback callback);

    void savePost(Post post);

    void deletePost(String id);
}
