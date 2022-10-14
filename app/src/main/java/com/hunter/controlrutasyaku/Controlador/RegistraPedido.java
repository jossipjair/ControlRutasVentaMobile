package com.hunter.controlrutasyaku.Controlador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Entidad.E_DetallePedido;
import com.hunter.controlrutasyaku.Modelo.M_DetallePedido;
import com.hunter.controlrutasyaku.Modelo.M_Pedido;
import com.hunter.controlrutasyaku.Modelo.M_Producto;
import com.hunter.controlrutasyaku.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RegistraPedido extends AppCompatActivity {

    private EditText txtIdPedido;
    private Button btnSeleccionaCliente;
    public String idCliente;
    public EditText txtNombreClientePedido;
    public EditText txtDespacharA;
    private EditText txtFechaEntregaPedido;
    private Spinner spFormaPago;
    private EditText txtObservacion;
    private Button btnAgregarDetallePedido;
    public TextView lblTotal;
    private Button btnRegistrarPedido;

    public double total;
    private String tipoPago;
    public String idPedido = "";
    private boolean guardarEstado = true;

    private int dia;
    private int mes;
    private int anio;

    M_Pedido m_pedido = new M_Pedido();
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_pedido);

        VariableGeneral.TAG_ACTIVITY = "RegistraPedido";

        txtIdPedido = findViewById(R.id.txtIdPedido);
        btnSeleccionaCliente = findViewById(R.id.btnSeleccionaCliente);
        txtNombreClientePedido = findViewById(R.id.txtNombreClientePedido);
        txtDespacharA = findViewById(R.id.txtDespacharA);
        txtFechaEntregaPedido = findViewById(R.id.txtFechaEntregaPedido);
        spFormaPago = findViewById(R.id.spFormaPago);
        txtObservacion = findViewById(R.id.txtObservacion);
        btnAgregarDetallePedido = findViewById(R.id.btnAgregarDetallePedido);
        lblTotal = findViewById(R.id.lblTotal);
        btnRegistrarPedido = findViewById(R.id.btnRegistrarPedido);
        preferences = getSharedPreferences("PREFERENCIA", MainActivity.MODE_PRIVATE);


        //IdPedido
        generaIdPedido();
        lblTotal.setText("0.00");
        //
        txtIdPedido.setInputType(InputType.TYPE_NULL);
        llenarSpinnerTipoPago();
        txtFechaEntregaPedido.setInputType(InputType.TYPE_NULL);

        txtFechaEntregaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistraPedido.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = "";
                        String fechaDate = year + "/" + (month + 1) + "/" + dayOfMonth;
                        Date date = new Date(fechaDate);
                        Format format = new SimpleDateFormat("dd/MM/yyyy");
                        fecha = format.format(date);
                        txtFechaEntregaPedido.setText(fecha);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });

        spFormaPago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoPago = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSeleccionaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusquedaClienteDialog dialog = new BusquedaClienteDialog();
                //Se debe manejar con 'import android.app.DialogFragment;' para el el layout de dialogo
                dialog.show(getFragmentManager(), "Dialogo");
            }
        });

        btnAgregarDetallePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Debe buscar pedido guardado

                if(validaElementos()){

                    RegistraDetallePedidoDialog dialog = new RegistraDetallePedidoDialog();
                    //Se debe manejar con 'import android.app.DialogFragment;' para el el layout de dialogo
                    dialog.show(getFragmentManager(), "REGISTRA_PEDIDO");

                    //Inserta Pedido
                    if(guardarEstado){
                        m_pedido.insertaPedido(RegistraPedido.this,txtIdPedido.getText().toString(), idCliente, txtDespacharA.getText().toString(), txtFechaEntregaPedido.getText().toString(), preferences.getString("DNI", ""), tipoPago,txtObservacion.getText().toString());
                        guardarEstado = false;
                    }
                }

            }
        });

        btnRegistrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(RegistraPedido.this);
                builder.setTitle("Mensaje de Confirmación");
                builder.setMessage("¿Desea guardar pedido?");
                builder.setCancelable(false);
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(validaElementos() && Integer.parseInt(lblTotal.getText().toString()) > 0){
                            m_pedido.guardaPedido(RegistraPedido.this, txtIdPedido.getText().toString(), lblTotal.getText().toString());
                            //Detalle pedido
                            M_DetallePedido m_detallePedido = new M_DetallePedido();
                            m_detallePedido.guardaPedidoDetalle(RegistraPedido.this, txtIdPedido.getText().toString());

                            Intent intent = new Intent(RegistraPedido.this, MenuPrincipal.class);
                            startActivity(intent);
                            RegistraPedido.this.finish();
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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistraPedido.this, MenuPrincipal.class);
        startActivity(intent);
        finish();
    }

    private static String fechaHora(String formato) { //"dd/MM/yyyy HH:mm:ss.fff"
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }

    void generaIdPedido() {
        txtIdPedido.setText("P" + fechaHora("yyyyMMddHHmmss"));
        idPedido = txtIdPedido.getText().toString();
    }

    void llenarSpinnerTipoPago() {
        M_Pedido m_pedido = new M_Pedido();
        m_pedido.llenaSpinnerTipoPago(RegistraPedido.this, spFormaPago);
    }

    boolean validaElementos(){

        boolean estado = true;

        if(txtFechaEntregaPedido.getText().toString().equals("")
        || tipoPago.equals("")
        || txtNombreClientePedido.getText().toString().equals("")){
            Toast.makeText(this, "¡Por favor completar formulario!", Toast.LENGTH_SHORT).show();
            estado = false;
        }

        return estado;

    }

    public  void calculaSubtotal(String valor){
        lblTotal.setText(valor);
    }

}
