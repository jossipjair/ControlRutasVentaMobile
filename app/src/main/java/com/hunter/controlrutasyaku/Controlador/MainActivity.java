package com.hunter.controlrutasyaku.Controlador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hunter.controlrutasyaku.Modelo.M_Configuracion;
import com.hunter.controlrutasyaku.Modelo.M_Empleado;
import com.hunter.controlrutasyaku.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAcceder;
    private EditText txtUsuario;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = findViewById(R.id.txtUsuario);
        btnAcceder = findViewById(R.id.btnAcceder);
        preferences = getSharedPreferences("PREFERENCIA", MainActivity.MODE_PRIVATE);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                M_Empleado m_empleado = new M_Empleado();
                String dni = m_empleado.validaUsuario(MainActivity.this, txtUsuario.getText().toString());

                M_Configuracion m_configuracion = new M_Configuracion();
                int confi = m_configuracion.traeConfiguracion(MainActivity.this);

                if (txtUsuario.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Ingrese su DNI", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtUsuario.getText().toString().equals(dni)) {

                        if (confi > 0) {
                            VariableGeneral.estadoConfiguracion = false;
                            VariableGeneral.EMP_ID = dni;

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("DNI", dni);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Aplicativo sin configuración, contacte con el desarrollador", Toast.LENGTH_SHORT).show();
                        }

                    } else if (txtUsuario.getText().toString().equals("71852207")) {
                        if (confi == 0) {
                            VariableGeneral.estadoConfiguracion = true;
                            Intent intent = new Intent(MainActivity.this, Configuracion.class);
                            startActivity(intent);
                            finish();
                        } else {
                            VariableGeneral.estadoConfiguracion = true;
                            Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
