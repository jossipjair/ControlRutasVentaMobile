package com.hunter.controlrutasyaku.Controlador;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Modelo.M_ClienteProveedor;
import com.hunter.controlrutasyaku.Modelo.M_Configuracion;
import com.hunter.controlrutasyaku.Modelo.M_Empleado;
import com.hunter.controlrutasyaku.Modelo.M_Producto;
import com.hunter.controlrutasyaku.Modelo.SincronizaDetallePedido;
import com.hunter.controlrutasyaku.Modelo.SincronizaPedido;
import com.hunter.controlrutasyaku.Modelo.SincronizarClienteProveedor;
import com.hunter.controlrutasyaku.R;

public class MenuPrincipal extends AppCompatActivity {

    private ImageButton imbRegistroCliente;
    private ImageButton imbRegistroPedidos;
    private ImageButton imbVisualizaRutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        imbRegistroCliente = findViewById(R.id.imbRegistroCliente);
        imbRegistroPedidos = findViewById(R.id.imbRegistroPedido);
        imbVisualizaRutas = findViewById(R.id.imbVisualizaRutas);

        imbRegistroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, ListaClientes.class);
                startActivity(intent);
                finish();
            }
        });

        imbRegistroPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, RegistraPedido.class);
                startActivity(intent);
                finish();
            }
        });

        imbVisualizaRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, VisualizaRuta.class);
                startActivity(intent);
                finish();
            }
        });

        traeServidor();
        buscaNombreEmpleado();


        //Solicitud acceso a la red
        int permissionCheck = ContextCompat.checkSelfPermission(MenuPrincipal.this, Manifest.permission.INTERNET);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MenuPrincipal.this, Manifest.permission.INTERNET)) {

            } else {
                ActivityCompat.requestPermissions(MenuPrincipal.this, new String[]{Manifest.permission.INTERNET}, 1);
            }
        }



    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
        builder.setTitle("Mensaje de Confirmación");
        builder.setMessage("¿Desea salir del aplicativo?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MenuPrincipal.this.finish();
                finish();
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

    //Creación de Menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.mnuDescargarDatos) {
            traeDataToServer();
        } else if (item.getItemId() == R.id.mnuEnviaDatosToServer) {


            AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
            builder.setTitle("Mensaje de Confirmación");
            builder.setMessage("¿Desea Sincronizar desde el Servidor al Teléfono?");
            builder.setCancelable(false);
            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    //Toast.makeText(this, "Sincronizando datos", Toast.LENGTH_SHORT).show();

                    //Clientes
                    SincronizarClienteProveedor sincronizarClienteProveedor =  new SincronizarClienteProveedor();
                    sincronizarClienteProveedor.recorreListaClienteProveedor(MenuPrincipal.this);

                    //Pedido
                    SincronizaPedido sincronizaPedido = new SincronizaPedido();
                    sincronizaPedido.recorreListaSincronizaPedido(MenuPrincipal.this);

                    //Insercion de detalle de pedido luego de 3 segundos
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //DetallePedido
                    SincronizaDetallePedido sincronizaDetallePedido = new SincronizaDetallePedido();
                    sincronizaDetallePedido.recorreListaSincronizaPedidoDetalle(MenuPrincipal.this);


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



        }else if(item.getItemId() == R.id.mnuReportes) {

            startActivity(new Intent(MenuPrincipal.this, ReporteVentaDia.class));

        }else if(item.getItemId() == R.id.mnuConfigura){

            if(VariableGeneral.estadoConfiguracion){
                Intent intent = new Intent(MenuPrincipal.this, Configuracion.class);
                startActivity(intent);
                MenuPrincipal.this.finish();
            }else{
                Toast.makeText(this, "Acceso Denegado", Toast.LENGTH_SHORT).show();
            }

        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }


    void traeDataToServer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
        builder.setTitle("Mensaje de Confirmación");
        builder.setMessage("¿Desea Sincronizar desde el Servidor al Teléfono?");
        builder.setCancelable(false);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();

                if(m_clienteProveedor.cuentaPendientesEnvio(MenuPrincipal.this) == 0){
                    llamarEmpleados();
                    llamarClientes();
                    llamarProductos();
                }else{
                    Toast.makeText(MenuPrincipal.this, "¡Hay datos pendientes de envío!", Toast.LENGTH_SHORT).show();
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

    void llamarEmpleados(){
        M_Empleado m_empleado = new M_Empleado();
        m_empleado.insertarEmpleadoToLocal(MenuPrincipal.this);
    }

    void llamarClientes(){
        M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
        m_clienteProveedor.insertarClientesToLocal(MenuPrincipal.this);
    }

    void llamarProductos(){
        M_Producto m_producto = new M_Producto();
        m_producto.insertarProductoToLocal(MenuPrincipal.this);
    }

    void buscaNombreEmpleado(){
        M_Empleado m_empleado = new M_Empleado();

        if(VariableGeneral.CONTEO == 0){
            if(!VariableGeneral.EMP_ID.equals("")){
                String nombres = m_empleado.buscaEmpleadoNombre(MenuPrincipal.this, VariableGeneral.EMP_ID);
                Toast.makeText(this, "Bienvenido " + nombres, Toast.LENGTH_SHORT).show();
            }
            VariableGeneral.CONTEO++;
        }

    }

    void traeServidor(){
        M_Configuracion m_configuracion = new M_Configuracion();
        VariableGeneral.SERVIDOR = m_configuracion.traeConfiguracionServidor(MenuPrincipal.this);
    }


}
