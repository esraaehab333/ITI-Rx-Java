package com.example.javarxlab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Lab3_2 extends AppCompatActivity {

    private EditText etName, etAge;
    private Button btnShow;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab32);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnShow = findViewById(R.id.btnShow);
        tvResult = findViewById(R.id.tvResult);
        btnShow.setOnClickListener(v -> {
            String namesInput = etName.getText().toString().trim();
            String agesInput = etAge.getText().toString().trim();
            Observable<String> userObservable = Observable.zip(
                                    Observable.fromArray(namesInput.split("\n")),
                                    Observable.fromArray(agesInput.split("\n")),
                                    (name, age) -> {
                                        return new android.util.Pair<>(name.trim(),age.trim());
                                    }).filter(pair -> !pair.first.isEmpty() && !pair.second.isEmpty())
                            .map(pair -> "Name: " + pair.first + ", Age: " + pair.second).toList()
                            .map(list -> {
                                StringBuilder builder = new StringBuilder();
                                for (String s : list) builder.append(s).append("\n");
                                return builder.toString();
                            }).toObservable();

            userObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            result -> tvResult.setText(result),
                            throwable -> tvResult.setText("Error: " + throwable.getMessage())
                    );
        });

    }
}