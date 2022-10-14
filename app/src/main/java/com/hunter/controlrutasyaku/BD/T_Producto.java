package com.hunter.controlrutasyaku.BD;

public class T_Producto {

    private static final String TABLA = "Producto";

    private static final String PRO_ID = "Pro_Id";
    private static final String UNIMED_ID = "UniMed_Id";
    private static final String PRO_CODIGO = "Pro_Codigo";
    private static final String PRO_DESCRIPCION = "Pro_Descripcion";
    private static final String PRO_NOMBRE = "Pro_Nombre";
    private static final String PRO_TIPO = "Pro_Tipo";
    private static final String PRO_ESTADO = "Pro_Estado";
    private static final String PRO_PRECIO = "Pro_Precio";

    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + PRO_ID + " INTEGER NOT NULL,"
            + UNIMED_ID + " TEXT NOT NULL,"
            + PRO_CODIGO + " TEXT NOT NULL,"
            + PRO_DESCRIPCION + " TEXT NOT NULL,"
            + PRO_NOMBRE + " TEXT NOT NULL,"
            + PRO_TIPO + " TEXT NOT NULL,"
            + PRO_ESTADO + " TEXT NOT NULL,"
            + PRO_PRECIO + " TEXT NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static final String INSERT_PRODUCTO(int pro_Id, String uniMed_Id, String pro_Codigo, String pro_Descripcion, String pro_Nombre,
                                               String pro_Tipo, String pro_Estado, String pro_Precio){
        return "INSERT INTO " + TABLA + "(" + PRO_ID + "," + UNIMED_ID + "," + PRO_CODIGO + "," + PRO_DESCRIPCION + ","
                + PRO_NOMBRE + "," + PRO_TIPO + "," + PRO_ESTADO + "," + PRO_PRECIO +") VALUES("
                + pro_Id + ",'" + uniMed_Id + "','" + pro_Codigo + "','" + pro_Descripcion + "','" + pro_Nombre + "','" + pro_Tipo + "','" + pro_Estado + "','" + pro_Precio + "');";
    }

    public static final String SELECT_PRODUCTO(String pro_Nombre){
        return "SELECT " + PRO_ID + "," + PRO_NOMBRE + ","+ PRO_PRECIO + " FROM " + TABLA + " WHERE " + PRO_ESTADO + "='true' AND " + PRO_NOMBRE + " LIKE '%" + pro_Nombre + "%';";
    }

    public static final String SELECT_PRODUCTO(){
        return "SELECT " + PRO_ID + "," + PRO_NOMBRE + ","+ PRO_PRECIO + " FROM " + TABLA + " WHERE " + PRO_ESTADO + "='1';";
    }

    public static final String SELECT_PRODUCTO_PRECIO(int pro_Id){
        return "SELECT " + PRO_PRECIO + " FROM " + TABLA + " WHERE " + PRO_ID + "=" + pro_Id + ";";
    }

    public static final String SELECT_PRODUCTO(int pro_Id){
        return "SELECT " + PRO_DESCRIPCION + " FROM " + TABLA + " WHERE " + PRO_ID + "=" + pro_Id + ";";
    }



}

