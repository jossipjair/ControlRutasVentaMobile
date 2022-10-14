package com.hunter.controlrutasyaku.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Adaptador.AdaptadorDetallePedido;
import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_ClienteProveedor;
import com.hunter.controlrutasyaku.BD.T_DetallePedido;
import com.hunter.controlrutasyaku.Entidad.E_DetallePedidoAdaptador;
import com.hunter.controlrutasyaku.R;

import java.util.ArrayList;

public class M_DetallePedido {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    public void insertaPedidoDetalle(Context context, String ped_Id, int pro_Id, int pedDet_Cantidad, String pedDet_PrecioUni, String pedDet_Importe){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_DetallePedido.INSERT_DETALLE_PEDIDO(ped_Id, pro_Id, pedDet_Cantidad, pedDet_PrecioUni, pedDet_Importe));
            Toast.makeText(context, "Detalle guardado correctamente", Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void guardaPedidoDetalle(Context context, String ped_Id){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_DetallePedido.GUARDA_DETALLE_PEDIDO(ped_Id));
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void eliminaPedidoDetalle(Context context, int PedDet_Id){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_DetallePedido.DELETE_DETALLE_PEDIDO(PedDet_Id));
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void actualizaPedidoDetalle(Context context, int PedDet_Id){
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_DetallePedido.ACTUALIZA_DETALLE_PEDIDO(PedDet_Id));
        }finally {
            sqLiteDatabase.close();
        }
    }


    public void mostrarDetallePedido(Context context, GridView gridView, String Ped_Id){
        Cursor registros = null;
        M_Producto m_producto = new M_Producto();

        try {
            ArrayList<String> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_DetallePedido.SELECT_DETALLE_PEDIDO(Ped_Id), null);

            if(registros.moveToFirst()){
                do{
                    lista.add(m_producto.buscaProducto(context, registros.getInt(0)));
                    lista.add(registros.getString(1));
                    lista.add(registros.getString(2));
                    lista.add(registros.getString(3));
                }while (registros.moveToNext());
            }
            ArrayAdapter adapter;
            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, lista);
            gridView.setAdapter(adapter);

        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }


    public void mostrarAdaptadorDetallePedido(Context context, ListView listView, String pedId) {

        Cursor registros = null;
        M_Producto m_producto = new M_Producto();

        try {
            ArrayList<E_DetallePedidoAdaptador> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();

            registros = sqLiteDatabase.rawQuery(T_DetallePedido.SELECT_DETALLE_PEDIDO(pedId), null);

            if (registros.moveToFirst()) {
                do {

                    E_DetallePedidoAdaptador e_detallePedidoAdaptador = new E_DetallePedidoAdaptador();
                    e_detallePedidoAdaptador.setNombreProducto(m_producto.buscaProducto(context, registros.getInt(0))  );
                    e_detallePedidoAdaptador.setImporteDetalle( Double.parseDouble(registros.getString(1)));
                    e_detallePedidoAdaptador.setPrecioProducto(Double.parseDouble(registros.getString(2)) );
                    e_detallePedidoAdaptador.setCantidadProducto(registros.getInt(3));
                    e_detallePedidoAdaptador.setIdDetalle(registros.getInt(4));
                    lista.add(e_detallePedidoAdaptador);
                } while (registros.moveToNext());
            }
            AdaptadorDetallePedido adaptadorDetallePedido = new AdaptadorDetallePedido(context, R.layout.item_detalle_pedido, lista);
            listView.setAdapter(adaptadorDetallePedido);
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }


    public String mostrarSubTotalPedido(Context context, String Ped_Id){
        Cursor registros = null;
        String total = "-";
        try {
            ArrayList<String> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_DetallePedido.SELECT_TOTAL_DETALLE(Ped_Id), null);

            if(registros.moveToFirst()){
                do{
                    total = (registros.getString(0));

                }while (registros.moveToNext());
            }


        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return total;
    }




}
