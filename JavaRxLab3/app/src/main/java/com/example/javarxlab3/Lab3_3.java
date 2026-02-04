package com.example.javarxlab3;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Lab3_3 extends AppCompatActivity {
    private static final String TAG = "Lab3_3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab33);
        ObservableTransformer<Integer, Integer> filterAndSchedule = upstream ->
                upstream.filter(i -> i > 0)
                        .distinctUntilChanged()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        Observable.just(1, 1, 2, 2, -1, 3, 3)
                .compose(filterAndSchedule)
                .subscribe(
                        i -> Log.d(TAG, "Value: " + i),
                        throwable -> Log.e(TAG, "Error: ", throwable),
                        () -> Log.d(TAG, "Completed")
                );

    }
}