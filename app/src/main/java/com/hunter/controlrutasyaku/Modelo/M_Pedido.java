package com.hunter.controlrutasyaku.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_Pedido;
import com.hunter.controlrutasyaku.Entidad.E_TipoPago;

import java.util.ArrayList;

public class M_Pedido {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    public void llenaSpinnerTipoPago(Context context, Spinner spTipoPago) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(".:: SELECCIONE FORMA DE PAGO ::.");
        lista.add("AL CONTADO");
        lista.add("CREDITO 30 DIAS");
        lista.add("CREDITO 15 DIAS");
        lista.add("CREDITO 7 DIAS");
        lista.add("PENDIENTE DE PAGO");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
        spTipoPago.setAdapter(arrayAdapter);
    }

    public void insertaPedido(Context context, String ped_Id, String cli_Pro, String ped_DespachadoA, String fechaEntregaPedido, String emp_Id, String ped_FormaPago, String ped_Observacion){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_Pedido.INSERT_PEDIDO(ped_Id, cli_Pro, ped_DespachadoA, fechaEntregaPedido, emp_Id, ped_FormaPago, ped_Observacion));
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void actualizaPedido(Context context, String ped_Id){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_Pedido.ACTUALIZA_PEDIDO(ped_Id));
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void guardaPedido(Context context, String ped_Id, String Total){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_Pedido.GUARDA_PEDIDO(ped_Id, Total));
            Toast.makeText(context, "Pedido Guardado", Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
    }

    public double mostrarTotales(Context context, String Empleado){
        double total = 0.0;
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros =sqLiteDatabase.rawQuery(T_Pedido.VENTA_TOTAL(Empleado), null );
            if(registros.moveToFirst()){
                total = registros.getDouble(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return total;
    }

    public double mostrarTotalesTipoPago(Context context, String Empleado, String TipoPago){
        double total = 0.0;
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros =sqLiteDatabase.rawQuery(T_Pedido.VENTA_TOTAL_FORMA_PAGO(Empleado, TipoPago), null );
            if(registros.moveToFirst()){
                total = registros.getDouble(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return total;
    }


}
