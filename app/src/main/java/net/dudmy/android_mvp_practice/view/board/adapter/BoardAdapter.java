package net.dudmy.android_mvp_practice.view.board.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dudmy.android_mvp_practice.R;
import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.databinding.ItemBoardBinding;
import net.dudmy.android_mvp_practice.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> implements
        BoardAdapterContract.Model, BoardAdapterContract.View {

    private List<Post> posts;
    private OnItemClickListener itemClickListener;

    public BoardAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_board, parent, false);
        return new ViewHolder(itemView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        Post post = getItem(position);
        holder.binding.setPost(post);
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public void addItem(Post post) {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);
    }

    @Override
    public void addItems(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemBoardBinding binding;

        ViewHolder(View itemView, final OnItemClickListener itemClickListener) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
