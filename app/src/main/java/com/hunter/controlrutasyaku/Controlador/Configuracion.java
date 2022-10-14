package com.hunter.controlrutasyaku.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Modelo.M_Configuracion;
import com.hunter.controlrutasyaku.R;

public class Configuracion extends AppCompatActivity {

    private EditText txtServidor;
    private Button btnGrabar;
    private Button btnSalirSistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        txtServidor = findViewById(R.id.txtServidor);
        btnGrabar = findViewById(R.id.btnGrabar);
        btnSalirSistema = findViewById(R.id.btnSalirSistema);

        M_Configuracion mConfiguracion = new M_Configuracion();
        if(!mConfiguracion.traeConfiguracionServidor(Configuracion.this).equals("-")){
            txtServidor.setText(mConfiguracion.traeConfiguracionServidor(Configuracion.this));
        }

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtServidor.getText().toString().equals("")){
                    M_Configuracion m_configuracion = new M_Configuracion();
                    m_configuracion.insertarConfiguracion(Configuracion.this, txtServidor.getText().toString());
                }else{
                    Toast.makeText(Configuracion.this, "No se ha encontrado datos que guardar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSalirSistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Configuracion.this, MenuPrincipal.class);
                startActivity(intent);
                Configuracion.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Configuracion.this, MenuPrincipal.class);
        startActivity(intent);
        Configuracion.this.finish();
    }
}
