package com.example.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static com.example.cursoavanzado.metodosglobales.obtenerversionApp;

public class LoadActivity extends AppCompatActivity {
////////////////////////////////////VISTAS///////////////
    TextView txtversion,txtinfo;
    Button btnPermisos;
/////////////////////////////////////////////////////////

////////////////////////////////////UTILIDADES///////////
Context context;
Timer primerTimer,segundoTimer;
boolean permisoCamara= false;
boolean permisoLlamadas= false;
/////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txtinfo=findViewById(R.id.txt_load_info);
        txtversion=findViewById(R.id.txt_version);
        btnPermisos=findViewById(R.id.btn_permisos);
        context= LoadActivity.this;
        String version= obtenerversionApp(context);
        txtversion.setText(version);
        revisarPermisos();


    }

    @Override
    protected void onResume() {
        super.onResume();
        btnPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revisarPermisos();
                btnPermisos.setVisibility(view.INVISIBLE);
            }
        });
    }

    private void revisarPermisos(){
    permisoCamara= checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED;
    permisoLlamadas=checkSelfPermission(CALL_PHONE)==PackageManager.PERMISSION_GRANTED;
    if (permisoCamara&&permisoLlamadas){
        setprimerTimer(3000);
        }else {
        requestPermissions(new String[]{CAMERA,CALL_PHONE},25);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==25){
            permisoCamara= checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED;
            permisoLlamadas=checkSelfPermission(CALL_PHONE)==PackageManager.PERMISSION_GRANTED;
            if(permisoCamara&&permisoLlamadas){
                setprimerTimer(3000);
            }else{
                txtinfo.setText(getString(R.string.alertapermisos));
                btnPermisos.setVisibility(View.VISIBLE);
            }
        }
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