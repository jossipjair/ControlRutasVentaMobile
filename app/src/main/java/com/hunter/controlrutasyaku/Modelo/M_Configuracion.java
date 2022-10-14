package com.hunter.controlrutasyaku.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_Configuracion;

public class M_Configuracion {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    public void insertarConfiguracion(Context context, String servidor) {
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_Configuracion.DROP_TABLA);
            sqLiteDatabase.execSQL(T_Configuracion.CREATE_TABLA);
            sqLiteDatabase.execSQL(T_Configuracion.DELETE_TABLA);
            sqLiteDatabase.execSQL(T_Configuracion.INSERT_CONFIGURACION(servidor));
            Toast.makeText(context, "Configuración guardada", Toast.LENGTH_SHORT).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    public int traeConfiguracion(Context context){
        int conteo = 0;
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Configuracion.SELECT_CONFIGURACION(), null);
            if(registros.moveToFirst()){
                conteo = registros.getCount();
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return conteo;
    }

    public String traeConfiguracionServidor(Context context){
        String servidor = "-";
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Configuracion.SELECT_CONFIGURACION_SERVIDOR(), null);
            if(registros.moveToFirst()){
                servidor = registros.getString(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return servidor;
    }

}
