package com.hunter.controlrutasyaku.Modelo;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hunter.controlrutasyaku.BD.LocalBD;
import com.hunter.controlrutasyaku.BD.T_Empleado;
import com.hunter.controlrutasyaku.Controlador.VariableGeneral;
import com.hunter.controlrutasyaku.Entidad.E_Empleado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class M_Empleado {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    private String emp_Id;
    private String emp_Dni;
    private String emp_ApellidosNomb;
    private String emp_Telefono;

    ProgressDialog progressDialog;

    public void insertarEmpleadoToLocal(final Context context) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();
        progressDialog = ProgressDialog.show(context,
                "Sincronizando Datos",
                "Espere un momento por favor.");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, VariableGeneral.SERVIDOR + "Empleado", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sqLiteDatabase.execSQL(T_Empleado.DROP_TABLA);
                sqLiteDatabase.execSQL(T_Empleado.CREATE_TABLA);
                int tamañoResponse = Integer.parseInt(String.valueOf(response.length())) - 1;
                for (int i = 0; i <= tamañoResponse; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        emp_Id = jsonObject.getString("emp_Id");
                        emp_Dni = jsonObject.getString("emp_Dni");
                        emp_ApellidosNomb = jsonObject.getString("emp_ApellidoNomb");
                        emp_Telefono = jsonObject.getString("emp_Telefono");

                        sqLiteDatabase.execSQL(T_Empleado.INSERT_EMPLEADO(emp_Id,emp_Dni, emp_ApellidosNomb, emp_Telefono));

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
                Toast.makeText(context, "Error Empleados: conexión JSON fallida", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyInstance.getInstanceVolley(context).addToRequestQueue(jsonArrayRequest);
    }

    public String validaUsuario(Context context, String dni){
        String data = "";
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Empleado.SELECT_EMPLEADO_LOGIN(dni), null);
            if(registros.moveToFirst()){
                data = registros.getString(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return data;
    }

    public String buscaEmpleadoNombre(Context context, String dni){
        String data = "";
        Cursor registros = null;
        try{
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Empleado.SELECT_EMPLEADO_NOMBRES(dni), null);
            if(registros.moveToFirst()){
                data = registros.getString(0);
            }while (registros.moveToNext());
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return data;
    }

    public void llenarEmpleadoContacto(Context context, Spinner spEmpleado){
        Cursor registros = null;

        try{
            ArrayList<E_Empleado> lista = new ArrayList<>();
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_Empleado.SELECT_EMPLEADO_NOMBRES_TELEFONOS(), null);
            if(registros.moveToFirst()){
                do{
                    E_Empleado e_empleado = new E_Empleado(registros.getString(0), registros.getString(1));
                    lista.add(e_empleado);
                }while (registros.moveToNext());
            }
            ArrayAdapter<E_Empleado> arrayAdapter;
            arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spEmpleado.setAdapter(arrayAdapter);
        }finally {
            if(registros != null){
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }




}
