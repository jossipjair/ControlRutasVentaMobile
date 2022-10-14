package com.hunter.controlrutasyaku.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalBD extends SQLiteOpenHelper {

    private static final String baseDatosLocal = "BdControlPedidosYaku";
    private static final int versionBD = 4;

    public LocalBD(Context context) {
        super(context, baseDatosLocal, null, versionBD);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(T_ClienteProveedor.CREATE_TABLA);
        db.execSQL(T_DetallePedido.CREATE_TABLA);
        db.execSQL(T_Empleado.CREATE_TABLA);
        db.execSQL(T_Pedido.CREATE_TABLA);
        db.execSQL(T_Producto.CREATE_TABLA);
        db.execSQL(T_Configuracion.CREATE_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(T_ClienteProveedor.DROP_TABLA);
        db.execSQL(T_DetallePedido.DROP_TABLA);
        db.execSQL(T_Empleado.DROP_TABLA);
        db.execSQL(T_Pedido.DROP_TABLA);
        db.execSQL(T_Producto.DROP_TABLA);
        db.execSQL(T_Configuracion.DROP_TABLA);
        onCreate(db);
    }
}
