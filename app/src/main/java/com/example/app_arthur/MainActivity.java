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


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private List<String> listNomes;
    private ArrayAdapter<String> usb;

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

        dbHelper = new DatabaseHelper(this);

        EditText entradaNome = findViewById(R.id.EntradaNome);
        Button btnAdicionar = findViewById(R.id.button);
        ListView listanomes = findViewById(R.id.ListView);

        listNomes = new ArrayList<>();
        usb = new ArrayAdapter<>(
                this,
                R.layout.item_lista,
                R.id.textView,
                listNomes
        );
        listanomes.setAdapter(usb);

        atualizarLista();

        btnAdicionar.setOnClickListener(v -> {
            String novoNome = entradaNome.getText().toString().trim();

            if (!novoNome.isEmpty()) {
                long id = dbHelper.inserirNota(novoNome, "");
                if (id != -1) {
                    Toast.makeText(this, "Cadastrado no banco de dados!", Toast.LENGTH_SHORT).show();
                    entradaNome.setText("");
                    atualizarLista();
                } else {
                    Toast.makeText(this, "Erro ao salvar no banco!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Digite um nome!", Toast.LENGTH_SHORT).show();
            }
        });

        listanomes.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(getApplicationContext(), listNomes.get(position), Toast.LENGTH_LONG).show()
        );
    }

    private void atualizarLista() {
        listNomes.clear();
        listNomes.addAll(dbHelper.obterTodasNotas());
        usb.notifyDataSetChanged();
    }
}
