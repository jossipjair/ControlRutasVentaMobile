package com.hunter.controlrutasyaku.Entidad;

public class E_DetallePedidoAdaptador {

    private int IdDetalle;
    private String NombreProducto;
    private double ImporteDetalle;
    private double PrecioProducto;
    private double CantidadProducto;

    public E_DetallePedidoAdaptador() {
    }

    public E_DetallePedidoAdaptador(int idDetalle, String nombreProducto, double importeDetalle, double precioProducto, double cantidadProducto) {
        IdDetalle = idDetalle;
        NombreProducto = nombreProducto;
        ImporteDetalle = importeDetalle;
        PrecioProducto = precioProducto;
        CantidadProducto = cantidadProducto;
    }

    public int getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        IdDetalle = idDetalle;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public double getImporteDetalle() {
        return ImporteDetalle;
    }

    public void setImporteDetalle(double importeDetalle) {
        ImporteDetalle = importeDetalle;
    }

    public double getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        PrecioProducto = precioProducto;
    }

    public double getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(double cantidadProducto) {
        CantidadProducto = cantidadProducto;
    }
}
