package com.example.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Objects;

import static com.example.cursoavanzado.VariablesGlobales.Latitude;
import static com.example.cursoavanzado.VariablesGlobales.Longitud;
import static com.example.cursoavanzado.VariablesGlobales.codigoQR;
import static com.example.cursoavanzado.VariablesGlobales.telefonodaniel;
import static com.example.cursoavanzado.VariablesGlobales.usuariosApp;
import static com.example.cursoavanzado.metodosglobales.leerBaseDeDatos;
import static com.example.cursoavanzado.metodosglobales.obtenerversionApp;



public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
//////////////////////////////////Vistas///////////////////////////
    Toolbar mainToolbar;
    Menu menuActivity;
    Context context;
    Button btnQR;
    TextView txtcodigoQR;

    //Objetos
    AlertDialog acercaDedialogo;

    private GoogleApiClient googleApiClient; //Cliente para la conexion de los servicios de google

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
        googleApiClient= new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
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

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
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
            case R.id.mostarmapa:
                Intent intent=new Intent(context,MapsActivity.class);
                startActivity(intent);
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    @SuppressLint("MissingPermission") Location userLocation= LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    if(userLocation!=null){
        Latitude=userLocation.getLatitude();
        Longitud=userLocation.getLongitude();
        Toast.makeText(context,"Latitude:"+String.valueOf(Latitude)+
                "/ Longitude:"+String.valueOf(Longitud),Toast.LENGTH_SHORT).show();
    }

    }

    @Override
    public void onConnectionSuspended(int i) {
    Toast.makeText(context,"Conoexion a google suspendida",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(context,"Conoexion a google fallida",Toast.LENGTH_SHORT).show();
    }
}