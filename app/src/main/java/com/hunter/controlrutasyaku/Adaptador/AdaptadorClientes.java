package com.hunter.controlrutasyaku.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hunter.controlrutasyaku.Entidad.E_ClienteProveedorAdaptador;
import com.hunter.controlrutasyaku.R;

import java.util.ArrayList;

public class AdaptadorClientes extends BaseAdapter {


    private Context context;
    private Integer layout;
    private ArrayList<E_ClienteProveedorAdaptador> claseClienteProveedor;

    public AdaptadorClientes(Context context, Integer layout, ArrayList<E_ClienteProveedorAdaptador> claseClienteProveedor) {
        this.context = context;
        this.layout = layout;
        this.claseClienteProveedor = claseClienteProveedor;
    }

    @Override
    public int getCount() {
        return claseClienteProveedor.size();
    }

    @Override
    public Object getItem(int i) {
        return this.claseClienteProveedor.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.item_clientes, null);

        TextView txtDni_Nombres = view.findViewById(R.id.txtDni_Nombres);
        TextView txtCodigInterno = view.findViewById(R.id.txtCodigoInterno);
        TextView txtDireccion = view.findViewById(R.id.txtDireccion);

        txtDni_Nombres.setText(this.claseClienteProveedor.get(i).getNombres());
        txtCodigInterno.setText(String.valueOf(this.claseClienteProveedor.get(i).getCodigoInterno()));
        txtDireccion.setText(String.valueOf(this.claseClienteProveedor.get(i).getDireccion()));


        view.setTag(claseClienteProveedor.get(i).getCodigoInterno());

        return view;
    }
}
