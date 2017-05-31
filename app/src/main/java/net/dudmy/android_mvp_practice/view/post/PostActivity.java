package net.dudmy.android_mvp_practice.view.post;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.dudmy.android_mvp_practice.MyApplication;
import net.dudmy.android_mvp_practice.R;
import net.dudmy.android_mvp_practice.dagger.component.DaggerPostComponent;
import net.dudmy.android_mvp_practice.dagger.module.PostModule;
import net.dudmy.android_mvp_practice.data.Post;
import net.dudmy.android_mvp_practice.databinding.ActivityPostBinding;

import javax.inject.Inject;

public class PostActivity extends AppCompatActivity implements PostContract.View {

    private ActivityPostBinding binding;

    private String postId;

    @Inject
    PostPresenter postPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);

        postId = getIntent().getStringExtra("postId");

        DaggerPostComponent.builder()
                .postRepositoryComponent(((MyApplication) getApplication()).getPostRepositoryComponent())
                .postModule(new PostModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        postPresenter.loadPost(postId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postPresenter.detachView();
    }

    @Override
    public void showPost(Post post) {
        binding.setPost(post);
    }

    @Override
    public void toastDeletedMessage() {
        Toast.makeText(this, "Post has been deleted.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastCannotFindItem() {
        Toast.makeText(this, "Cannot find item. Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToBoardPage() {
        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            postPresenter.deletePost(postId);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
