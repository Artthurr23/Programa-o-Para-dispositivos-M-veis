package com.example.app_arthur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notas_db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NOTAS = "notas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_NOTA = "nota";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT, " +
                    COLUMN_NOTA + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);
        onCreate(db);
    }

    public long inserirNota(String nome, String nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_NOTA, nota);
        long id = db.insert(TABLE_NOTAS, null, values);
        db.close();
        return id;
    }

    public List<String> obterTodasNotas() {
        List<String> listaNotas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTAS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME));
                String nota = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTA));

                StringBuilder item = new StringBuilder();
                item.append("ID: ").append(id);
                if (nome != null && !nome.isEmpty()) {
                    item.append(" | Nome: ").append(nome);
                }
                if (nota != null && !nota.isEmpty()) {
                    item.append(" | Nota: ").append(nota);
                }
                listaNotas.add(item.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaNotas;
    }
}
