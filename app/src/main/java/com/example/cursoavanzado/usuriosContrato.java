package com.example.cursoavanzado;

import android.provider.BaseColumns;

public class usuriosContrato {
    public static class usuariosColumnas implements BaseColumns{
        public static final String NOMBRE= "NOMBRE";
        public static final String PASSWORD= "PASSWORD";
        public static final String GENERO= "GENERO";
        public static final String ROL= "ROL";
        public static final String CORREO= "CORREO";

        public static final String TABLE_NAME= "USUARIOS TABLE";
    }
}
