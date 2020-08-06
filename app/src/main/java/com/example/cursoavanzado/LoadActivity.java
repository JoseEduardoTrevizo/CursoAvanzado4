package com.example.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.cursoavanzado.metodosglobales.obtenerversionApp;

public class LoadActivity extends AppCompatActivity {
////////////////////////////////////VISTAS///////////////
    TextView txtversion,txtinfo;
/////////////////////////////////////////////////////////

////////////////////////////////////UTILIDADES///////////
Context context;
Timer primerTimer,segundoTimer;
/////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtinfo=findViewById(R.id.txt_load_info);
        txtversion=findViewById(R.id.txt_version);
        context= LoadActivity.this;
        String version= obtenerversionApp(context);
        txtversion.setText(version);
        setprimerTimer(3000);

    }
    private void  setprimerTimer (int milis){
        primerTimer= new Timer(); //Declarando el timer como uno nuevo
        primerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // aqui se ejecuta el codigo despues que termina el timer
                Handler handler= new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //AQUI NOS REGRESAMOS AL HILO PRINSIPAL
                        txtinfo.setText(getString(R.string.Preparando));
                        setsegundoTimer(2500);
                    }
                });
            }
        },milis);
    }
    private void  setsegundoTimer (int milis){
        segundoTimer= new Timer(); //Declarando el timer como uno nuevo
        segundoTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // aqui se ejecuta el codigo despues que termina el timer
                Handler handler= new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //AQUI NOS REGRESAMOS AL HILO PRINSIPAL
                        txtinfo.setText(getString(R.string.Espera));
                        mydata mydata= new mydata(context);
                        boolean acceso= mydata.getLogeo();
                        if(acceso){
                            startActivity(new Intent(context,MainActivity.class));
                        }else{
                        startActivity(new Intent(context,LoginActivity.class));
                        }
                    }
                });
            }
        },milis);
    }
}