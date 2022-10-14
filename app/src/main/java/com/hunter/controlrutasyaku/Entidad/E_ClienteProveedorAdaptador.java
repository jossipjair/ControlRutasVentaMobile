package com.hunter.controlrutasyaku.Entidad;

public class E_ClienteProveedorAdaptador {

    private String Nombres;
    private String CodigoInterno;
    private String Direccion;

    public E_ClienteProveedorAdaptador() {
    }

    public E_ClienteProveedorAdaptador(String nombres, String codigoInterno, String direccion) {
        Nombres = nombres;
        CodigoInterno = codigoInterno;
        Direccion = direccion;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getCodigoInterno() {
        return CodigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        CodigoInterno = codigoInterno;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
