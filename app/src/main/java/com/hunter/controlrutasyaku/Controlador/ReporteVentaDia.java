package com.hunter.controlrutasyaku.Controlador;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.hunter.controlrutasyaku.Modelo.M_Pedido;
import com.hunter.controlrutasyaku.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReporteVentaDia extends AppCompatActivity {


    private TextView txtTotalVentas;
    private TextView txtPagoContado;
    private TextView txtPagoSiete;
    private TextView txtCreditoQuince;
    private TextView txtCreditoTreinta;
    private TextView txtPendientePago;
    private TextView txtTituloVentas;
    private ListView lvReporteVentas;
    private M_Pedido m_pedido;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_venta_dia);

        txtTotalVentas = findViewById(R.id.txtTotalVentas);
        txtPagoContado = findViewById(R.id.txtPagoContado);
        txtPagoSiete = findViewById(R.id.txtPagoSiete);
        txtCreditoQuince = findViewById(R.id.txtCreditoQuince);
        txtCreditoTreinta = findViewById(R.id.txtCreditoTreinta);
        txtPendientePago = findViewById(R.id.txtPendientePago);
        txtTituloVentas = findViewById(R.id.txtTituloVentas);
        lvReporteVentas = findViewById(R.id.lvReporteVentas);
        preferences = getSharedPreferences("PREFERENCIA", MainActivity.MODE_PRIVATE);

        m_pedido = new M_Pedido();

        txtTituloVentas.setText("TOTAL VENTAS DEL " + fechaHora("dd/MM/yyyy"));

        txtTotalVentas.setText(String.valueOf(mostrarTotalVentas()));
        txtPagoContado.setText(String.valueOf(mostrarPagoContado()));
        txtPagoSiete.setText(String.valueOf(mostrarPagoSiete()));
        txtCreditoQuince.setText(String.valueOf(mostrarPagoQuince()));
        txtCreditoTreinta.setText(String.valueOf(mostrarPagoTreinta()));
        txtPendientePago.setText(String.valueOf(mostrarPagoPendiente()));
    }


    double mostrarTotalVentas(){
        return m_pedido.mostrarTotales(ReporteVentaDia.this, preferences.getString("DNI", ""));
    }

    double mostrarPagoContado(){
        return m_pedido.mostrarTotalesTipoPago(ReporteVentaDia.this, preferences.getString("DNI", ""), "AL CONTADO");
    }

    double mostrarPagoSiete(){
        return m_pedido.mostrarTotalesTipoPago(ReporteVentaDia.this, preferences.getString("DNI", ""), "CREDITO 7 DIAS");
    }

    double mostrarPagoQuince(){
        return m_pedido.mostrarTotalesTipoPago(ReporteVentaDia.this, preferences.getString("DNI", ""), "CREDITO 15 DIAS");
    }

    double mostrarPagoTreinta(){
        return m_pedido.mostrarTotalesTipoPago(ReporteVentaDia.this, preferences.getString("DNI", ""), "CREDITO 30 DIAS");
    }

    double mostrarPagoPendiente(){
        return m_pedido.mostrarTotalesTipoPago(ReporteVentaDia.this, preferences.getString("DNI", ""), "PENDIENTE DE PAGO");
    }

    private static String fechaHora(String formato){ //"dd/MM/yyyy HH:mm:ss"
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }
}
