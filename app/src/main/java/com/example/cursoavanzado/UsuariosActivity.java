package com.example.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import static com.example.cursoavanzado.VariablesGlobales.usuariosApp;

public class UsuariosActivity extends AppCompatActivity {

    ListView listUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        listUsuarios=findViewById(R.id.listUsuarios);
        listAdapter adapatador =new listAdapter(UsuariosActivity.this,R.layout.usario_list_item
                ,0,usuariosApp);
        listUsuarios.setAdapter(adapatador);
    }
}