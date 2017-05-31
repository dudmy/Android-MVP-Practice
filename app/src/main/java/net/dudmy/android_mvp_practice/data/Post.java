package net.dudmy.android_mvp_practice.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yujin on 2017. 5. 31..
 */

public class Post extends RealmObject {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @NonNull
    private Date date;

    @NonNull
    private String name = "익명";

    public Post() {
    }

    public Post(@NonNull String title, @NonNull String content, @Nullable String name) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.date = new Date(System.currentTimeMillis());
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
