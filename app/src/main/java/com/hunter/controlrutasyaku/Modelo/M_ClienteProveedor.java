package com.hunter.controlrutasyaku.Modelo;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hunter.controlrutasyaku.Adaptador.AdaptadorClientes;
import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_ClienteProveedor;
import com.hunter.controlrutasyaku.Controlador.VariableGeneral;
import com.hunter.controlrutasyaku.Entidad.E_ClienteProveedor;
import com.hunter.controlrutasyaku.Entidad.E_ClienteProveedorAdaptador;
import com.hunter.controlrutasyaku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class M_ClienteProveedor {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    private String cliPro_Id;
    private String cliPro_Ruc;
    private String cliPro_NombreCom;
    private String cliPro_PersonaContacto;
    private String cliPro_Direccion;
    private String cliPro_Telefono;
    private String cliPro_LatitudDir;
    private String cliPro_LongitudDir;
    private String emp_Id;
    ProgressDialog progressDialog;

    public void insertarClientesToLocal(final Context context) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();
        progressDialog = ProgressDialog.show(context,
                "Sincronizando Datos",
                "Espere un momento por favor.");
        //RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, VariableGeneral.SERVIDOR + "ClienteProveedor", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sqLiteDatabase.execSQL(T_ClienteProveedor.DROP_TABLA);
                sqLiteDatabase.execSQL(T_ClienteProveedor.CREATE_TABLA);
                int tamañoResponse = Integer.parseInt(String.valueOf(response.length())) - 1;

                if(Integer.parseInt(String.valueOf(response.length())) > 2){
                    sqLiteDatabase.execSQL(T_ClienteProveedor.DELETE_CLENTE());
                }

               for (int i = 0; i <= tamañoResponse; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        cliPro_Id = jsonObject.getString("cliPro_Id");
                        cliPro_Ruc = jsonObject.getString("cliPro_Ruc");
                        cliPro_NombreCom = jsonObject.getString("cliPro_NombreCom");
                        cliPro_PersonaContacto = jsonObject.getString("cliPro_PersonaContacto");
                        cliPro_Direccion = jsonObject.getString("cliPro_Direccion");
                        cliPro_Telefono = jsonObject.getString("cliPro_Telefono");
                        cliPro_LatitudDir = jsonObject.getString("cliPro_LatitudDir");
                        cliPro_LongitudDir = jsonObject.getString("cliPro_LongitudDir");
                        emp_Id = jsonObject.getString("emp_Id");
                        sqLiteDatabase.execSQL(T_ClienteProveedor.INSERT_CLIENTE(cliPro_Id,cliPro_Ruc, cliPro_NombreCom, cliPro_PersonaContacto, cliPro_Direccion, cliPro_Telefono, cliPro_LatitudDir, cliPro_LongitudDir, emp_Id, 1));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                sqLiteDatabase.close();
                Toast.makeText(context, "¡Datos Sincronizados!", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.cancel();
                Toast.makeText(context, "Error Clientes: conexión JSON fallida", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(jsonArrayRequest);

    }

    public void insertarCliente(Context context, String cliPro_Id, String cliPro_Ruc, String cliPro_NombreCom, String cliPro_PersonaContacto,
                                String cliPro_Direccion, String cliPro_Telefono, String cliPro_LatitudDir, String cliPro_LongitudDir, String emp_Id) {
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_ClienteProveedor.INSERT_CLIENTE(cliPro_Id, cliPro_Ruc, cliPro_NombreCom, cliPro_PersonaContacto, cliPro_Direccion, cliPro_Telefono, cliPro_LatitudDir, cliPro_LongitudDir, emp_Id, 0));
            Toast.makeText(context, "Cliente guardado", Toast.LENGTH_SHORT).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    public void actualizaSincronizacion(Context context, String cliPro_Id) {
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            sqLiteDatabase.execSQL(T_ClienteProveedor.ACTUALIZA_ESTADO_SINCRONIZACION(cliPro_Id));

        } finally {
            sqLiteDatabase.close();
        }
    }

    public ArrayAdapter<String> llenarListaClientesDialog(Context context, ListView listView, String descripcionCliente) {
        ArrayAdapter<String> arrayAdapter;
        Cursor registros = null;
        try{
            ArrayList<String> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_ClienteProveedor.SELECT_CLIENTE_BUSQUEDA(descripcionCliente), null);

            if (registros.moveToFirst()) {
                do {
                    lista.add(registros.getString(0));
                } while (registros.moveToNext());
            }
            arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            listView.setAdapter(arrayAdapter);
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }

        return  arrayAdapter;
    }


    public int cuentaPendientesEnvio(Context context) {
        int cantidad = 0;
        Cursor registros = null;
        try{

            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_ClienteProveedor.SELECT_CLIENTES_NO_ENVIADOS(), null);

            if (registros.moveToFirst()) {
                do {
                    cantidad = registros.getInt(0);
                } while (registros.moveToNext());
            }

        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }

        return  cantidad;
    }

    public E_ClienteProveedor busquedaCliente(Context context, String descripcionCliente){
        E_ClienteProveedor e_clienteProveedor = null;
        Cursor registros = null;

        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_ClienteProveedor.SELECT_CLIENTE_EXACTO(descripcionCliente), null);
            if(registros.moveToFirst()){
                do {
                    e_clienteProveedor = new E_ClienteProveedor();
                    e_clienteProveedor.setCliPro_Id(registros.getString(0));
                    e_clienteProveedor.setCliPro_Ruc(registros.getString(1));
                    e_clienteProveedor.setCliPro_NombreCom(registros.getString(2));
                    e_clienteProveedor.setCliPro_PersonaContacto(registros.getString(3));
                    e_clienteProveedor.setCliPro_Direccion(registros.getString(4));
                    e_clienteProveedor.setCliPro_Telefono(registros.getString(5));
                    e_clienteProveedor.setCliPro_LatitudDir(registros.getString(6));
                    e_clienteProveedor.setCliPro_LongitudDir(registros.getString(7));

                }while (registros.moveToNext());
            }
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return e_clienteProveedor;
    }

    public void mostrarClientes(Context context, GridView gridView){
        Cursor registros = null;

        try {
            ArrayList<String> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_ClienteProveedor.SELECT_CLIENTE_LISTA(), null);

            if(registros.moveToFirst()){
                do{
                    lista.add(registros.getString(0));
                    lista.add(registros.getString(1));
                    lista.add(registros.getString(2));
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


    public void mostrarAdaptadorClientes(Context context, ListView listView) {

        Cursor registros = null;
        try {
            ArrayList<E_ClienteProveedorAdaptador> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();

            registros = sqLiteDatabase.rawQuery(T_ClienteProveedor.SELECT_CLIENTE_ADAPTADOR(), null);

            if (registros.moveToFirst()) {
                do {

                    E_ClienteProveedorAdaptador e_clienteProveedorAdaptador = new E_ClienteProveedorAdaptador();
                    e_clienteProveedorAdaptador.setNombres(registros.getString(0));
                    e_clienteProveedorAdaptador.setCodigoInterno(registros.getString(1));
                    e_clienteProveedorAdaptador.setDireccion(registros.getString(2));

                    lista.add(e_clienteProveedorAdaptador);
                } while (registros.moveToNext());
            }
            AdaptadorClientes adaptadorClientes = new AdaptadorClientes(context, R.layout.item_clientes, lista);
            listView.setAdapter(adaptadorClientes);
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }

}
