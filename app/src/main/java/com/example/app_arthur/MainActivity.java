package com.example.app_arthur;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int version = 0;
    Button button;
    EditText edittext;
    EditText editext;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setup();

        button = findViewById(R.id.button);
        edittext = findViewById(R.id.EntradaNome);
        editext = findViewById(R.id.EntradaNota);

        button.setOnClickListener((View v) -> {
            String titulo = edittext.getText().toString();
            String txt = editext.getText().toString();

            ContentValues cv = new ContentValues();
            cv.put("titulo", titulo);
            cv.put("txt", txt);

            long status = db.insert("notas", null, cv);
            String msgStatus;
            if (status > 0) {
                msgStatus = "nota inserida " + status;
            } else {
                msgStatus = "falha na inserção da nota";
            }
            Toast.makeText(getApplicationContext(), msgStatus, Toast.LENGTH_LONG).show();
        });
    }

    public void setup() {
        db = openOrCreateDatabase("bdnotas", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, txt VARCHAR)");
    }
}
