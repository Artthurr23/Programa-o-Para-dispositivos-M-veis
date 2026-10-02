
package com.example.app_arthur;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String nome[] = new String[]{
                "Robson", "João", "Júlia", "Kiara", "Bernardo", "Luiza", "Luana"
        };

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom
                    );
                    return insets;
                });
        ListView listanomes = findViewById(R.id.ListView);
        ArrayAdapter<String> usb = new ArrayAdapter<>(getApplicationContext(), R.layout.item_lista, R.id.textview,nome
        );
        listanomes.setAdapter(usb);
        listanomes.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(),nome[position],Toast.LENGTH_LONG).show();
        });

    }
}