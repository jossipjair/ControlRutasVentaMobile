package com.hunter.controlrutasyaku.Modelo;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_DetallePedido;
import com.hunter.controlrutasyaku.Controlador.VariableGeneral;

import java.util.HashMap;
import java.util.Map;

public class SincronizaDetallePedido {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    private String pedDet_Id;
    private String ped_Id;
    private String pro_Id;
    private String pedDet_Cantidad;
    private String pedDet_PrecioUni;
    private String pedDet_Importe;

    ProgressDialog progressDialog;

    public void recorreListaSincronizaPedidoDetalle(final Context context) {
        Cursor registros = null;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            progressDialog = ProgressDialog.show(context,
                    "Enviado Datos Al Servidor",
                    "Espere un momento por favor.");
            registros = sqLiteDatabase.rawQuery(T_DetallePedido.SINCRONIZA_SERVIDOR(), null);
            if (registros.moveToFirst()) {
                do {
                    pedDet_Id = registros.getString(0);
                    ped_Id = registros.getString(1);
                    pro_Id = registros.getString(2);
                    pedDet_Cantidad = registros.getString(3);
                    pedDet_PrecioUni = registros.getString(4);
                    pedDet_Importe = registros.getString(5);

                    sincronizaPedidoDetalleToServer(context, pedDet_Id, ped_Id, pro_Id, pedDet_Cantidad, pedDet_PrecioUni, pedDet_Importe);

                } while (registros.moveToNext());
            }
            progressDialog.cancel();
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
                progressDialog.cancel();
            }
        }
    }


    private void sincronizaPedidoDetalleToServer(final Context context, final String detalleId, final String idPedido, final String producto,
                                          final String cantidad, final String precio, final String importe) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, VariableGeneral.SERVIDOR + "PedidoDetalle/", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.equals("true")) {
                    M_DetallePedido m_detallePedido = new M_DetallePedido();
                    m_detallePedido.actualizaPedidoDetalle(context, Integer.parseInt(detalleId));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ped_Id", idPedido);
                params.put("pro_Id", producto);
                params.put("pedDet_Cantidad", cantidad);
                params.put("pedDet_PrecioUni", precio);
                params.put("pedDet_Importe", importe);

                return params;
            }
        };
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }


}
