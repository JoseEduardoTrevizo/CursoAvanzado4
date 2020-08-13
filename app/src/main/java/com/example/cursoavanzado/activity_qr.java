package com.example.cursoavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static com.example.cursoavanzado.VariablesGlobales.codigoQR;

public class activity_qr extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView SCANNERVIEW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        SCANNERVIEW= findViewById(R.id.zxingScannerView);
        SCANNERVIEW.setResultHandler(activity_qr.this);
        SCANNERVIEW.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Este metodo se ejecuta cuando se realiza el scan del QR
        codigoQR=result.getText();
        SCANNERVIEW.stopCamera();
        startActivity(new Intent(activity_qr.this,MainActivity.class));
    }
}