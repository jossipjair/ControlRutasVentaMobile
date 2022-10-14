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
import com.hunter.controlrutasyaku.BD.T_ClienteProveedor;
import com.hunter.controlrutasyaku.Controlador.VariableGeneral;

import java.util.HashMap;
import java.util.Map;

public class SincronizarClienteProveedor {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    private String cliPro_Id = "";
    private String cliPro_Ruc = "";
    private String cliPro_NombreCom = "";
    private String cliPro_PersonaContacto = "";
    private String cliPro_Direccion = "";
    private String cliPro_Telefono = "";
    private String cliPro_LatitudDir = "";
    private String cliPro_LongitudDir = "";
    private String emp_Id = "";
    ProgressDialog progressDialog;

    public void recorreListaClienteProveedor(final Context context) {
        Cursor registros = null;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            progressDialog = ProgressDialog.show(context,
                    "Enviado Datos Al Servidor",
                    "Espere un momento por favor.");
            registros = sqLiteDatabase.rawQuery(T_ClienteProveedor.SINCRONIZA_SERVIDOR(), null);
            if (registros.moveToFirst()) {
                do {
                    cliPro_Id = registros.getString(0);
                    cliPro_Ruc = registros.getString(1);
                    cliPro_NombreCom = registros.getString(2);
                    cliPro_PersonaContacto = registros.getString(3);
                    cliPro_Direccion = registros.getString(4);
                    cliPro_Telefono = registros.getString(5);
                    cliPro_LatitudDir = registros.getString(6);
                    cliPro_LongitudDir = registros.getString(7);
                    emp_Id = registros.getString(8);
                    sincronizaClientesToServer(context, cliPro_Id, cliPro_Ruc, cliPro_NombreCom, cliPro_PersonaContacto, cliPro_Direccion, cliPro_Telefono, cliPro_LatitudDir, cliPro_LongitudDir, emp_Id);

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



    private void sincronizaClientesToServer(final Context context, final String idCliente, final String ruc, final String nombreComercial,
                                           final String personaContacto, final String direccion, final String telefono,
                                           final String latitud, final String longitud, final String empleado) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, VariableGeneral.SERVIDOR + "ClienteProveedorIns/", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.equals("true")) {
                    M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
                    m_clienteProveedor.actualizaSincronizacion(context, idCliente);
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
                params.put("cliPro_Id", idCliente);
                params.put("cliPro_Ruc", ruc);
                params.put("cliPro_NombreCom", nombreComercial);
                params.put("cliPro_PersonaContacto", personaContacto);
                params.put("cliPro_Direccion", direccion);
                params.put("cliPro_Telefono", telefono);
                params.put("cliPro_LatitudDir", latitud);
                params.put("cliPro_LongitudDir", longitud);
                params.put("emp_Id", empleado);
                return params;
            }
        };
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }



}
