package com.example.javarxlab2;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class Lab2_3 extends AppCompatActivity {
    private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab23);
        // Lab 2_3
        // Try to use debounce operators and specify its type (terminal or non-terminal)
        //debounce(long timeout, java.util.concurrent.TimeUnit unit)
        //Returns an Observable that mirrors the source Observable
        // as it return Observable so it it non-terminal
        Observable<Integer> events = Observable.just(1, 2, 3, 4, 5)
                .concatMap(i -> Observable.just(i)
                        .delay(i * 100, TimeUnit.MILLISECONDS));
        disposable = events.debounce(200, TimeUnit.MILLISECONDS)
                .subscribe(
                        n-> Log.d("Lab2", n.toString()),
                        throwable -> Log.d("Lab2", "Error"),
                        ()->Log.d("Lab2", "onComplete")
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            Log.d("Lab2", "Disposable disposed");
        }
    }
}