package net.dudmy.android_mvp_practice.view.board;

/**
 * Created by yujin on 2017. 5. 31..
 */

public interface BoardContract {

    interface Presenter {

        void detachView();

        void loadPosts();

        void addPost(String title, String content, String name);
    }

    interface View {

        void showSavedMessage();

        void toastEnterTitle();

        void toastEnterContent();

        void toastCannotFindItem();

        void setPlaceholder(boolean show);

        void startPostPage(String id);
    }
}
