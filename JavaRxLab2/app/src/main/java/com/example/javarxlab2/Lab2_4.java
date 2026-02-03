package com.example.javarxlab2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class Lab2_4 extends AppCompatActivity {
    TextView Result;
    EditText Search;
    List<String> names = List.of(
            "Alaa Adel",
            "Abdallah",
            "Abdelrahman Rashed ",
            "Esraa Ehab",
            "Mohamed Ayman",
            "Ahmed Salah ",
            "Mona Hamed ",
            "Yomna Mohamed",
            "Hend"
    );
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Search = findViewById(R.id.searchEditText);
        Result = findViewById(R.id.resultsTextView);
        Result.setText(getList(names));
        Observable<String> textObservable = Observable.create(emitter -> {
            Search.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());
                }
                @Override public void afterTextChanged(Editable s) {}
            });
        });

        disposable = textObservable
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(text -> {
                    List<String> result = names.stream()
                            .filter(name -> name.toLowerCase().contains(text.toLowerCase()))
                            .collect(Collectors.toList());
                    Result.post(() -> {
                        Result.setText(getList(result));
                    });
                });
    }

    private String getList(List<String> list) {
        return list.stream()
                .map(name -> "-" + name)
                .collect(Collectors.joining("\n"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}