package com.hunter.controlrutasyaku.Modelo;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_Pedido;
import com.hunter.controlrutasyaku.Controlador.VariableGeneral;
import java.util.HashMap;
import java.util.Map;

public class SincronizaPedido {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;
    private String ped_Id;
    private String cliPro_Id;
    private String ped_DespachadoA;
    private String ped_FechaPed;
    private String ped_FechaHoraPed;
    private String ped_FechaHoraLog;
    private String ped_FechaEntregaPed;
    private String emp_Id;
    private String ped_FormaPag;
    private String ped_Observacion;
    private String ped_Total;
    ProgressDialog progressDialog;


    public void recorreListaSincronizaPedido(final Context context) {
        Cursor registros = null;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            progressDialog = ProgressDialog.show(context,
                    "Enviado Datos Al Servidor",
                    "Espere un momento por favor.");
            registros = sqLiteDatabase.rawQuery(T_Pedido.SINCRONIZA_SERVIDOR(), null);
            if (registros.moveToFirst()) {
                do {
                    ped_Id = registros.getString(0);
                    cliPro_Id = registros.getString(1);
                    ped_DespachadoA = registros.getString(2);
                    ped_FechaPed = registros.getString(3);
                    ped_FechaHoraPed = registros.getString(4);
                    ped_FechaHoraLog = registros.getString(5);
                    ped_FechaEntregaPed = registros.getString(6);
                    emp_Id = registros.getString(7);
                    ped_FormaPag = registros.getString(8);
                    ped_Observacion = registros.getString(9);
                    ped_Total = registros.getString(12);

                    sincronizaPedidoToServer(context, ped_Id, cliPro_Id, ped_DespachadoA, ped_FechaPed, ped_FechaHoraPed, ped_FechaHoraLog, ped_FechaEntregaPed, emp_Id, ped_FormaPag, ped_Observacion, ped_Total);

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

    private void sincronizaPedidoToServer(final Context context, final String idPedido, final String idCliente, final String despachado,
                                            final String fechaPedido, final String fechaHora, final String horalog, final String fechaEntrega,
                                            final String empleado, final String formaPago, final String observacion, final String total) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, VariableGeneral.SERVIDOR + "Pedido/", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                int cantSincro = 0;

                if (response.equals("true")) {
                    M_Pedido m_pedido = new M_Pedido();
                    m_pedido.actualizaPedido(context, idPedido);
                    cantSincro = 1;
                }

                if(cantSincro == 1){
                    Toast.makeText(context, "¡Registros Sincronizados!", Toast.LENGTH_SHORT).show();
                    cantSincro = 2;
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
                params.put("cliPro_Id", idCliente);
                params.put("ped_DespachadoA", despachado);
                params.put("ped_FechaPed", fechaPedido);
                params.put("ped_FechaHoraPed", fechaHora);
                params.put("ped_FechaHoraLog", horalog);
                params.put("ped_FechaEntregaPed", fechaEntrega);
                params.put("emp_Id", empleado);
                params.put("ped_FormaPag", formaPago);
                params.put("ped_Observacion", observacion);
                params.put("ped_Total", total);
                return params;
            }
        };
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }


}
