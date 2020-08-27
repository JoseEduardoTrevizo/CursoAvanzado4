package com.example.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.example.cursoavanzado.VariablesGlobales.codigoQR;
import static com.example.cursoavanzado.VariablesGlobales.telefonodaniel;
import static com.example.cursoavanzado.VariablesGlobales.usuariosApp;
import static com.example.cursoavanzado.metodosglobales.leerBaseDeDatos;
import static com.example.cursoavanzado.metodosglobales.obtenerversionApp;



public class MainActivity extends AppCompatActivity {
//////////////////////////////////Vistas///////////////////////////
    Toolbar mainToolbar;
    Menu menuActivity;
    Context context;
    Button btnQR;
    TextView txtcodigoQR;

    //Objetos
    AlertDialog acercaDedialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,activity_qr.class));
            }
        });
        if(!codigoQR.isEmpty()){
        String codigoLeido=getString(R.string.codigoescaneado)+""+codigoQR;
        txtcodigoQR.setText(codigoLeido);
        }
    }

    private void configuracionesIniciales() {
        mainToolbar=findViewById(R.id.toolbarMain);
        setSupportActionBar(mainToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        context= MainActivity.this;
        btnQR= findViewById(R.id.btn_leerQR);
        txtcodigoQR=findViewById(R.id.txt_QR);
    }
/*
Infla la vista del menu que dise;amos (main menu)
ademas se iguala el menuActivity al menu de la vista inflada
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        this.menuActivity=menu;
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();// obtenemos el id del item presionado
        switch (id){
            case R.id.mainMenu_logout:
                mydata mydata=new mydata(context);
                mydata.setLogeo(false);
                startActivity(new Intent(context,LoadActivity.class));
                break;
            case R.id.AcercaDeAPP:
                mostrarCuadroDialogoAcercaDe();
                break;
            case R.id.agregarUsuario:
                startActivity(new Intent(context,AgregarUsuarioActivity.class));
                break;
            case R.id.VerusuariosApp:
                leerBaseDeDatos(context);
                if (usuariosApp.size()==0){
                    Toast.makeText(context,"No hay usuarios",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(context, UsuariosActivity.class));
                    break;
                }
            case R.id.lanzarnotificacion:
                tareaAsincrona tA=new tareaAsincrona(context);
                tA.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
            case R.id.realizarllamada:
                realizarllamada();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void realizarllamada(){
        Uri call= Uri.parse(telefonodaniel);
        Intent phoneIntent= new Intent(Intent.ACTION_CALL,call);
        context.startActivity(phoneIntent);
    }


    private void mostrarCuadroDialogoAcercaDe() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup parent = findViewById(R.id.parent);
        View vista= getLayoutInflater().inflate(R.layout.acerca_de_dialogo,parent,false);
        TextView txtversion= vista.findViewById(R.id.txtDialogoVersion);
        TextView txtsigueme = vista.findViewById(R.id.txtsigueme);
        Linkify.addLinks(txtsigueme,Linkify.WEB_URLS);//se agrega direccion url para redireccionar
        Button btnllamar = vista.findViewById(R.id.btn_llamar);//TODO falta codigo pararealizar llamada
        txtversion.setText(obtenerversionApp(context));
        builder.setView(vista);//Se configura la vista en el builder
        acercaDedialogo=builder.create();//se crea el cuadro de dialogo a partir del builder
        acercaDedialogo.show();//muestra cuadro de dialogo

    }
}