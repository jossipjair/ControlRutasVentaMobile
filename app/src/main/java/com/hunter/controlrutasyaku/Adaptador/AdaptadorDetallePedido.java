package com.hunter.controlrutasyaku.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hunter.controlrutasyaku.Entidad.E_DetallePedidoAdaptador;
import com.hunter.controlrutasyaku.R;

import java.util.ArrayList;

public class AdaptadorDetallePedido extends BaseAdapter {

    private Context context;
    private Integer layout;
    private ArrayList<E_DetallePedidoAdaptador> clasePedidoAdaptador;

    public AdaptadorDetallePedido(Context context, Integer layout, ArrayList<E_DetallePedidoAdaptador> clasePedidoAdaptador) {
        this.context = context;
        this.layout = layout;
        this.clasePedidoAdaptador = clasePedidoAdaptador;
    }

    @Override
    public int getCount() {
        return clasePedidoAdaptador.size();
    }

    @Override
    public Object getItem(int i) {
        return this.clasePedidoAdaptador.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.item_detalle_pedido, null);

        TextView txtProductoImporte = view.findViewById(R.id.txtProductoImporte);
        TextView txtPrecioUnitario = view.findViewById(R.id.txtPrecioUnitario);
        TextView txtCantidadProducto = view.findViewById(R.id.txtCantidadProducto);
        TextView txtIdDetalle = view.findViewById(R.id.txtIdDetalle);

        txtProductoImporte.setText(this.clasePedidoAdaptador.get(i).getNombreProducto() + " - S/ " + this.clasePedidoAdaptador.get(i).getImporteDetalle());
        txtPrecioUnitario.setText(String.valueOf(this.clasePedidoAdaptador.get(i).getPrecioProducto()));
        txtCantidadProducto.setText(String.valueOf(this.clasePedidoAdaptador.get(i).getCantidadProducto()));
        txtIdDetalle.setText(String.valueOf(this.clasePedidoAdaptador.get(i).getIdDetalle()));


        view.setTag(clasePedidoAdaptador.get(i).getIdDetalle());


        return view;
    }
}
