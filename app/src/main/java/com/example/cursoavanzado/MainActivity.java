package com.example.cursoavanzado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
//////////////////////////////////Vistas///////////////////////////
    Toolbar mainToolbar;
    Menu menuActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracionesIniciales();
    }

    private void configuracionesIniciales() {
        mainToolbar=findViewById(R.id.toolbarMain);
        setSupportActionBar(mainToolbar);
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
                break;
            case R.id.AcercaDeAPP:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}