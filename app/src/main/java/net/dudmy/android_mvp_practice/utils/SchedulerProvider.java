package net.dudmy.android_mvp_practice.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yujin on 2017. 6. 9..
 */

public class SchedulerProvider {

    private static SchedulerProvider INSTANCE;

    private SchedulerProvider() {
    }

    public static SchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulerProvider();
        }
        return INSTANCE;
    }

    /**
     * 이벤트 루프나 콜백을 처리하는 등의 동작을 수행하기에 적합
     */
    public Scheduler computation() {
        return Schedulers.computation();
    }

    /**
     * IO 관련 동작을 수행하기에 적합 (네트워크 요청이나 디크스 오퍼레이션)
     */
    public Scheduler io() {
        return Schedulers.io();
    }

    /**
     * UI 스레드에서 실행
     */
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}