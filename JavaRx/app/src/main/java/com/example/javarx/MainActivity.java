package com.example.javarx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import io.reactivex.rxjava3.core.Observable;
public class MainActivity extends AppCompatActivity {

    private Integer[] arrays = {1, 2, 3, 4, 5};
    TextView observableFromArray, observableFromIterable;
    Button btnFromArray, btnFromIterable;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        observableFromArray = findViewById(R.id.observableFromArrayTextView);
        observableFromIterable = findViewById(R.id.observableFromIterableTextView);
        btnFromArray = findViewById(R.id.btnFromArray);
        btnFromIterable = findViewById(R.id.btnFromIterable);
        btnFromArray.setOnClickListener(v -> {
            Observable<Integer> observable1 = Observable.fromArray(arrays);
            StringBuilder builder1 = new StringBuilder();
            observable1.subscribe(
                    item -> {
                        builder1.append("item: ").append(item).append("\n");
                        observableFromArray.setText(builder1.toString());
                    },
                    error -> Log.e("RX", "Error", error),
                    () -> Log.d("RX", "Complete")
            );
        });
        btnFromIterable.setOnClickListener(v -> {
            Observable<Integer> observable2 = Observable.fromIterable(Arrays.asList(arrays));
            StringBuilder builder2 = new StringBuilder();
            observable2.subscribe(
                    item -> {
                        builder2.append("item: ").append(item).append("\n");
                        observableFromIterable.setText(builder2.toString());
                    },
                    error -> Log.e("RX", "Error", error),
                    () -> Log.d("RX", "Complete")
            );
        });
    }
}
