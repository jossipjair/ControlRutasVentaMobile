package com.hunter.controlrutasyaku.Controlador;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.hunter.controlrutasyaku.Modelo.M_ClienteProveedor;
import com.hunter.controlrutasyaku.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class RegistraCliente extends AppCompatActivity {

    private EditText txtRucCliente;
    private EditText txtNombreCliente;
    private EditText txtPersonaContacto;
    private EditText txtTelefonoContacto;
    private EditText txtDireccionCliente;
    private EditText txtLatitud;
    private EditText txtLongitud;
    private Button btnMuestraUbicacion;
    private Button btnGuardarCliente;
    SharedPreferences preferences;

    //Coordenadas
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_cliente);

        txtRucCliente = findViewById(R.id.txtRucCliente);
        txtNombreCliente = findViewById(R.id.txtNombreCliente);
        txtPersonaContacto = findViewById(R.id.txtPersonaContacto);
        txtTelefonoContacto = findViewById(R.id.txtTelefonoContacto);
        txtDireccionCliente = findViewById(R.id.txtDireccionCliente);
        btnMuestraUbicacion = findViewById(R.id.btnMuestraUbicacion);
        btnGuardarCliente = findViewById(R.id.btnGuardarCliente);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        preferences = getSharedPreferences("PREFERENCIA", MainActivity.MODE_PRIVATE);

        //Solicitar permiso en tiempo de ejecucion
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        //

        capturarCoordenadas();

        btnMuestraUbicacion.performClick();

        btnMuestraUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           /*     int permissionCheck = ContextCompat.checkSelfPermission(RegistraCliente.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(RegistraCliente.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                    } else {
                        ActivityCompat.requestPermissions(RegistraCliente.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                }

                fusedLocationProviderClient = new FusedLocationProviderClient(RegistraCliente.this);
                locationRequest = new LocationRequest();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setFastestInterval(2000);
                locationRequest.setInterval(4000);

                fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        txtLatitud.setText(String.valueOf(locationResult.getLastLocation().getLatitude()));
                        txtLongitud.setText(String.valueOf(locationResult.getLastLocation().getLongitude()));
                    }

                    @Override
                    protected void finalize() throws Throwable {
                        super.finalize();
                        onDestroy();
                    }

                }, getMainLooper());
*/

                capturarCoordenadas();
            }
        });

        btnGuardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtNombreCliente.getText().toString().equals("") || txtPersonaContacto.getText().toString().equals("") || txtDireccionCliente.getText().toString().equals("") || txtLatitud.getText().toString().equals("") || txtLongitud.getText().toString().equals("")){
                    Toast.makeText(RegistraCliente.this, "Faltan datos\n¡Verifique!", Toast.LENGTH_SHORT).show();
                }else{
                    M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
                    String idCliente = "C" + fechaHora("yyyyMMddHHmmss");
                    m_clienteProveedor.insertarCliente(RegistraCliente.this, idCliente,txtRucCliente.getText().toString(), txtNombreCliente.getText().toString(), txtPersonaContacto.getText().toString(), txtDireccionCliente.getText().toString(),txtTelefonoContacto.getText().toString(), txtLatitud.getText().toString(), txtLongitud.getText().toString(), preferences.getString("DNI", ""));
                    limpiarFormulario();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(RegistraCliente.this, ListaClientes.class);
        startActivity(intent);
        RegistraCliente.this.finish();
    }

    private static String fechaHora(String formato){ //"dd/MM/yyyy HH:mm:ss"
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }

    private void limpiarFormulario(){
        txtRucCliente.setText("");
        txtNombreCliente.setText("");
        txtPersonaContacto.setText("");
        txtTelefonoContacto.setText("");
        txtDireccionCliente.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
    }

    void capturarCoordenadas(){
        int permissionCheck = ContextCompat.checkSelfPermission(RegistraCliente.this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegistraCliente.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(RegistraCliente.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        fusedLocationProviderClient = new FusedLocationProviderClient(RegistraCliente.this);
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(2000);
        locationRequest.setInterval(4000);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                txtLatitud.setText(String.valueOf(locationResult.getLastLocation().getLatitude()));
                txtLongitud.setText(String.valueOf(locationResult.getLastLocation().getLongitude()));
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                onDestroy();
            }

        }, getMainLooper());
    }

   /* void capturaCoordenadas() {
        LocationManager locationManager = (LocationManager) RegistraCliente.this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(RegistraCliente.this, "Latitud: " +location.getLatitude() , Toast.LENGTH_SHORT).show();
                Toast.makeText(RegistraCliente.this, "Longitud: " + location.getLongitude() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //Solicitar permiso en tiempo de ejecucion
        int permissionCheck = ContextCompat.checkSelfPermission(RegistraCliente.this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }*/

}
