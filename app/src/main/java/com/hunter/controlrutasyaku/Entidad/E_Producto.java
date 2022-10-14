package com.hunter.controlrutasyaku.Entidad;

public class E_Producto {

    private int pro_Id;
    private String uniMed_Id;
    private String pro_Codigo;
    private String pro_Descripcion;
    private String pro_Nombre;
    private String pro_Tipo;
    private String pro_Estado;
    private String pro_Precio;

    public E_Producto() {
    }

    public E_Producto(int pro_Id, String uniMed_Id, String pro_Codigo, String pro_Descripcion, String pro_Nombre, String pro_Tipo, String pro_Estado, String pro_Precio) {
        this.pro_Id = pro_Id;
        this.uniMed_Id = uniMed_Id;
        this.pro_Codigo = pro_Codigo;
        this.pro_Descripcion = pro_Descripcion;
        this.pro_Nombre = pro_Nombre;
        this.pro_Tipo = pro_Tipo;
        this.pro_Estado = pro_Estado;
        this.pro_Precio = pro_Precio;
    }

    public E_Producto(int pro_Id, String pro_Nombre, String pro_Precio) {
        this.pro_Id = pro_Id;
        this.pro_Nombre = pro_Nombre;
        this.pro_Precio = pro_Precio;
    }

    public E_Producto(String pro_Nombre) {
        this.pro_Nombre = pro_Nombre;
    }



    public int getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(int pro_Id) {
        this.pro_Id = pro_Id;
    }

    public String getUniMed_Id() {
        return uniMed_Id;
    }

    public void setUniMed_Id(String uniMed_Id) {
        this.uniMed_Id = uniMed_Id;
    }

    public String getPro_Codigo() {
        return pro_Codigo;
    }

    public void setPro_Codigo(String pro_Codigo) {
        this.pro_Codigo = pro_Codigo;
    }

    public String getPro_Descripcion() {
        return pro_Descripcion;
    }

    public void setPro_Descripcion(String pro_Descripcion) {
        this.pro_Descripcion = pro_Descripcion;
    }

    public String getPro_Nombre() {
        return pro_Nombre;
    }

    public void setPro_Nombre(String pro_Nombre) {
        this.pro_Nombre = pro_Nombre;
    }

    public String getPro_Tipo() {
        return pro_Tipo;
    }

    public void setPro_Tipo(String pro_Tipo) {
        this.pro_Tipo = pro_Tipo;
    }

    public String getPro_Estado() {
        return pro_Estado;
    }

    public void setPro_Estado(String pro_Estado) {
        this.pro_Estado = pro_Estado;
    }

    public String getPro_Precio() {
        return pro_Precio;
    }

    public void setPro_Precio(String pro_Precio) {
        this.pro_Precio = pro_Precio;
    }

    @Override
    public String toString(){
        return getPro_Nombre();
    }

}
