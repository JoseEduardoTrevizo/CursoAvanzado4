package com.example.cursoavanzado;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.cursoavanzado.VariablesGlobales.LOG_TAG;
import static com.example.cursoavanzado.VariablesGlobales.duraciontareaAsincrona;

public class tareaAsincrona extends AsyncTask<Void,Void,Void>{

private int segundos=0;
private final String CHANNEL_ID= "10";
private Context context;

public tareaAsincrona(Context context){
    this.context=context;
}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(LOG_TAG,"tareaAsincrona:onPreExecute");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.i(LOG_TAG,"tareaAsincrona:onInBackground");
    while (segundos<duraciontareaAsincrona){
    delay(1000);
    segundos++;
}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        lanzarNoti("Informacion muy importante","Tarea Asincrona Terminada ;)");
        Log.i(LOG_TAG,"tareaAsincrona:onPosExecute");

    }

    private  void delay(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    @SuppressLint("ObsoleteSdkInt")
    private  void createNotificacionChanel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence channelName="canalUno";
            String channelDescription="canalUnoDescription";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel= new NotificationChannel(CHANNEL_ID,channelName,importance);
            channel.setDescription(channelDescription);
            NotificationManager nm= context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }
    private void lanzarNoti(String titulo,String mensaje){
    createNotificacionChanel();
        Intent intent= new Intent(context,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.mipmap.bell)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManagerCompat nc= NotificationManagerCompat.from(context);
        nc.notify(20,builder.build());
    }

}
