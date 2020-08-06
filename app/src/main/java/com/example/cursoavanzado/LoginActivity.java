package com.example.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cursoavanzado.VariablesGlobales.passwordTemporal;
import static com.example.cursoavanzado.VariablesGlobales.usuarioTemporal;
import static com.example.cursoavanzado.metodosglobales.obtenerversionApp;

public class LoginActivity extends AppCompatActivity {
    ////////////////////////////////////VISTAS////////////////////////////
    TextView txtVersion;
    EditText edtpassword, edtuser;
    Button btnaccesar;
    ///////////////////////////////////UTILIDADES/////////////////////////
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtVersion=findViewById(R.id.txt_version_login);
        context = LoginActivity.this;
        String Version= obtenerversionApp(context);
        txtVersion.setText(Version);
        configuracionesiniciales();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnaccesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario=edtuser.getText().toString();// Se obtiene el usuario ingresado
                String password= edtpassword.getText().toString();
                if (usuario.isEmpty()){//compara si el user esta vacio
                    Toast.makeText(context,getString(R.string.faltausuario),Toast.LENGTH_LONG).show();
                }else if (password.isEmpty()){
                    Toast.makeText(context,getString(R.string.faltapassword),Toast.LENGTH_LONG).show();
                }else{// Entra aqui si user y contraseña no estan vacios
                    if (usuario.equals(usuarioTemporal)){
                        if (password.equals(passwordTemporal)){
                            Toast.makeText(context,getString(R.string.Accesocorrecto),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(context,MainActivity.class));
                            mydata mydata=new mydata(context);//guardar my usuario
                            mydata.guardarUsuario(usuario);
                            mydata.setLogeo(true);//guardar true
                        }else{
                            Toast.makeText(context,getString(R.string.Accesoincorrecto),Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(context,getString(R.string.Accesoincorrecto),Toast.LENGTH_LONG).show();
                    }
                }



            }
        });
    }

    private void configuracionesiniciales(){
        txtVersion= findViewById(R.id.txt_version_login);
        edtpassword=findViewById(R.id.edt_password);
        edtuser= findViewById(R.id.edt_user);
        btnaccesar=findViewById(R.id.btn_ingresar);
        context=LoginActivity.this;

    }
}