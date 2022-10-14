package com.hunter.controlrutasyaku.BD;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class T_RecorridoGps {

    private int recGps_Id;
    private String recGps_FechaHora;
    private String recGps_Fecha;
    private String recGps_Latitud;
    private String recGps_Longitud;

    private static final String TABLA = "RecorridoGps";

    private static final String RECGPS_ID = "RecGps_Id";
    private static final String RECGPS_FECHAHORA = "RecGps_FechaHora";
    private static final String RECGPS_FECHA = "RecGps_Fecha";
    private static final String RECGPS_LATITUD = "RecGps_Latitud";
    private static final String RECGPS_LONGITUD = "RecGps_Longitud";
    private static final String RECGPS_ESSINCRONIZADO = "PedDet_EsSincronizado";

    private static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + RECGPS_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + RECGPS_FECHAHORA + " TEXT NOT NULL,"
            + RECGPS_FECHA + " TEXT NOT NULL,"
            + RECGPS_LATITUD + " TEXT NOT NULL,"
            + RECGPS_LONGITUD + " TEXT NOT NULL,"
            + RECGPS_ESSINCRONIZADO + " INTEGER NOT NULL);";

    private static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";


    private static final String INSERT_RECORRIDOGPS(String recGps_Latitud, String recGps_Longitud){
        return "INSERT INTO " + TABLA + "(" + RECGPS_ID + "," + RECGPS_FECHAHORA + "," + RECGPS_FECHA + "," + RECGPS_LATITUD + "," + RECGPS_LONGITUD + "," + RECGPS_ESSINCRONIZADO + ") VALUES('"
                + fechaHora("dd/MM/yyyy HH:mm:ss") + "','" + fechaHora("dd/MM/yyyy") + "','" + recGps_Latitud + "','" + recGps_Longitud + "', 0 );";
    }

    private static final String SELECT_RECORRIDOGPS(){
        return "SELECT " + RECGPS_FECHAHORA + "," + RECGPS_FECHA + "," + RECGPS_LATITUD + "," + RECGPS_LONGITUD + " FROM " + TABLA + " WHERE " + RECGPS_ESSINCRONIZADO + "=0";
    }



    private static String fechaHora(String formato){ //"dd/MM/yyyy HH:mm:ss"
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }

}
