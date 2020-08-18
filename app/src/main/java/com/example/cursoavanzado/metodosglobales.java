package com.example.cursoavanzado;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import static com.example.cursoavanzado.VariablesGlobales.usuariosApp;

public class metodosglobales {
    //clase donde estan los metodos globales

    public static String obtenerversionApp(Context context){
        String respuesta="";
        try {
            PackageInfo packageInfo=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            respuesta=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public  static  void leerBaseDeDatos(Context context){
        usuariosApp = new ArrayList<>();//inizialisamos usuarios
        SQLiteDatabase database;
        usuariosDB usuariosDB = new usuariosDB(context);
        database =usuariosDB.getWritableDatabase();
        Cursor cursor= database.query(usuriosContrato.usuariosColumnas.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String nombre= cursor.getString(1);
            String password= cursor.getString(2);
            String genero= cursor.getString(3);
            String rol= cursor.getString(4);
            String correo= cursor.getString(5);
            usuariosApp.add(new Usuario(id,nombre,password,genero,rol,correo));
        }
        cursor.close();
    }

}
