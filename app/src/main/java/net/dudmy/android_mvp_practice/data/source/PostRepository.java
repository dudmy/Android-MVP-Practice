package net.dudmy.android_mvp_practice.data.source;

import net.dudmy.android_mvp_practice.data.Post;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

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
    public void getPosts(final LoadPostsCallback callback) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Post> results = realm.where(Post.class)
                        .findAllSorted("date");

                List<Post> posts = realm.copyFromRealm(results);

                if (posts.isEmpty()) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onPostsLoaded(posts);
                }
            }
        });
    }

    @Override
    public void getPost(final String id, final LoadPostCallback callback) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Post result = realm.where(Post.class)
                        .equalTo("id", id)
                        .findFirst();

                if (result == null) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onPostLoaded(result);
                }
            }
        });
    }

    @Override
    public void savePost(final Post post) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(post);
            }
        });
    }

    @Override
    public void deletePost(final String id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Post.class)
                        .equalTo("id", id)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }
}
