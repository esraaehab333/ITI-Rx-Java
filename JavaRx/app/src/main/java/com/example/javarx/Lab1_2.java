package com.example.javarx;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Lab1_2 extends AppCompatActivity {

    private Observable<Long> observable;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab);
        //----------.intervalRang(long, long , long , time )------------
        // it make the observer execute after a spacific perid
        //after this period end it stop
        // but this is cause memory leak so we use disposble to avoid mamory leak
        // to distroy it with the activity
        observable = Observable.intervalRange(1, 10, 0, 2, TimeUnit.SECONDS);
        disposable = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(number -> Log.i("RX", "Interval emitted: " + number)
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
