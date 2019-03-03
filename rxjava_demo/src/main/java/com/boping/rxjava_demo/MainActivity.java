package com.boping.rxjava_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 随着业务逻辑变得越来越复杂，RxJava依然可以保持简介
 */
public class MainActivity extends AppCompatActivity {

    private Observable<String> observable;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * RxJava链式编程
     */
    public void linkCreateObservable() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {//1、初始化被观察者
                Log.d(TAG, "subscribe: " + Thread.currentThread().getName());//IO子线程
                emitter.onNext("张三");
                emitter.onNext("李四");
//                emitter.onError(new Throwable("xxxxx"));//只能发一次，否则报错，且后面发送的任何消息观察者不再接收
                emitter.onComplete();//能发多次，但被观察者只能接收一次，且后面发送的任何消息观察者不再接收
                emitter.onNext("王五");//观察者接收不到
            }
        })
                .observeOn(AndroidSchedulers.mainThread())//2、将观察者切换到主线程
                .subscribeOn(Schedulers.io())//3、将被观察者切换到子线程
                .subscribe(new Observer<String>() {//4、创建观察者并订阅
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Observable onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "Observable onNext: " + s + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Observable onError: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Observable onComplete: " + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * 创建被观察者
     */
    public void createObservable(View view) {

//        //1、发送对应的方法
//        observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                Log.d(TAG, "subscribe: " + Thread.currentThread().getName());//IO子线程
//                emitter.onNext("张三");
//                emitter.onNext("李四");
////                emitter.onError(new Throwable("xxxxx"));//只能发一次，否则报错，且后面发送的任何消息观察者不再接收
//                emitter.onComplete();//能发多次，但被观察者只能接收一次，且后面发送的任何消息观察者不再接收
//                emitter.onNext("王五");//观察者接收不到
//            }
//        });

        //2、发送多个数据
//        observable = Observable.just("李四","王五麻子");

//        //3、发送数组
//        observable = Observable.fromArray("张三","李四","王五麻子");
//
//        //4、发送一个数据
//        observable = Observable.fromCallable(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "只有张三";
//            }
//        });
    }

    /**
     * 创建观察者
     */
    public void createObserver(View view) {
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String o) {

                Log.d(TAG, "onNext: " + o + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: " + Thread.currentThread().getName());
            }
        };
    }

    /**
     * 订阅
     *
     * @param view
     */
    public void subscribe(View view) {
        if (observable != null && observer != null)
            observable.subscribe(observer);
    }
}
