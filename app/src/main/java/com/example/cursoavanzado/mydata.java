package com.example.cursoavanzado;

import android.content.Context;
import android.content.SharedPreferences;

public class mydata {
    private SharedPreferences preferences;


    public mydata(Context context) {
        //CONFIGURAR PREFERENCES, SE CREA UN ARCHIVO DE MODO PRIVAFO LLAMADO SHARECURSOAVANZADO
        preferences=context.getSharedPreferences("shareCursoAvanzado",Context.MODE_PRIVATE);
    }
    public void guardarUsuario(String usuario){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("nombreUsuario",usuario);
        editor.apply();
    }
    public void setLogeo (Boolean acess){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("login",acess);
        editor.apply();
    }
    public String Obtenerusuario(){
        return preferences.getString("nombreUsuario","");
    }
    public boolean getLogeo(){
        return preferences.getBoolean("login",false);
    }


}
