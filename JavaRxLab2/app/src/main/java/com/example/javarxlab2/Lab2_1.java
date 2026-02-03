package com.example.javarxlab2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.stream.Stream;

public class Lab2_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab21);
        // Lab 2_1
        List<String> names = List.of(
                "Patrick Ross",
                "Kelly Wood",
                "James Moore",
                "Janice Coleman",
                "Mary Carter"
        );
        long count = names.stream()
                        .filter(str -> str.length() < 12 && str.startsWith("J"))
                        .count();
        Log.d("Lab2", String.valueOf(count));

    }
}