package com.hunter.controlrutasyaku.BD;

public class T_DetallePedido {

    private static final String TABLA = "DetallePedido";

    private static final String PEDDET_ID = "PedDet_Id";
    private static final String PED_ID = "Ped_Id";
    private static final String PRO_ID = "Pro_Id";
    private static final String PEDDET_CANTIDAD = "PedDet_Cantidad";
    private static final String PEDDET_PRECIOUNI = "PedDet_PrecioUni";
    private static final String PEDDET_IMPORTE = "PedDet_Importe";
    private static final String PEDDET_ESSINCRONIZADO = "PedDet_EsSincronizado";
    private static final String PEDDET_ESTERMINADO = "PedDet_EsTerminado";

    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + PEDDET_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + PED_ID + " TEXT NOT NULL,"
            + PRO_ID + " INTEGER NOT NULL,"
            + PEDDET_CANTIDAD + " INTEGER NOT NULL,"
            + PEDDET_PRECIOUNI + " TEXT NOT NULL,"
            + PEDDET_IMPORTE + " TEXT NOT NULL,"
            + PEDDET_ESTERMINADO + " INTEGER NOT NULL,"
            + PEDDET_ESSINCRONIZADO + " INTEGER NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static final String INSERT_DETALLE_PEDIDO(String ped_Id, int pro_Id, int pedDet_Cantidad, String pedDet_PrecioUni, String pedDet_Importe){
        return "INSERT INTO " + TABLA + "(" + PED_ID + "," + PRO_ID + "," + PEDDET_CANTIDAD + "," + PEDDET_PRECIOUNI + "," + PEDDET_IMPORTE + ","  + PEDDET_ESTERMINADO + "," + PEDDET_ESSINCRONIZADO  + ") VALUES('"
                + ped_Id + "'," + pro_Id + "," + pedDet_Cantidad + ",'" + pedDet_PrecioUni + "','" + pedDet_Importe + "', 0, 0 );";
    }

    public static final String GUARDA_DETALLE_PEDIDO(String ped_Id){
        return "UPDATE " + TABLA + " SET " + PEDDET_ESTERMINADO + "=1 WHERE " + PED_ID + "='" + ped_Id + "';";
    }

    public static final String SELECT_DETALLE_PEDIDO(String Ped_Id){
        return "SELECT " + PRO_ID + ","  + PEDDET_IMPORTE + "," + PEDDET_PRECIOUNI  + "," + PEDDET_CANTIDAD + "," + PEDDET_ID
                + " FROM " + TABLA + " WHERE " + PED_ID + "='" + Ped_Id +"';";
    }

    public static final String DELETE_DETALLE_PEDIDO(int PedDet_Id){
        return "DELETE FROM " + TABLA + " WHERE " + PEDDET_ID + "=" + PedDet_Id + ";";
    }

    public static final String SELECT_TOTAL_DETALLE(String Ped_Id){
        return "SELECT SUM(CAST(" + PEDDET_IMPORTE + " AS FLOAT)) FROM " + TABLA + " WHERE " + PED_ID + "='" + Ped_Id + "';";
    }

    public static final String ACTUALIZA_DETALLE_PEDIDO(int DetPed_Id)
    {
        return "UPDATE " + TABLA + " SET " + PEDDET_ESSINCRONIZADO + "=" + 1 + " WHERE " + PEDDET_ID + "=" + DetPed_Id + ";";
    }

    public static final String SINCRONIZA_SERVIDOR(){
        return "SELECT " + PEDDET_ID + "," +  PED_ID + "," + PRO_ID + "," + PEDDET_CANTIDAD + "," + PEDDET_PRECIOUNI + "," + PEDDET_IMPORTE + " FROM " + TABLA
                + " WHERE " + PEDDET_ESSINCRONIZADO + "=" + 0 + " AND " + PEDDET_ESTERMINADO + "=" + 1 + ";";
    }

}
