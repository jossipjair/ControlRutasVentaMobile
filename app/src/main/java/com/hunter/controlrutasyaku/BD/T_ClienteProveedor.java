package com.hunter.controlrutasyaku.BD;

public class T_ClienteProveedor {

    private static final String TABLA = "ClienteProveedor";

    private static final String CLIPRO_ID = "CliPro_Id";
    private static final String CLIPRO_RUC = "CliPro_Ruc";
    private static final String CLIPRO_NOMBRECOM = "CliPro_NombreCom";
    private static final String CLIPRO_PERSONACONTACTO = "CliPro_PersonaContacto";
    private static final String CLIPRO_DIRECCION = "CliPro_Direccion";
    private static final String CLIPRO_TELEFONO = "CliPro_Telefono";
    private static final String CLIPRO_LATITUDDIR = "CliPro_LatitudDir";
    private static final String CLIPRO_LONGITUDDIR = "CliPro_LongitudDir";
    private static final String EMP_ID = "Emp_Id";
    private static final String ES_SINCRONIZADO = "Es_Sincronizado";

    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + CLIPRO_ID + " TEXT NOT NULL,"
            + CLIPRO_RUC + " TEXT,"
            + CLIPRO_NOMBRECOM + " TEXT NOT NULL,"
            + CLIPRO_PERSONACONTACTO + " TEXT NOT NULL,"
            + CLIPRO_DIRECCION + " TEXT NOT NULL,"
            + CLIPRO_TELEFONO + " TEXT,"
            + CLIPRO_LATITUDDIR + " TEXT NOT NULL,"
            + CLIPRO_LONGITUDDIR + " TEXT NOT NULL,"
            + EMP_ID + " TEXT NOT NULL,"
            + ES_SINCRONIZADO + " INTEGER NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static final String INSERT_CLIENTE(String cliPro_Id, String cliPro_Ruc, String cliPro_NombreCom, String cliPro_PersonaContacto,
                                              String cliPro_Direccion, String cliPro_Telefono, String cliPro_LatitudDir, String cliPro_LongitudDir, String emp_Id, int es_Sincronizado){
        return "INSERT INTO " + TABLA + "(" + CLIPRO_ID + "," + CLIPRO_RUC + "," + CLIPRO_NOMBRECOM + "," + CLIPRO_PERSONACONTACTO  + "," + CLIPRO_DIRECCION + "," + CLIPRO_TELEFONO + ","
                + CLIPRO_LATITUDDIR + "," + CLIPRO_LONGITUDDIR + "," + EMP_ID + "," + ES_SINCRONIZADO + ") VALUES('" + cliPro_Id + "','" + cliPro_Ruc + "','" + cliPro_NombreCom + "','" + cliPro_PersonaContacto  + "','" + cliPro_Direccion + "','"
                + cliPro_Telefono + "','" + cliPro_LatitudDir + "','" + cliPro_LongitudDir + "','"+ emp_Id + "', " + es_Sincronizado + ");";
    }

    public static final String SELECT_CLIENTE(String cliente){
        return "SELECT " + CLIPRO_ID + "," + CLIPRO_RUC + "," + CLIPRO_NOMBRECOM + "," + CLIPRO_DIRECCION + "," + CLIPRO_TELEFONO + " FROM " + TABLA
                + " WHERE " + CLIPRO_NOMBRECOM + " LIKE '%" + cliente + "%';";
    }

    public static final String SINCRONIZA_SERVIDOR(){
        return "SELECT " + CLIPRO_ID + "," + CLIPRO_RUC + "," + CLIPRO_NOMBRECOM + "," + CLIPRO_PERSONACONTACTO  + "," + CLIPRO_DIRECCION + "," + CLIPRO_TELEFONO + ","
                + CLIPRO_LATITUDDIR + "," + CLIPRO_LONGITUDDIR + "," + EMP_ID + " FROM " + TABLA + " WHERE " + ES_SINCRONIZADO + " = 0;";
    }

    public static final String ACTUALIZA_ESTADO_SINCRONIZACION(String idCliente){
        return "UPDATE " + TABLA + " SET " + ES_SINCRONIZADO + "=" + 1 + " WHERE " + CLIPRO_ID + "='" + idCliente + "';";
    }

    public static final String SELECT_CLIENTE_BUSQUEDA(String cliente){
        return "SELECT " + CLIPRO_NOMBRECOM  + " FROM " + TABLA
                + " WHERE " + CLIPRO_NOMBRECOM + " LIKE '%" + cliente + "%' ORDER BY " + CLIPRO_NOMBRECOM  +";";
    }

    public static final String SELECT_CLIENTE_EXACTO(String cliente){
        return "SELECT " + CLIPRO_ID + "," + CLIPRO_RUC + "," + CLIPRO_NOMBRECOM + "," + CLIPRO_PERSONACONTACTO + "," + CLIPRO_DIRECCION + "," + CLIPRO_TELEFONO + "," + CLIPRO_LATITUDDIR + "," + CLIPRO_LONGITUDDIR + " FROM " + TABLA
                + " WHERE " + CLIPRO_NOMBRECOM + "='" + cliente + "';";
    }

    public static final String SELECT_CLIENTE_LISTA(){
        return "SELECT " + CLIPRO_ID + ","  + CLIPRO_NOMBRECOM + "," + CLIPRO_DIRECCION + " FROM " + TABLA;
    }

    public static final String DELETE_CLENTE()
    {
        return "DELETE FROM " + TABLA + ";";
    }

    public static final String SELECT_CLIENTES_NO_ENVIADOS(){
        return "SELECT COUNT(*) FROM " +  TABLA + " WHERE " + ES_SINCRONIZADO + "=" + 0 + ";";
    }

    public  static final String SELECT_CLIENTE_ADAPTADOR(){
        return "SELECT " + CLIPRO_NOMBRECOM + "," + CLIPRO_ID + "," + CLIPRO_DIRECCION + " FROM " + TABLA + ";";
    }

}

