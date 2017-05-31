package net.dudmy.android_mvp_practice.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yujin on 2017. 5. 31..
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreActivity {
}
