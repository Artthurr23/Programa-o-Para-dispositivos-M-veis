package com.example.app_arthur;

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
String smin =edmin.getText().toString();
            String smax =edmin.getText().toString();
            if (smin.trim().equals(" "){
                edmin.setError("informe um valor");
                edmin.requestFocus();
                return;
            }
            if (smax.trim().equals(" "){
                edmax.setError("informe um valor");
                edmax.requestFocus();
                return;
            }



            int min = Integer.parseInt(edmin.getText().toString());
            int max = Integer.parseInt(edmax.getText().toString());
if(min>=max){
    edmax.setError("O valor maximo deve ser maior que o minimo");
    return;;
}
            int r = random.nextInt(max - min + 1) + min;

            tv.setText(String.valueOf(r));
        });

        bb.setOnClickListener(v -> {
            tv.setText("0");
        });

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }
}
