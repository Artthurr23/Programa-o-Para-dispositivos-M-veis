package com.example.app_arthur;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText edmin = findViewById(R.id.edmin);
        EditText edmax = findViewById(R.id.edmax);

        TextView tv = findViewById(R.id.textView);

        Button b = findViewById(R.id.Button);
        Button bb = findViewById(R.id.button2);

        b.setOnClickListener(v -> {

            Random random = new Random();

            String smin = edmin.getText().toString();
            String smax = edmax.getText().toString();

            if (smin.trim().isEmpty()) {
                edmin.setError("Informe um valor");
                edmin.requestFocus();
                return;
            }

            if (smax.trim().isEmpty()) {
                edmax.setError("Informe um valor");
                edmax.requestFocus();
                return;
            }

            int min = Integer.parseInt(smin);
            int max = Integer.parseInt(smax);

            if (min >= max) {
                edmax.setError("O valor máximo deve ser maior que o mínimo");
                edmax.requestFocus();
                return;
            }

            int r = random.nextInt(max - min + 1) + min;

            tv.setText(String.valueOf(r));
        });


        bb.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);

        });


    }
}
