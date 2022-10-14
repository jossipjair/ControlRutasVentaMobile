package com.hunter.controlrutasyaku.Controlador;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Entidad.E_Empleado;
import com.hunter.controlrutasyaku.Modelo.M_Empleado;
import com.hunter.controlrutasyaku.R;

public class VisualizaRuta extends AppCompatActivity {

    private Button btnSeleccionaCliente;
    private Button btnEnviarRutaSms;
    public EditText txtNombreCliente;
    public EditText txtDireccionCliente;
    public EditText txtTelefonoCliente;
    public TextView txtGoogleMaps;
    private Spinner spContactoEmpleado;
    private String telefonoEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_ruta);

        btnSeleccionaCliente = findViewById(R.id.btnSeleccionaCliente);
        btnEnviarRutaSms = findViewById(R.id.btnEnviarRutaSms);
        txtNombreCliente = findViewById(R.id.txtNombreCliente);
        txtDireccionCliente = findViewById(R.id.txtDireccionCliente);
        txtGoogleMaps = findViewById(R.id.txtGoogleMaps);
        txtTelefonoCliente = findViewById(R.id.txtTelefonoCliente);
        spContactoEmpleado = findViewById(R.id.spContactoEmpleado);

        //Data de prueba
       // txtGoogleMaps.setText("http://maps.google.com/maps?f=q&q=-14.0000152,-75.7006209&z=16");
        //txtNombreCliente.setText("ROSMELIA LUZ JUAREZ CHACALLA");
        //txtDireccionCliente.setText("AV. LA MAQUINA 1163");
        //Fin Data Prueba

        VariableGeneral.TAG_ACTIVITY = "VisualizaUbicacion";

        btnSeleccionaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusquedaClienteDialog dialog = new BusquedaClienteDialog();
                //Se debe manejar con 'import android.app.DialogFragment;' para el el layout de dialogo
                dialog.show(getFragmentManager(), "Dialogo");

            }
        });

        //Solicitar permiso en tiempo de ejecucion
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }

        //

        llenarEmpleados();

        spContactoEmpleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                E_Empleado e_empleado = (E_Empleado) parent.getSelectedItem();
                telefonoEmpleado = e_empleado.getEmp_Telefono();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEnviarRutaSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccionMapa = txtNombreCliente.getText().toString()
                        + "\n" + txtDireccionCliente.getText().toString()
                        + "\n" + "Tel. "  + txtTelefonoCliente.getText().toString()
                        + "\n" + txtGoogleMaps.getText().toString();
            /*    int cantidadTexto = txtNombreCliente.length() + txtDireccionCliente.length() + txtGoogleMaps.length();
               // Toast.makeText(VisualizaRuta.this, "Total: " + String.valueOf(cantidadTexto), Toast.LENGTH_SHORT).show();
                if (direccionMapa.length() > 0) {
                    enviarSMS(telefonoEmpleado, direccionMapa);
                } else {
                    Toast.makeText(VisualizaRuta.this, "Selecciona cliente", Toast.LENGTH_SHORT).show();
                }*/

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, direccionMapa);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(VisualizaRuta.this, MenuPrincipal.class);
        startActivity(intent);
        VisualizaRuta.this.finish();
    }

    void llenarEmpleados() {
        M_Empleado m_empleado = new M_Empleado();
        m_empleado.llenarEmpleadoContacto(VisualizaRuta.this, spContactoEmpleado);
    }

    private void enviarSMS(final String numeroTelefono, final String mensaje) {

        AlertDialog.Builder builder = new AlertDialog.Builder(VisualizaRuta.this);
        builder.setTitle("Mensaje de Confirmación");
        builder.setMessage("¿Desea enviar datos a " + spContactoEmpleado.getSelectedItem().toString() + "?");
        builder.setCancelable(false);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PendingIntent pendingIntent = PendingIntent.getActivity(VisualizaRuta.this, 0, new Intent(VisualizaRuta.this, VisualizaRuta.class), 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(numeroTelefono, null, mensaje, pendingIntent, null);
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


}
