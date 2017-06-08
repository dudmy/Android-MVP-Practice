package net.dudmy.android_mvp_practice.data.source;

import net.dudmy.android_mvp_practice.data.Post;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Singleton
public class PostRepository implements PostDataSource {

    @Inject
    Realm mRealm;

    @Inject
    public PostRepository() {
    }

    @Override
    public Observable<List<Post>> getPosts() {
        List<Post> posts = mRealm.copyFromRealm(
                mRealm.where(Post.class)
                        .findAllSorted("date"));

        if (posts.isEmpty()) {
            return Observable.empty();
        } else {
            return Observable.just(posts);
        }
    }

    @Override
    public Observable<Post> getPost(final String id) {
        Post result = mRealm.where(Post.class)
                .equalTo("id", id)
                .findFirst();

        if (result == null) {
            return Observable.error(new Throwable("empty"));
        } else {
            return Observable.just(result);
        }
    }

    @Override
    public void savePost(final Post post) {
        mRealm.executeTransaction(realm -> realm.copyToRealm(post));
    }

    @Override
    public void deletePost(final String id) {
        mRealm.executeTransaction(realm ->
                realm.where(Post.class)
                        .equalTo("id", id)
                        .findFirst()
                        .deleteFromRealm());
    }
}
