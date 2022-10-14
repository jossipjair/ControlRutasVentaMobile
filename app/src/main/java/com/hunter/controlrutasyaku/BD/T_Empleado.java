package com.hunter.controlrutasyaku.BD;

public class T_Empleado {

    private static final String TABLA = "Empleado";

    private static final String EMP_ID = "Emp_Id";
    private static final String EMP_DNI = "Emp_Dni";
    private static final String EMP_APELLIDOSNOMB = "Emp_ApellidoNomb";
    private static final String EMP_TELEFONO = "Emp_Telefono";

    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + "("
            + EMP_ID + " TEXT NOT NULL,"
            + EMP_DNI + " TEXT NOT NULL,"
            + EMP_APELLIDOSNOMB + " TEXT NOT NULL,"
            + EMP_TELEFONO + " TEXT NOT NULL);";

    public static final String DROP_TABLA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static final String INSERT_EMPLEADO(String emp_Id, String emp_Dni, String emp_ApellidosNomb, String emp_Telefono){
        return "INSERT INTO " + TABLA + "(" + EMP_ID + "," + EMP_DNI + "," + EMP_APELLIDOSNOMB + "," + EMP_TELEFONO + ") VALUES('"
                + emp_Id + "','" + emp_Dni + "','" + emp_ApellidosNomb + "','" + emp_Telefono + "');";
    }

    public static final String SELECT_EMPLEADO(){
        return "SELECT " + EMP_ID + "," + EMP_DNI + "," + EMP_APELLIDOSNOMB + " FROM " + TABLA + ";";
    }

    public static final String SELECT_EMPLEADO_LOGIN(String dni){
        return "SELECT " + EMP_DNI + " FROM " + TABLA + " WHERE " + EMP_DNI + "='" + dni +"';";
    }

    public static final String SELECT_EMPLEADO_NOMBRES(String dni){
        return "SELECT " + EMP_APELLIDOSNOMB + " FROM " + TABLA + " WHERE " + EMP_DNI + "='" + dni +"';";
    }


    public static final String SELECT_EMPLEADO_NOMBRES_TELEFONOS(){
        return "SELECT " + EMP_APELLIDOSNOMB + ","+ EMP_TELEFONO + " FROM " + TABLA + " ORDER BY " + EMP_APELLIDOSNOMB +";";
    }

}
