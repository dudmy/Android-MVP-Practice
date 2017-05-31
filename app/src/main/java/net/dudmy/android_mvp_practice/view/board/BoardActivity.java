package net.dudmy.android_mvp_practice.view.board;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import net.dudmy.android_mvp_practice.MyApplication;
import net.dudmy.android_mvp_practice.R;
import net.dudmy.android_mvp_practice.dagger.component.DaggerBoardComponent;
import net.dudmy.android_mvp_practice.dagger.module.BoardModule;
import net.dudmy.android_mvp_practice.databinding.ActivityBoardBinding;
import net.dudmy.android_mvp_practice.databinding.DialogPostingBinding;
import net.dudmy.android_mvp_practice.view.board.adapter.BoardAdapter;
import net.dudmy.android_mvp_practice.view.post.PostActivity;

import javax.inject.Inject;

public class BoardActivity extends AppCompatActivity implements BoardContract.View {

    private ActivityBoardBinding binding;

    @Inject
    BoardPresenter boardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board);
        binding.setActivity(this);

        setSupportActionBar(binding.toolbar);

        BoardAdapter boardAdapter = new BoardAdapter();

        DaggerBoardComponent.builder()
                .postRepositoryComponent(((MyApplication) getApplication()).getPostRepositoryComponent())
                .boardModule(new BoardModule(boardAdapter, this))
                .build()
                .inject(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(boardAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boardPresenter.loadPosts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boardPresenter.detachView();
    }

    @Override
    public void showSavedMessage() {
        Snackbar.make(binding.getRoot(), "Post has been saved.", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void toastEnterTitle() {
        Toast.makeText(this, "Please enter the title.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastEnterContent() {
        Toast.makeText(this, "Please enter the content.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastCannotFindItem() {
        Toast.makeText(this, "Cannot find item. Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPlaceholder(boolean show) {
        binding.tvPlaceholder.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void startPostPage(String id) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("postId", id);
        startActivity(intent);
    }

    public void onAddButtonClick() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_posting, null);

        final DialogPostingBinding dialogBinding = DataBindingUtil.bind(dialogView);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setMessage("게시글 작성")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boardPresenter.addPost(dialogBinding.edtTitle.getText().toString(), dialogBinding.edtContent.getText().toString(), dialogBinding.edtName.getText().toString());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
