
package com.example.app_arthur;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });


        List<String> listNomes = new ArrayList<>(Arrays.asList(
                "aRTHUR"
        ));


        EditText entradaNome = findViewById(R.id.EntradaNome);
        Button btnAdicionar = findViewById(R.id.button);
        ListView listanomes = findViewById(R.id.ListView);
        ArrayAdapter<String> usb = new ArrayAdapter<>(
                this,
                R.layout.item_lista,
                R.id.textView,
                listNomes
        );
        listanomes.setAdapter(usb);


        btnAdicionar.setOnClickListener(v -> {
            String novoNome = entradaNome.getText().toString().trim();

            if (!novoNome.isEmpty()) {
                listNomes.add(novoNome);
                usb.notifyDataSetChanged();
                entradaNome.setText("");
            } else {
                Toast.makeText(this, "Digite um nome!", Toast.LENGTH_SHORT).show();
            }
        });
        listanomes.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(), listNomes.get(position), Toast.LENGTH_LONG).show();
        });
    }
}