package com.example.javarxlab2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.stream.Stream;

public class Lab2_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab22);
        // Lab 2_2
        //Suppose you have the below code, What does this code print?
        // first we create a stream of string one two three four
        // anyMatch() -> any one of the stream is allow the condition
        // so it will print true
        // after the method antMatch() run the stream is close as it terminal
        // like collect(),AllMatch(),toList()
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        boolean match = stream.anyMatch(s -> s.contains("four"));
        Log.d("Lab2", String.valueOf(match));
    }
}