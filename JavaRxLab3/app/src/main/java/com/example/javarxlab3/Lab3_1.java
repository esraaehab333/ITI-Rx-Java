package com.example.javarxlab3;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Lab3_1 extends AppCompatActivity {

    private static final String TAG = "Lab3_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab31);
        Observable<Integer> myobservable = Observable.create(source -> {
            Log.d(TAG, "Hello MAD");
            source.onNext(1);
            source.onNext(2);
            source.onNext(3);
            source.onNext(4);
            source.onNext(5);
        });
        myobservable.subscribeOn(Schedulers.io())
                .doOnNext(integer -> Log.d(TAG, "Emitting item " + integer + " on: " + Thread.currentThread().getName()))
                .observeOn(Schedulers.newThread())
                .doOnNext(integer -> Log.d(TAG, "After Emitting item " + integer + " on: " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .doOnNext(integer -> Log.d(TAG, "After computation Thread Emitting item " + integer + " on: " + Thread.currentThread().getName()))
                .subscribe(integer -> Log.d(TAG, "Consuming item " + integer + " on: " + Thread.currentThread().getName()));

    }
}