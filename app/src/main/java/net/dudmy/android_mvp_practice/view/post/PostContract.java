package net.dudmy.android_mvp_practice.view.post;

import net.dudmy.android_mvp_practice.data.Post;

/**
 * Created by yujin on 2017. 5. 31..
 */

public interface PostContract {

    interface Presenter {

        void loadPost(String id);

        void detachView();

        void deletePost(String id);
    }

    interface View {

        void showPost(Post post);

        void toastDeletedMessage();

        void toastCannotFindItem();

        void goToBoardPage();
    }
}
