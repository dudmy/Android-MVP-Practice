package net.dudmy.android_mvp_practice.view.board.adapter;

import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by yujin on 2017. 5. 31..
 */

public interface BoardAdapterContract {

    interface Model {

        Post getItem(int position);

        void addItem(Post post);

        void addItems(List<Post> posts);

        int size();
    }

    interface View {

        void refresh();

        void setClickListener(OnItemClickListener itemClickListener);
    }
}
