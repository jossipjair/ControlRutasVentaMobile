package com.hunter.controlrutasyaku.Controlador;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.hunter.controlrutasyaku.Entidad.E_ClienteProveedor;
import com.hunter.controlrutasyaku.Modelo.M_ClienteProveedor;
import com.hunter.controlrutasyaku.R;

public class BusquedaClienteDialog extends DialogFragment {

    private ListView listaClientes;
    private ArrayAdapter<String> adapter;
    private EditText txtBusquedaCliente;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selecciona_cliente_ruta, container, false);
        listaClientes = view.findViewById(R.id.listaCliente);
        txtBusquedaCliente = view.findViewById(R.id.txtBusquedaCliente);
        llenarListView();

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(VariableGeneral.TAG_ACTIVITY.equals("VisualizaUbicacion")){
                    M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
                    E_ClienteProveedor e_clienteProveedor = new E_ClienteProveedor();

                    e_clienteProveedor = m_clienteProveedor.busquedaCliente(BusquedaClienteDialog.this.getActivity().getApplication(), String.valueOf(adapter.getItem(position)));
                    ((VisualizaRuta)getActivity()).txtNombreCliente.setText(e_clienteProveedor.getCliPro_NombreCom());
                    ((VisualizaRuta)getActivity()).txtDireccionCliente.setText(e_clienteProveedor.getCliPro_Direccion());
                    ((VisualizaRuta)getActivity()).txtTelefonoCliente.setText(e_clienteProveedor.getCliPro_Telefono());
                    ((VisualizaRuta)getActivity()).txtGoogleMaps.setText("http://maps.google.com/maps?f=q&q=" + e_clienteProveedor.getCliPro_LatitudDir() + "," + e_clienteProveedor.getCliPro_LongitudDir()+ "&z=16");

                }else if(VariableGeneral.TAG_ACTIVITY.equals("RegistraPedido")){
                    M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
                    E_ClienteProveedor e_clienteProveedor = new E_ClienteProveedor();

                    e_clienteProveedor = m_clienteProveedor.busquedaCliente(BusquedaClienteDialog.this.getActivity().getApplication(), String.valueOf(adapter.getItem(position)));
                    ((RegistraPedido)getActivity()).idCliente = e_clienteProveedor.getCliPro_Id();
                    ((RegistraPedido)getActivity()).txtNombreClientePedido.setText(e_clienteProveedor.getCliPro_NombreCom());
                    ((RegistraPedido)getActivity()).txtDespacharA.setText(e_clienteProveedor.getCliPro_PersonaContacto());

                }
                getDialog().dismiss();
            }
        });

        txtBusquedaCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                llenarListView();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void llenarListView(){
        M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
        adapter = m_clienteProveedor.llenarListaClientesDialog(BusquedaClienteDialog.this.getActivity().getApplication(), listaClientes, txtBusquedaCliente.getText().toString());
    }

}
