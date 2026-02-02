package com.example.javarx;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Lab1_2_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab122);
        //----------.never()------------
        // the observer is shut down or silent
        // not execute any thing or any statment
        // for testing ??
        //The main purpose is to prevent completing a stream or provide non-emitting
        // Observable to operators that otherwise react to signals of secondary
        // sources (for example, window, takeUntil).
        // In addition, it is great for testing timeout with sequences.
        Observable observable = Observable.never();
        observable.subscribe(
                item -> Log.i("RX", "Received: " + item),
                error -> Log.e("RX", "Error: " + error),
                () -> Log.i("RX", "Completed!")
        );
    }
}