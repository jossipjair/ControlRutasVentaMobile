package com.hunter.controlrutasyaku.BD;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class T_Pedido {

    private static final String TABLA = "Pedido";

    private static final String PED_ID = "Ped_Id";
    private static final String CLI_PRO = "CliPro_Id";
    private static final String PED_DESPACHADOA = "Ped_DespachadoA";
    private static final String PED_FECHAPED = "Ped_FechaPed";
    private static final String PED_FECHAHORAPED = "Ped_FechaHoraPed";
    private static final String PED_FECHAHORALOG = "Ped_FechaHoraLog";
    private static final String PED_FECHAENTREGAPED = "Ped_FechaEntregaPed";
    private static final String EMP_ID = "Emp_Id";
    private static final String PED_FORMAPAG = "Ped_FormaPag";
    private static final String PED_OBSERVACION = "Ped_Observacion";
    private static final String PED_TOTAL = "Ped_Total";
    private static final String PED_ESSINCRONIZADO = "Ped_EsSincronizado";
    private static final String PED_ESTERMINADO = "Ped_EsTerminado";


    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + PED_ID + " TEXT NOT NULL PRIMARY KEY,"
            + CLI_PRO + " TEXT NOT NULL,"
            + PED_DESPACHADOA + " TEXT NOT NULL,"
            + PED_FECHAPED + " TEXT NOT NULL,"
            + PED_FECHAHORAPED + " TEXT NOT NULL,"
            + PED_FECHAHORALOG + " TEXT NOT NULL,"
            + PED_FECHAENTREGAPED + " TEXT NOT NULL,"
            + EMP_ID + " TEXT NOT NULL,"
            + PED_FORMAPAG + " TEXT NOT NULL,"
            + PED_OBSERVACION + " TEXT NOT NULL,"
            + PED_ESTERMINADO + " INTEGER NOT NULL,"
            + PED_ESSINCRONIZADO + " INTEGER NOT NULL,"
            + PED_TOTAL + " TEXT NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static final String INSERT_PEDIDO(String ped_Id, String cli_Pro, String ped_DespachadoA, String fechaEntregaPedido, String emp_Id, String ped_FormaPago, String ped_Observacion){
        return "INSERT INTO " + TABLA + "(" + PED_ID + "," + CLI_PRO + "," + PED_DESPACHADOA + "," + PED_FECHAPED + ","
                + PED_FECHAHORAPED + "," + PED_FECHAHORALOG + "," + PED_FECHAENTREGAPED + "," + EMP_ID + "," + PED_FORMAPAG + "," + PED_OBSERVACION  + "," + PED_ESTERMINADO + "," + PED_ESSINCRONIZADO + "," + PED_TOTAL + ") VALUES('"
                + ped_Id + "','" + cli_Pro + "','" + ped_DespachadoA + "','" + fechaHora("dd/MM/yyyy") + "','"
                + fechaHora("dd/MM/yyyy HH:mm:ss") + "','" + fechaHora("dd/MM/yyyy HH:mm:ss") + "','" + fechaEntregaPedido + "','" + emp_Id + "','"
                + ped_FormaPago + "','" + ped_Observacion + "', 0, 0,'0');";
    }

    public static final String GUARDA_PEDIDO(String ped_Id, String total){
        return "UPDATE " + TABLA + " SET " + PED_ESTERMINADO + "=1, " + PED_TOTAL + "='" + total + "' WHERE " + PED_ID + "='" + ped_Id + "';";
    }

    public static final String SINCRONIZA_SERVIDOR(){
        return "SELECT " + PED_ID + "," + CLI_PRO + "," + PED_DESPACHADOA + "," + PED_FECHAPED + ","
                + PED_FECHAHORAPED + "," + PED_FECHAHORALOG + "," + PED_FECHAENTREGAPED + "," + EMP_ID + ","
                + PED_FORMAPAG + "," + PED_OBSERVACION  + "," + PED_ESTERMINADO + "," + PED_ESSINCRONIZADO + "," + PED_TOTAL
                + " FROM " + TABLA + " WHERE " + PED_ESSINCRONIZADO + "=" + 0 + " AND " + PED_ESTERMINADO + "=" + 1 + ";";
    }

    public static final String ACTUALIZA_PEDIDO(String ped_Id){
        return "UPDATE " + TABLA + " SET " + PED_ESSINCRONIZADO + "=" + 1 +" WHERE " + PED_ID + "='" + ped_Id + "';";
    }



    public static String VENTA_TOTAL(String Empleado){
        return "SELECT SUM(CAST(" + PED_TOTAL + " AS FLOAT)) FROM " + TABLA + " WHERE " + PED_ESTERMINADO + "=" + 1
                + " AND " + EMP_ID + "='" + Empleado + "' AND " +  PED_FECHAPED + "='" + fechaHora("dd/MM/yyyy") + "';";
    }

    public static String VENTA_TOTAL_FORMA_PAGO(String Empleado, String FormaPago){
        return "SELECT SUM(CAST(" + PED_TOTAL + " AS FLOAT)) FROM " + TABLA + " WHERE " + PED_ESTERMINADO + "=" + 1
                + " AND " + EMP_ID + "='" + Empleado + "' AND " + PED_FORMAPAG + "='" + FormaPago + "' AND " +  PED_FECHAPED + "='" + fechaHora("dd/MM/yyyy") + "';";
    }


    private static String fechaHora(String formato){ //"dd/MM/yyyy HH:mm:ss"
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }


}

