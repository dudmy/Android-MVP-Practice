package net.dudmy.android_mvp_practice.data.source;

import net.dudmy.android_mvp_practice.data.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yujin on 2017. 5. 31..
 */

public interface PostDataSource {

    Observable<List<Post>> getPosts();

    Observable<Post> getPost(String id);

    void savePost(Post post);

    void deletePost(String id);
}
