package com.hunter.controlrutasyaku.Controlador;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hunter.controlrutasyaku.R;

public class LoginDialogConfiguracion extends DialogFragment {

    private Button btnAccederConfiguracion;
    private EditText txtUsuarioConfiguracion;
    private EditText txtPasswordConfiguracion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_configuracion, container, false);

        txtUsuarioConfiguracion = view.findViewById(R.id.txtUsuarioConfiguracion);
        txtPasswordConfiguracion = view.findViewById(R.id.txtPasswordConfiguracion);
        btnAccederConfiguracion = view.findViewById(R.id.btnAccederConfiguracion);

        btnAccederConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUsuarioConfiguracion.getText().toString().equals("YAKU") && txtPasswordConfiguracion.getText().toString().equals("9999")) {
                    LoginDialogConfiguracion dialogConfiguracion = new LoginDialogConfiguracion();
                    dialogConfiguracion.show(getFragmentManager(), "LoginConfiguracion");
                    getDialog().dismiss();
                } else {
                    getDialog().dismiss();
                }
            }
        });
        return view;
    }

}
