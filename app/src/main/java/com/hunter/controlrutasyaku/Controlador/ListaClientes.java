package com.hunter.controlrutasyaku.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Modelo.M_ClienteProveedor;
import com.hunter.controlrutasyaku.R;

import java.util.List;

public class ListaClientes extends AppCompatActivity {

    private Button btnRegistroClienteLista;
    private ListView gvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        btnRegistroClienteLista = findViewById(R.id.btnRegistroClienteLista);
        gvClientes = findViewById(R.id.lvClientes);
        mostrarClientes();

        btnRegistroClienteLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClientes.this, RegistraCliente.class);
                startActivity(intent);
                ListaClientes.this.finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ListaClientes.this, MenuPrincipal.class);
        startActivity(intent);
        ListaClientes.this.finish();
    }

    void mostrarClientes(){
        try {
            M_ClienteProveedor m_clienteProveedor = new M_ClienteProveedor();
            m_clienteProveedor.mostrarAdaptadorClientes(ListaClientes.this, gvClientes);
        }catch (Exception e){
            Toast.makeText(this, "Sin registros para mostrar", Toast.LENGTH_SHORT).show();
        }
    }
}
