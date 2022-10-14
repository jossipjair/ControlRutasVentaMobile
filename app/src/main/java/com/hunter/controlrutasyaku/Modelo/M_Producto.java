package com.hunter.controlrutasyaku.Modelo;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_Producto;
import com.hunter.controlrutasyaku.Controlador.VariableGeneral;
import com.hunter.controlrutasyaku.Entidad.E_Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class M_Producto {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    private int pro_Id;
    private String uniMed_Id;
    private String pro_Codigo;
    private String pro_Descripcion;
    private String pro_Nombre;
    private String pro_Tipo;
    private String pro_Estado;
    private String pro_Precio;
    ProgressDialog progressDialog;

    public void insertarProductoToLocal(final Context context) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();
        progressDialog = ProgressDialog.show(context,
                "Sincronizando Datos",
                "Espere un momento por favor.");
        //RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, VariableGeneral.SERVIDOR + "Producto", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sqLiteDatabase.execSQL(T_Producto.DROP_TABLA);
                sqLiteDatabase.execSQL(T_Producto.CREATE_TABLA);
                int tamañoResponse = Integer.parseInt(String.valueOf(response.length())) - 1;
                for (int i = 0; i <= tamañoResponse; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        pro_Id = jsonObject.getInt("pro_Id");
                        uniMed_Id = jsonObject.getString("uniMed_Id");
                        pro_Codigo = jsonObject.getString("pro_Codigo");
                        pro_Descripcion = jsonObject.getString("pro_Descripcion");
                        pro_Nombre = jsonObject.getString("pro_Nombre");
                        pro_Tipo = jsonObject.getString("pro_Tipo");
                        pro_Estado = jsonObject.getString("pro_Estado");
                        pro_Precio = jsonObject.getString("pro_Precio");

                        sqLiteDatabase.execSQL(T_Producto.INSERT_PRODUCTO(pro_Id, uniMed_Id,pro_Codigo, pro_Descripcion, pro_Nombre, pro_Tipo, pro_Estado, pro_Precio));

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
                Toast.makeText(context, "Error Productos: conexión JSON fallida " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Error conexion producto",  error.toString());

            }
        });
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(jsonArrayRequest);
    }

    public void llenarSpinner(Context context, Spinner spProductoDetalle){
        Cursor registros = null;
        try{
            ArrayList<E_Producto> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Producto.SELECT_PRODUCTO(), null);
            //Registro vacio
            E_Producto e_producto = new E_Producto();
            e_producto.setPro_Id(0);
            e_producto.setPro_Nombre(".:: SELECCIONE PRODUCTO ::.");
            e_producto.setPro_Precio("0.00");
            lista.add(e_producto);
            //Fin registro
            if(registros.moveToFirst()){
                do{
                    e_producto = new E_Producto(registros.getInt(0), registros.getString(1), registros.getString(2));
                    lista.add(e_producto);
                }while (registros.moveToNext());
            }
            ArrayAdapter<E_Producto> arrayAdapter;
            arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spProductoDetalle.setAdapter(arrayAdapter);
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }

    public Double buscaPrecio(Context context, int pro_Id){
        double precio = 0;
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros =sqLiteDatabase.rawQuery(T_Producto.SELECT_PRODUCTO_PRECIO(pro_Id), null );
            if(registros.moveToFirst()){
                precio = registros.getInt(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return precio;
    }

    public String buscaProducto(Context context, int pro_Id){
        String producto = "";
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros =sqLiteDatabase.rawQuery(T_Producto.SELECT_PRODUCTO(pro_Id), null );
            if(registros.moveToFirst()){
                producto = registros.getString(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return producto;
    }



}
