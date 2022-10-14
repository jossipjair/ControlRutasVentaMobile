package com.hunter.controlrutasyaku.BD;

public class T_Configuracion {

    private static final String TABLA = "Configuracion";
    private static final String CONF_ID = "Conf_Id";
    private static final String CONF_SERVIDOR = "Conf_Servidor";

    public static final String CREATE_TABLA = "CREATE TABLE "+ TABLA + "("
            + CONF_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + CONF_SERVIDOR + " TEXT NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS "+ TABLA + ";";
    public static final String DELETE_TABLA = "DELETE FROM " + TABLA + ";";

    public static final String INSERT_CONFIGURACION(String conf_Servidor){
       return "INSERT INTO " + TABLA + "(" + CONF_SERVIDOR + ") VALUES('" + conf_Servidor +"');";
    }

    public static final String SELECT_CONFIGURACION(){
        return "SELECT " + CONF_ID + " FROM "  + TABLA + ";";
    }

    public static final String SELECT_CONFIGURACION_SERVIDOR(){
        return "SELECT " + CONF_SERVIDOR + " FROM "  + TABLA + ";";
    }





}
