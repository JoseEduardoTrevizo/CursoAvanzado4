package com.example.cursoavanzado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.PublicKey;

import static com.example.cursoavanzado.VariablesGlobales.LOG_TAG;
import static com.example.cursoavanzado.VariablesGlobales.usuariosApp;

public class usuariosDB extends SQLiteOpenHelper {
private static final int VERSION_BASEDATOS=1;
public  static final String NOMBRE_BASEDATOS="usuarios.db";

    public usuariosDB(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String comando = "CREATE TABLE " + usuriosContrato.usuariosColumnas.TABLE_NAME + " (" +
                usuriosContrato.usuariosColumnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                usuriosContrato.usuariosColumnas.NOMBRE + " TEXT NOT NULL," +
                usuriosContrato.usuariosColumnas.PASSWORD + " TEXT NOT NULL," +
                usuriosContrato.usuariosColumnas.GENERO + " TEXT NOT NULL," +
                usuriosContrato.usuariosColumnas.ROL + " TEXT NOT NULL," +
                usuriosContrato.usuariosColumnas.CORREO + " TEXT NOT NULL" +
                ")";
        try {
            sqLiteDatabase.execSQL(comando);
            Log.i(LOG_TAG,"Comando ejecutado en SQL " + comando);
            Log.i(LOG_TAG,"Tabla: " + usuriosContrato.usuariosColumnas.TABLE_NAME +
                    " Creada exitosamente!");
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exeception on db.execSQL:" + e);
        }
    }


    /* Se ejecuta la primera vez que se crea la base de datos o cuando cambiar el numero de version*/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + NOMBRE_BASEDATOS);
        onCreate(sqLiteDatabase);
    }
}
