package com.example.cursoavanzado;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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
}
