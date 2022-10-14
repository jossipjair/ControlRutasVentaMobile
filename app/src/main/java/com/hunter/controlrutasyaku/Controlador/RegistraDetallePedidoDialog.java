package com.hunter.controlrutasyaku.Controlador;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Entidad.E_DetallePedido;
import com.hunter.controlrutasyaku.Entidad.E_DetallePedidoAdaptador;
import com.hunter.controlrutasyaku.Entidad.E_Producto;
import com.hunter.controlrutasyaku.Modelo.M_DetallePedido;
import com.hunter.controlrutasyaku.Modelo.M_Producto;
import com.hunter.controlrutasyaku.R;

import java.util.ArrayList;

public class RegistraDetallePedidoDialog extends DialogFragment {

    private EditText txtPrecioProducto;
    private EditText txtCantidad;
    private EditText txtImporteDetallePedido;
    private Button btnAgregarDetalleProducto;
    private ListView lvDetallePedido;
    private Spinner spProductoDetalle;
    private int codigoProducto;
    private double precio;
    private M_Producto m_producto = new M_Producto();

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registra_detalle_pedido, container, false);

        txtPrecioProducto = view.findViewById(R.id.txtPrecioProducto);
        txtCantidad = view.findViewById(R.id.txtCantidad);
        txtImporteDetallePedido = view.findViewById(R.id.txtImporteDetallePedido);
        btnAgregarDetalleProducto = view.findViewById(R.id.btnAgregarDetalleProducto);
        lvDetallePedido = view.findViewById(R.id.lvDetallePedido);
        spProductoDetalle = view.findViewById(R.id.spProductoDetalle);

        llenarProductoDetalle();
        llenarPedidoDetalle();

        btnAgregarDetalleProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Mensaje de Confirmación");
                builder.setMessage("¿Desea agregar producto al detalle?");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        try {
                            if (txtPrecioProducto.getText().equals("0.0")
                                    || txtCantidad.getText().toString().equals("")
                                    || Double.parseDouble(txtCantidad.getText().toString()) <= 0
                                    || Double.parseDouble(txtPrecioProducto.getText().toString()) <= 0) {
                                Toast.makeText(getActivity(), "¡Por favor complete formulario!", Toast.LENGTH_SHORT).show();

                            } else {
                                M_DetallePedido m_detallePedido = new M_DetallePedido();
                                m_detallePedido.insertaPedidoDetalle(getActivity(), ((RegistraPedido) getActivity()).idPedido, codigoProducto, Integer.parseInt(txtCantidad.getText().toString()), String.valueOf(precio), txtImporteDetallePedido.getText().toString());
                                llenarPedidoDetalle();
                                muestraTotalPedido();
                                txtPrecioProducto.setText("");
                                txtCantidad.setText("");
                                txtImporteDetallePedido.setText("");
                                spProductoDetalle.setSelection(0);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Error al registrar detalle de pedido", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        txtPrecioProducto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && i == (keyEvent.KEYCODE_ENTER)) {
                    try {
                        txtImporteDetallePedido.setText(String.valueOf(calculaImporte(Integer.parseInt(txtCantidad.getText().toString()), precio)));
                    } catch (Exception e) {
                        Toast.makeText(RegistraDetallePedidoDialog.this.getActivity().getApplication(), "Error de Calculo", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        txtPrecioProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    txtImporteDetallePedido.setText(String.valueOf(calculaImporte(Integer.parseInt(txtCantidad.getText().toString()), precio)));
                } catch (Exception e) {
                    //Toast.makeText(RegistraDetallePedidoDialog.this.getActivity().getApplication(), "Error de Calculo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtCantidad.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && i == (keyEvent.KEYCODE_ENTER)) {
                    try {
                        txtImporteDetallePedido.setText(String.valueOf(calculaImporte(Integer.parseInt(txtCantidad.getText().toString()), precio)));
                    } catch (Exception e) {
                        Toast.makeText(RegistraDetallePedidoDialog.this.getActivity().getApplication(), "Error de Calculo", Toast.LENGTH_SHORT).show();
                    }
                }

                return false;
            }
        });

        txtCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    txtImporteDetallePedido.setText(String.valueOf(calculaImporte(Integer.parseInt(txtCantidad.getText().toString()), precio)));
                } catch (Exception e) {
                    //Toast.makeText(RegistraDetallePedidoDialog.this.getActivity().getApplication(), "Error de Calculo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        spProductoDetalle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txtPrecioProducto.setText("");
                txtCantidad.setText("");
                txtImporteDetallePedido.setText("");

                E_Producto producto = (E_Producto) parent.getSelectedItem();
                codigoProducto = producto.getPro_Id();
                precio = m_producto.buscaPrecio(RegistraDetallePedidoDialog.this.getActivity().getApplication(), codigoProducto);
                txtPrecioProducto.setText(String.valueOf(precio));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvDetallePedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Llamar la entidad del adaptador
                final E_DetallePedidoAdaptador e_detallePedidoAdaptador = (E_DetallePedidoAdaptador) adapterView.getItemAtPosition(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Eliminar");
                builder.setMessage("¿Desea eliminar el producto del pedido?");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Elimina
                        M_DetallePedido m_detallePedido = new M_DetallePedido();
                        m_detallePedido.eliminaPedidoDetalle(RegistraDetallePedidoDialog.this.getActivity().getApplication(), e_detallePedidoAdaptador.getIdDetalle());
                        llenarPedidoDetalle();
                        muestraTotalPedido();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        return view;
    }


    void llenarProductoDetalle() {
        //  M_Producto m_producto = new M_Producto();
        m_producto.llenarSpinner(RegistraDetallePedidoDialog.this.getActivity().getApplication(), spProductoDetalle);
    }

    void llenarPedidoDetalle() {
        M_DetallePedido m_detallePedido = new M_DetallePedido();
        //   m_detallePedido.mostrarDetallePedido(getActivity(), gvDetalleProductos,((RegistraPedido)getActivity()).idPedido);

        m_detallePedido.mostrarAdaptadorDetallePedido(getActivity(), lvDetallePedido, ((RegistraPedido) getActivity()).idPedido);

    }

    void muestraTotalPedido(){
        //Calcula total
        M_DetallePedido m_detallePedido = new M_DetallePedido();
        ((RegistraPedido) getActivity()).calculaSubtotal(m_detallePedido.mostrarSubTotalPedido(getActivity(), ((RegistraPedido) getActivity()).idPedido));

    }


    double calculaImporte(int cantidad, double precio) {
        return cantidad * precio;
    }


}
